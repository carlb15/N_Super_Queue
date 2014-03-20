import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// -------------------------------------------------------------------------
/**
 * LockBasedQueue class for the N Super Queue.
 *
 * @param <U>
 * @author Carl Barbee
 * @version Mar 16, 2014
 */
class LockBasedQueue<V>
    implements Queue<V>
{
    int  head, tail;
    V[]  items;
    Lock lock;


    // ----------------------------------------------------------
    /**
     * Constructor for LockBaseQueue
     *
     * @param capacity
     */
    @SuppressWarnings("unchecked")
    public LockBasedQueue()
    {
        head = 0;
        tail = 0;
        lock = new ReentrantLock();
        items = (V[])new Object[50000];
    }


    // ----------------------------------------------------------
    /**
     * Enqueues an item into a queue.
     *
     * @param x
     */
    public synchronized boolean add(V x)
    {
        lock.lock();
        try
        {
            if (tail - head == items.length)
            {
                throw new FullException();
            }
            items[tail % items.length] = x;
            tail++;
            return true;
        }
        catch (FullException ex)
        {
            return false;
        }
        finally
        {
            lock.unlock();
        }
    }


    // ----------------------------------------------------------
    /**
     * Dequeues an item from the queue.
     */
    public synchronized V remove()
    {
        lock.lock();
        try
        {
            if (tail == head)
            {
                throw new EmptyException();
            }
            V x = items[head % items.length];
            head++;
            return x;
        }
        catch (EmptyException ex)
        {
            return null;
        }
        finally
        {
            lock.unlock();
        }
    }


    /**
     * Unused Methods Below.
     */

    @Override
    public int size()
    {
        return 0;
    }


    @Override
    public boolean isEmpty()
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean contains(Object o)
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public Iterator<V> iterator()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Object[] toArray()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public <T> T[] toArray(T[] a)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public boolean remove(Object o)
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean containsAll(Collection<?> c)
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean addAll(Collection<? extends V> c)
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean removeAll(Collection<?> c)
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean retainAll(Collection<?> c)
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public void clear()
    {
        // TODO Auto-generated method stub

    }


    @Override
    public boolean offer(V e)
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public V poll()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public V element()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public V peek()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
