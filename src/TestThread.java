import java.util.NoSuchElementException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * @author Mohamed M. Saad
 */
public class TestThread
    extends Thread
{
    private static int             ID_GEN        = 0;
    private static final int       MAX_COUNT     = 1000;

    private N_Super_Queue<Integer> n_super_queue;
    private static int             MAXTHREADCOUNT;
    private static int             counter       = 0;
    private static int             threadCounter = 0;

    private int                    id;
    Random                         chooseMethod  = new Random();
    Random                         item          = new Random();


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
        threadCounter++;

        long startTime = System.nanoTime();

        while (System.nanoTime() - startTime < 5e9)
            ;

        startTime = System.nanoTime();

        while (System.nanoTime() - startTime < 2e9)
        {
            for (int i = 0; i < 1000; i++)
            {

                int methodChosen = chooseMethod.nextInt(1);
                int itemToAdd = item.nextInt(100);

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
                        //e.printStackTrace();
                    }
                }
                counter++;
            }
        }

        System.out.println("Number of Operations/Duration of Measurement: "
            + counter / 2);

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
