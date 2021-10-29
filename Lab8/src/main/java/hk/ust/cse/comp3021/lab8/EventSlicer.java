package hk.ust.cse.comp3021.lab8;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * EventSlice listens to events emitted by the provided {@link EventEmitter} and record first n events.
 */
public class EventSlicer {
    /**
     * the service to record events
     */
    Lab8Service service;

    /**
     * the number of first events to record
     */
    int n;

    /**
     * The list of events recorded from service
     */
    List<EventEmitter.Event> recordedEvents = new ArrayList<>();

    /**
     * @param service the service to record events
     * @param n       the number of first events to record
     */
    EventSlicer(EventEmitter service, int n) {
        if (!(service instanceof Lab8Service)) {
            throw new IllegalArgumentException("service must be a Lab8Service!");
        }
        this.service = (Lab8Service) service;
        this.n = n;
        service.addListener(e -> recordedEvents.add(e));
    }

    /**
     * Get the first n events recorded by EventSlicer.
     * If there are more than n events emitted by the {@link EventSlicer#service}, return the first n events.
     * If there are no sufficient events, i.e., total number of events by not is smaller than n, then return all of them.
     *
     * @return a list of {@link EventEmitter.Event} with length at most {@link EventSlicer#n}
     */
    public List<EventEmitter.Event> firstNEventsNonBlock() {
        if (recordedEvents.size() < n) {
            return recordedEvents;
        } else {
            ArrayList<EventEmitter.Event> temp = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                temp.add(recordedEvents.get(i));
            }
            return temp;
        }
    }

    /**
     * IMPORTANT: This method is optional to implement since it requires asynchronous programming skills.
     * Not implementing this method does not affect your grade in this lab.
     * <p>
     * Get the first n events recorded by EventSlicer.
     * If there are more than n events emitted by the {@link EventSlicer#service}, return the first n events.
     * If there are no sufficient events, <strong>block the execution here</strong> until the number of events is sufficient.
     * <p>
     * HINT: You can use {@link java.util.concurrent.CompletableFuture} to implement.
     * <p>
     * TEST: There is a test case called testGetFirstNEventsWithBlock() that is meant to test this method.
     * The test case is by default disabled, so it will not be included in the grading scheme.
     * You can remove the @Disable tag to enable the test case if you like.
     *
     * @return a list of {@link EventEmitter.Event} with length {@link EventSlicer#n}
     */
    public List<EventEmitter.Event> firstNEventsBlock() {
        if (recordedEvents.size() < n) {
            var future = new CompletableFuture<Boolean>();
            while (true) {
                Thread.yield();
                if (recordedEvents.size() >= n) {
                    future.complete(true);
                    break;
                }
            }
        }
        // there must be at least n events upon running here.
        ArrayList<EventEmitter.Event> temp = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            temp.add(recordedEvents.get(i));
        }
        return temp;
    }
}