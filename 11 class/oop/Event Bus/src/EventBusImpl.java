import events.Event;
import exception.MissingSubscriptionException;
import subscribers.Subscriber;

import java.time.Instant;
import java.util.*;

public class EventBusImpl implements EventBus {

    private final Map<Class<?>, List<Subscriber<?>>> subscribersMap;
    private final Map<Class<?>, List<Event<?>>> eventLogs;

    public EventBusImpl() {
        this.subscribersMap = new HashMap<>();
        this.eventLogs = new HashMap<>();
    }

    @Override
    public <T extends Event<?>> void subscribe(Class<T> eventType, Subscriber<? super T> subscriber) {
        if (eventType == null || subscriber == null) {
            throw new IllegalArgumentException("Event type and subscriber cannot be null.");
        }
        subscribersMap.computeIfAbsent(eventType, k -> new ArrayList<>()).add(subscriber);
    }

    @Override
    public <T extends Event<?>> void unsubscribe(Class<T> eventType, Subscriber<? super T> subscriber)
            throws MissingSubscriptionException {
        if (eventType == null || subscriber == null) {
            throw new IllegalArgumentException("Event type and subscriber cannot be null.");
        }

        List<Subscriber<?>> subscribers = subscribersMap.get(eventType);
        if (subscribers == null || !subscribers.remove(subscriber)) {
            throw new MissingSubscriptionException("Subscriber is not subscribed to the event type.");
        }
    }

    @Override
    public <T extends Event<?>> void publish(T event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null.");
        }

        Class<?> eventType = event.getClass();
        List<Subscriber<?>> subscribers = subscribersMap.get(eventType);
        if (subscribers != null) {
            for (Subscriber<?> subscriber : subscribers) {
                @SuppressWarnings("unchecked")
                Subscriber<T> typedSubscriber = (Subscriber<T>) subscriber;
                typedSubscriber.onEvent(event);
            }
        }

        eventLogs.computeIfAbsent(eventType, k -> new ArrayList<>()).add(event);
    }

    @Override
    public void clear() {
        subscribersMap.clear();
        eventLogs.clear();
    }

    @Override
    public Collection<? extends Event<?>> getEventLogs(Class<? extends Event<?>> eventType, Instant from, Instant to) {
        if (eventType == null || from == null || to == null) {
            throw new IllegalArgumentException("Event type and timestamps cannot be null.");
        }

        List<Event<?>> logs = eventLogs.getOrDefault(eventType, Collections.emptyList());
        List<Event<?>> filteredLogs = new ArrayList<>();

        for (Event<?> event : logs) {
            Instant timestamp = event.getTimestamp();
            if (!timestamp.isBefore(from) && timestamp.isBefore(to)) {
                filteredLogs.add(event);
            }
        }

        return Collections.unmodifiableList(filteredLogs);
    }

    @Override
    public <T extends Event<?>> Collection<Subscriber<?>> getSubscribersForEvent(Class<T> eventType) {
        if (eventType == null) {
            throw new IllegalArgumentException("Event type cannot be null.");
        }

        List<Subscriber<?>> subscribers = subscribersMap.getOrDefault(eventType, Collections.emptyList());
        return Collections.unmodifiableList(subscribers);
    }
}