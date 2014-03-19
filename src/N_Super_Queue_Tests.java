import static org.junit.Assert.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.Test;

public class N_Super_Queue_Tests
{

    @Test
    public <T> void N_Super_Queue_Test()
        throws InstantiationException,
        IllegalAccessException,
        NoSuchMethodException,
        SecurityException,
        IllegalArgumentException,
        InvocationTargetException
    {
        int size = 10;
        N_Super_Queue<Integer> nSuperQueue_sequential =
            new N_Super_Queue<Integer>(LockBasedQueue.class, size);

        N_Super_Queue<Integer> nSuperQueue_native =
            new N_Super_Queue<Integer>(ConcurrentLinkedQueue.class, size);

        int val = 10;
        int val2 = 12;

        for (int i = 0; i < 20; i++)
        {
            nSuperQueue_sequential.enqueue(i);
        }

        nSuperQueue_native.enqueue(val2);
    }

}
