package exceptions;

public class StringQueueEmptyException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 639960451368201703L;

    public StringQueueEmptyException(String s)
    {
        super(s);
    }
}
