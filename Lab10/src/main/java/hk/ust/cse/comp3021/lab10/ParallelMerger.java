package hk.ust.cse.comp3021.lab10;

public class ParallelMerger {
    @FunctionalInterface
    public interface ThreadSafeCharacterWriter {
        void write(char ch);
    }

    /**
     * The worker class which is {@link Runnable}.
     * All worker objects will work together to merge their own char array and put results in the provided instance of {@link ThreadSafeCharacterWriter}.
     * <p>
     * The merge rule is at follows:
     * <p>
     * 1. each worker thread take turns to write one char into the {@link ThreadSafeCharacterWriter}
     * <p>
     * 2. For example if we have two workers, worker0, worker1, with char array "hlo" and "el!" respectively.
     * Then after the merge, {@link ThreadSafeCharacterWriter} should contain: "hello!".
     * 'h' should be written by the first worker, 'e' should be written by the second worker and 'l' should be written by the first worker, so on and so forth.
     * <p>
     * TODO complete this class to implement the above functionality.
     * This class should implement {@link Runnable} so that it can be run in a thread.
     */
    private static class Worker {

    }

    /**
     * TODO This method does the merge in an array of string segments. Here is the desired procedure:
     * <ol>
     *     <li>Create workers and their threads, the number of threads should be the same as the length of array {@code segments}.</li>
     *     <li>Start all threads to merge the {@code segments} in parallel.</li>
     *     <li>Wait all threads to finish process and exit.</li>
     * </ol>
     *
     * @param segments     An array of strings that are to be merged. Each segment should be corresponding to one thread.
     * @param resultWriter A {@link ThreadSafeCharacterWriter} instance that the workers should write the results to.
     *                     The {@code resultWriter} should be written with the characters of merged string.
     *                     For example, suppose the merge result should be "hello", then the {@code resultWriter}
     *                     should be called 5 times with each character in the order.
     */
    public static void merge(String[] segments, ThreadSafeCharacterWriter resultWriter) throws InterruptedException {

    }
}