// -------------------------------------------------------------------------
/**
 * The full exception class for handling an empty queue.
 *
 * @author Carl Barbee
 * @version Mar 16, 2014
 */
public class FullException
    extends Exception
{
    // ----------------------------------------------------------
    /**
     * Create a new FullException object.
     */
    public FullException()
    {
    }


    // ----------------------------------------------------------
    /**
     * Create a new FullException object.
     *
     * @param message
     */
    public FullException(String message)
    {
        super(message);
    }
}
