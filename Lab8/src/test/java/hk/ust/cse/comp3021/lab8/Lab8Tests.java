package hk.ust.cse.comp3021.lab8;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lab8Tests {
    @Test
    @Timeout(1)
    public void testAddListener() throws InterruptedException {
        var future = new CompletableFuture<EventEmitter.Event>();
        var service = new Lab8Service();
        service.addListener(future::complete);
        Thread.sleep(500);
        assertNull(future.getNow(null));
    }

    @Test
    @Timeout(1)
    public void testAddListenerAndEmitEvent() throws ExecutionException, InterruptedException {
        var future = new CompletableFuture<EventEmitter.Event>();
        var service = new Lab8Service();
        var event = new EventEmitter.Event(0);
        service.addListener(future::complete);
        service.emitEvent(event);
        assertEquals(0, future.get().getSource());
    }

    @Test
    @Timeout(1)
    public void testRemoveListenerAndEmitEvent() {
        var service = new Lab8Service();

        var future = new CompletableFuture<EventEmitter.Event>();
        var event = new EventEmitter.Event(0);
        EventEmitter.Listener listener1 = future::complete;
        service.addListener(listener1);
        service.removeListener(listener1);
        service.emitEvent(event);
        assertFalse(future.isDone());
    }

    @Test
    @Timeout(1)
    public void testMultipleListeners() throws ExecutionException, InterruptedException {
        var service = new Lab8Service();

        var future1 = new CompletableFuture<EventEmitter.Event>();
        EventEmitter.Listener listener1 = future1::complete;
        service.addListener(listener1);

        var future2 = new CompletableFuture<EventEmitter.Event>();
        EventEmitter.Listener listener2 = future2::complete;
        service.addListener(listener2);

        var future3 = new CompletableFuture<EventEmitter.Event>();
        EventEmitter.Listener listener3 = future3::complete;
        service.addListener(listener3);

        var event = new EventEmitter.Event(1);
        service.emitEvent(event);

        assertEquals(1, future1.get().getSource());
        assertEquals(1, future2.get().getSource());
        assertEquals(1, future3.get().getSource());
    }

    @Test
    @Timeout(1)
    public void testMultipleEvents() {
        var service = new Lab8Service();

        var invokeCount = new AtomicInteger();
        EventEmitter.Listener listener = ev -> invokeCount.incrementAndGet();
        service.addListener(listener);
        var event1 = new EventEmitter.Event(0);
        service.emitEvent(event1);
        var event2 = new EventEmitter.Event(0);
        service.emitEvent(event2);
        assertEquals(2, invokeCount.get());
    }

    @Test
    @Timeout(10)
    public void testTimeTicking() throws ExecutionException, InterruptedException {
        testTimeTicking(3, Duration.ofMillis(500));
        testTimeTicking(5, Duration.ofMillis(300));
        testTimeTicking(10, Duration.ofMillis(200));
    }

    @Test
    @Timeout(1)
    public void testGetFirstNEvents() {
        testGetFirstNEvents(1);
        testGetFirstNEvents(100);
        testGetFirstNEvents(51);
    }

    @Test
    @Timeout(5)
    @Disabled
    // This test is by default disable because the method under test is optional in this lab.
    public void testGetFirstNEventsWithBlock() {
        testGetFirstNEventsWithBlock(2);
        testGetFirstNEventsWithBlock(10);
    }

    private void testTimeTicking(int n, Duration interval) throws ExecutionException, InterruptedException {
        var future = new CompletableFuture<Boolean>();
        var service = new Lab8Service();

        List<EventEmitter.Event> eventList = new ArrayList<>();
        service.addListener(eventList::add);

        service.startTick(interval);
        // stop tick after ((n-1)*interval) seconds. The service should be able to tick n times.
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                service.stopTick();

                // wait for more time in case stopTick() is not working
                try {
                    Thread.sleep(2 * interval.toMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                future.complete(true);
            }
        }, (long) ((n - 1 + 0.5) * interval.toMillis()));

        assertTrue(future.get());
        checkEventList(eventList, n, interval);
    }

    private void testGetFirstNEvents(int n) {
        var service = new Lab8Service();
        var slicer = new EventSlicer(service, n);
        for (int i = 0; i < n * 2; i++) {
            var event = new EventEmitter.Event(i);
            service.emitEvent(event);
            var slice = slicer.firstNEventsNonBlock();
            if (i < n) {
                assertEquals(i + 1, slice.size());
            } else {
                assertEquals(n, slice.size());
            }
        }
    }

    private void testGetFirstNEventsWithBlock(int n) {
        var service = new Lab8Service();
        var slicer = new EventSlicer(service, n);
        var i = new AtomicInteger();
        var timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                var event = new EventEmitter.Event(i.getAndIncrement());
                service.emitEvent(event);
            }
        }, 100, 100);
        var slice = slicer.firstNEventsBlock();
        timer.cancel();
        assertEquals(n, slice.size());
    }

    private void checkEventList(List<EventEmitter.Event> eventList, int n, Duration interval) {
        // check if there is exactly n events emitted
        assertEquals(n, eventList.size());

        // test the interval is roughly interval second
        var times = eventList.stream()
                .map(eventObject -> (Date) eventObject.getSource())
                .collect(Collectors.toList());
        for (int i = 1; i < times.size(); i++) {
            assertEquals(1, Math.round(((double) times.get(i).getTime() - times.get(i - 1).getTime()) / interval.toMillis()));
        }
    }
}
