import java.util.concurrent.ConcurrentLinkedQueue;

public class HW3_carlb15
{
    private static final int N            = 2;
    private static final int THREAD_COUNT = 4;


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param args
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void main(String[] args)
        throws InstantiationException,
        IllegalAccessException,
        NoSuchMethodException,
        SecurityException
    {
        // TODO Sub-Queue Type, Number of Sub-Queues, Number of Threads

        final N_Super_Queue<Integer> superQueue =
            new N_Super_Queue<Integer>(ConcurrentLinkedQueue.class, N);

        for (int i = 0; i < THREAD_COUNT; i++)
        {
            new TestThread(superQueue, THREAD_COUNT).start();
        }
    }
}
