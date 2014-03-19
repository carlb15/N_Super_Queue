import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Queue;
import java.util.Random;

// -------------------------------------------------------------------------
/**
 * THe n-super-queue for the
 *
 * @author Carl Barbee
 * @version Mar 16, 2014
 */
public class N_Super_Queue<E>
{
    Queue[] superQueue;
    int     size;
    Random  rand = new Random();


    @SuppressWarnings("unchecked")
    public N_Super_Queue(Class c, int s)
        throws InstantiationException,
        IllegalAccessException
    {
        final Queue[] superQueue = (Queue[])Array.newInstance(c, s);
        this.superQueue = superQueue;
        this.size = s;

        for (int i = 0; i < size; i++)
        {
            superQueue[i] = (Queue)c.newInstance();
        }
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param item
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void enqueue(E item)
        throws NoSuchMethodException,
        SecurityException,
        IllegalAccessException,
        IllegalArgumentException,
        InvocationTargetException,
        InstantiationException
    {
        int value = rand.nextInt(size);
        superQueue[value].add(item);
    }


    public void dequeue()
    {
        int value = rand.nextInt(size);
        superQueue[value].remove();
    }
}
