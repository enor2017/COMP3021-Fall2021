package hk.ust.cse.comp3021.lab10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


public class Lab10Tests {
    @Test
    @Timeout(1)
    public void testTwoThreads() throws InterruptedException {
        var segments = new String[]{"hlowrd", "el ol!"};
        var result = new StringBuilder();
        var threads = new ArrayList<Thread>();
        var lock = new ReentrantLock();
        ParallelMerger.merge(segments, ch -> {
            lock.lock();
            var threadIdx = result.length() % segments.length;
            result.append(ch);
            var thread = Thread.currentThread();
            if (!threads.contains(thread)) {
                threads.add(thread);
            }
            // check whether threads take turns to write
            assertEquals(threads.get(threadIdx), thread);
            lock.unlock();
        });
        for (var thread :
                threads) {
            // check whether all threads have exited after merge() returns
            assertEquals(Thread.State.TERMINATED, thread.getState());
        }
        // check whether number of threads is the same as number of segments to merge.
        assertEquals(2, threads.size());
        // check merge result
        assertEquals("hello world!", result.toString());
    }

    @Test
    @Timeout(1)
    public void testThreeThreads() throws InterruptedException {
        var segments = new String[]{"hlwl", "eood", "l r!"};
        var result = new StringBuilder();
        var threads = new ArrayList<Thread>();
        var lock = new ReentrantLock();
        ParallelMerger.merge(segments, ch -> {
            lock.lock();
            var threadIdx = result.length() % segments.length;
            result.append(ch);
            var thread = Thread.currentThread();
            if (!threads.contains(thread)) {
                threads.add(thread);
            }
            // check whether threads take turns to write
            assertEquals(threads.get(threadIdx), thread);
            lock.unlock();
        });
        for (var thread :
                threads) {
            // check whether all threads have exited after merge() returns
            assertEquals(Thread.State.TERMINATED, thread.getState());
        }
        // check whether number of threads is the same as number of segments to merge.
        assertEquals(3, threads.size());
        // check merge result
        assertEquals("hello world!", result.toString());
    }
}
