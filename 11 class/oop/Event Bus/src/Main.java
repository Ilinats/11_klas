import events.*;
import subscribers.*;
import exception.MissingSubscriptionException;

import java.time.Instant;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        EventBus eventBus = new EventBusImpl();

        DeferredEventSubscriber<SimpleEvent> deferredSubscriber = new DeferredEventSubscriber<>();
        System.out.print("Subscribing for events...");
        eventBus.subscribe(SimpleEvent.class, deferredSubscriber);
        System.out.println("Successful");

        SimpleEvent event1 = new SimpleEvent(1, "Source1", "EXPECTED EVENT 1!");
        SimpleEvent event2 = new SimpleEvent(2, "Source1", "EXPECTED EVENT 2!");
        SimpleEvent event2a = new SimpleEvent(2, "Source1", "EXPECTED EVENT 2a!");
        SimpleEvent event3 = new SimpleEvent(3, "Source1", "EXPECTED EVENT 3!");
        AnotherEvent eventx = new AnotherEvent(1, "Source1", "SHOULD NOT ARRIVE!");

        System.out.println("Queueing events...");
        eventBus.publish(event2a);
        eventBus.publish(event3);
        eventBus.publish(event1);
        eventBus.publish(event2);
        eventBus.publish(eventx);


        System.out.println("Are there received events in Queue? " + !deferredSubscriber.isEmpty());
        System.out.println("Processing events from subscriber...");
        Iterator<? extends Event<?>> iterator = deferredSubscriber.iterator();
        while (iterator.hasNext()) {
            Event<?> e = iterator.next();
            System.out.println("Processing event from source: " + e.getSource());
            System.out.println("Event priority: " + e.getPriority());
            System.out.println("Event timestamp: " + e.getTimestamp());
            System.out.println("Event payload: " + e.getPayload().getPayload());
            iterator.remove();
        }

        System.out.println("Is subscriber's event queue empty after processing? " + deferredSubscriber.isEmpty());

        try {
            // Unsubscribe from events
            System.out.print("Unsubscribing from events...");
            eventBus.unsubscribe(SimpleEvent.class, deferredSubscriber);
            System.out.println("Successful.");
        } catch (MissingSubscriptionException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Publish another event after unsubscribe
        System.out.println("Publishing another event after unsubscribe...");
        eventBus.publish(event1);

        System.out.println("Is subscriber's event queue empty after unsubscribing? " + deferredSubscriber.isEmpty());
    }
}