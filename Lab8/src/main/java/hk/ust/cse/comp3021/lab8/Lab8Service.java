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
     * A list of listeners of current event
     */
    List<Listener> listeners = new ArrayList<>();

    /**
     * Register a new listener
     *
     * @param listener the event listener
     */
    default void addListener(Listener listener) {
        listeners.add(listener);
    }

    /**
     * Unregister a listener
     *
     * @param listener event listener
     */
    default void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    /**
     * Emit the {@link Event} to every registered listeners
     *
     * @param event the event to emit
     */
    default void emitEvent(Event event) {
        for (var listener : listeners) {
            listener.handle(event);
        }
    }
}

interface TimeTicker extends EventEmitter {
    List<Timer> timers = new ArrayList<>();
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
        var timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                emitEvent(new Event(new Date()));
            }
        }, 0, interval.toMillis());
        timers.add(timer);
    }

    /**
     * Stop the time ticking that is currently working.
     * If no ticker is working, do nothing.
     */
    default void stopTick() {
        for (var timer : timers) {
            timer.cancel();
        }
        timers.clear();
    }
}

/**
 * Lab8Service support event emitting and time ticking.
 */
public class Lab8Service implements TimeTicker {
    // TODO implement this class
}
