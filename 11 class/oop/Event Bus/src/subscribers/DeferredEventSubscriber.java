package subscribers;

import events.Event;

import java.time.Instant;
import java.util.*;

/**
 * A subscriber that defers the processing of events.
 */
public class DeferredEventSubscriber<T extends Event<?>> implements Subscriber<T>, Iterable<T> {

    private final PriorityQueue<T> events;

    public DeferredEventSubscriber() {
        this.events = new PriorityQueue<>(new Comparator<T>() {
            @Override
            public int compare(T e1, T e2) {
                int priorityComparison = Integer.compare(e2.getPriority(), e1.getPriority());
                if (priorityComparison != 0) {
                    return - priorityComparison;
                }
                return e1.getTimestamp().compareTo(e2.getTimestamp());
            }
        });
    }

    /**
     * Stores an event for later processing.
     *
     * @param event the event to be processed
     * @throws IllegalArgumentException if the event is null
     */
    @Override
    public void onEvent(T event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        events.add(event);
    }

    /**
     * Provides an iterator over unprocessed events, sorted by priority and timestamp.
     *
     * @return an iterator over unprocessed events
     */
    @Override
    public Iterator<T> iterator() {
        return events.iterator();
    }

    /**
     * Checks if there are unprocessed events.
     *
     * @return true if there are no unprocessed events; false otherwise
     */
    public boolean isEmpty() {
        return events.isEmpty();
    }
}
