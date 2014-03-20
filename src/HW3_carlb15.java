import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

// -------------------------------------------------------------------------
/**
 * @author carlbarbee
 * @version Mar 20, 2014
 */
public class HW3_carlb15
{
    private static int          N, THREAD_COUNT;
    private static final String lockBasedQueue        = "LOCK_BASED";
    private static final String concurrentLinkedQueue = "CONCURRENT_LINKED";
    private static int          numberOfOperations    = 0;
    private static TestThread[] testThreads;


    // ----------------------------------------------------------
    /**
     * N-super-queue consists of N linearizable queues. Two types of
     * N-super-queue are implemented: The sub-queue is a regular sequential
     * queue, protected against concurrent access by using a lock. Uses a native
     * lock synchronized blocks/methods. The sub-queue is a native concurrent
     * queue (ConcurrentLinkedQueue). Measures the throughput (number of
     * operations enqueue/dequeue per second) of each implementation and
     * includes an excel sheet comparing the throughput as a function of the
     * number of threads.
     *
     * @param args
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws InterruptedException
     */
    public static void main(String[] args)
        throws InstantiationException,
        IllegalAccessException,
        NoSuchMethodException,
        SecurityException,
        IllegalArgumentException,
        InvocationTargetException,
        InterruptedException
    {
        if (args.length == 3 && args[0].equals(lockBasedQueue)
            && parseInt(args[1]) != null && parseInt(args[2]) != null)
        {
            final N_Super_Queue<Integer> superQueue;
            N = parseInt(args[1]);
            THREAD_COUNT = parseInt(args[2]);
            testThreads = new TestThread[THREAD_COUNT];
            superQueue = new N_Super_Queue<Integer>(LockBasedQueue.class, N);
            Random rand = new Random();

            // Enqueue a large amount of items in the sub-queues.
            for (int i = 0; i < 25000* N; i++)
            {
                int value = rand.nextInt(N);
                superQueue.enqueue(value);
            }

            // Run each of the threads.
            for (int i = 0; i < THREAD_COUNT; i++)
            {
                testThreads[i] = new TestThread(superQueue, THREAD_COUNT);
                testThreads[i].start();
            }

            // Wait for the threads to finish.
            for (int i = 0; i < THREAD_COUNT; i++)
            {
                testThreads[i].join();
                numberOfOperations += testThreads[i].getCounter();
            }

            System.out.println("Number of Operations/Duration of Measurement: "
                + numberOfOperations / 2);
        }

        else if (args.length == 3 && args[0].equals(concurrentLinkedQueue)
            && parseInt(args[1]) != null && parseInt(args[2]) != null)
        {
            final N_Super_Queue<Integer> superQueue;
            N = parseInt(args[1]);
            THREAD_COUNT = parseInt(args[2]);
            testThreads = new TestThread[THREAD_COUNT];
            superQueue =
                new N_Super_Queue<Integer>(ConcurrentLinkedQueue.class, N);
            Random rand = new Random();

            // Enqueue a large amount of items in the sub-queues.
            for (int i = 0; i < 1000 * N; i++)
            {
                int value = rand.nextInt(N);
                superQueue.enqueue(value);
            }

            // Run each of the threads.
            for (int i = 0; i < THREAD_COUNT; i++)
            {
                testThreads[i] = new TestThread(superQueue, THREAD_COUNT);
                testThreads[i].start();
            }

            // Wait for the threads to finish.
            for (int i = 0; i < THREAD_COUNT; i++)
            {
                testThreads[i].join();
                numberOfOperations += testThreads[i].getCounter();
            }

            System.out.println("Number of Operations/Duration of Measurement: "
                + numberOfOperations / 2);
        }
        else
        {
            System.out.println("Incorrect CLI Arguments:");
            System.out.println("Correct Arguments: " + lockBasedQueue
                + " Number of Threads Number of Sub-queues");
            System.out.println("or " + concurrentLinkedQueue
                + " Number of Threads Number of Sub-queues");
            System.out.println("where the numbers are greater than 0.");
        }

    }


    // ----------------------------------------------------------
    /**
     * Attempts to convert the string to an integer.
     *
     * @param text
     *            The text to convert.
     * @return text the string input to parse.
     */
    public static Integer parseInt(String text)
    {
        try
        {
            return new Integer(text);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}
