// -------------------------------------------------------------------------
/**
 * The empty exception class for handling an empty queue.
 *
 * @author Carl Barbee
 * @version Mar 16, 2014
 */
public class EmptyException
    extends Exception
{
    // ----------------------------------------------------------
    /**
     * Create a new EmptyException object.
     */
    public EmptyException()
    {
    }


    // ----------------------------------------------------------
    /**
     * Create a new EmptyException object.
     *
     * @param message
     */
    public EmptyException(String message)
    {
        super(message);
    }
}
