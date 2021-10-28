package hk.ust.cse.comp3021.lab8;

import java.time.Duration;
import java.util.*;

interface EventEmitter {
    class Event extends java.util.EventObject {
        /**
         * Constructs a prototypical Event.
         *
         * @param source the object on which the Event initially occurred
         * @throws IllegalArgumentException if source is null
         */
        public Event(Object source) {
            super(source);
        }
    }

    @FunctionalInterface
    interface Listener extends java.util.EventListener {
        void handle(Event event);
    }

    /**
     * Register a new listener
     *
     * @param listener the event listener
     */
    default void addListener(Listener listener) {
        throw new RuntimeException("implement me");
    }

    /**
     * Unregister a listener
     *
     * @param listener event listener
     */
    default void removeListener(Listener listener) {
        throw new RuntimeException("implement me");
    }

    /**
     * Emit the {@link Event} to every registered listeners
     *
     * @param event the event to emit
     */
    default void emitEvent(Event event) {
        throw new RuntimeException("implement me");
    }
}

interface TimeTicker extends EventEmitter {
    /**
     * Start time ticking, emit {@link Event} immediately and then periodically emit events with the given time interval.
     * That is to say, if the interval is 1 second,
     * the first event is emitted at 0-th second,
     * second event is emitted at 1-th second, etc.
     * The emitted {@link Event} should use the current time ({@link Date} object) as the value of {@link Event#getSource()}.
     *
     * @param interval the time interval to emit events periodically.
     */
    default void startTick(Duration interval) {
        throw new RuntimeException("implement me");
    }

    /**
     * Stop the time ticking that is currently working.
     * If no ticker is working, do nothing.
     */
    default void stopTick() {
        throw new RuntimeException("implement me");
    }
}

/**
 * Lab8Service support event emitting and time ticking.
 */
public class Lab8Service implements TimeTicker {
    // TODO implement this class
}
