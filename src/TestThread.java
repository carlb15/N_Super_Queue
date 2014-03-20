import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @author Carl Barbee
 */
public class TestThread
    extends Thread
{
    private static int             ID_GEN    = 0;
    private static final int       MAX_COUNT = 1000;

    private N_Super_Queue<Integer> n_super_queue;
    private static int             MAXTHREADCOUNT;
    private int                    counter   = 0;
    private int                    id;
    private Random                 random    = new Random();


    // ----------------------------------------------------------
    /**
     * Create a new TestThread object.
     *
     * @param counter
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public TestThread(N_Super_Queue superQueue, int threadCount)
        throws InstantiationException,
        IllegalAccessException
    {
        this.n_super_queue = superQueue;
        this.MAXTHREADCOUNT = threadCount;
    }


    @Override
    public void run()
    {
        long startTime = System.currentTimeMillis();
        int methodChosen, itemToAdd;

        while (System.currentTimeMillis() - startTime < 5000)
            ;

        startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < 2000)
        {
            for (int i = 0; i < 1000; i++)
            {
                methodChosen = random.nextInt(2);
                itemToAdd = random.nextInt(100);

                if (methodChosen == 1)
                {
                    try
                    {
                        n_super_queue.enqueue(itemToAdd);
                    }
                    catch (NoSuchMethodException | SecurityException
                        | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | InstantiationException e)
                    {
                        e.printStackTrace();
                    }
                }
                else if (methodChosen == 0)
                {
                    try
                    {
                        n_super_queue.dequeue();
                    }
                    catch (NoSuchElementException e)
                    {
                        return;
                    }
                }

                counter++;
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Get the counter for the thread.
     *
     * @return counter the number of operations performed by the thread.
     */
    public int getCounter()
    {
        return counter;
    }


    // ----------------------------------------------------------
    /**
     * Gets the thread id
     *
     * @return threads id
     */
    public int getThreadId()
    {
        return id;
    }
}
