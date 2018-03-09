package exceptions;

public class InvalidOverwriteException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 3866616990742280021L;

    public InvalidOverwriteException()
    {
        
    }
    
    public InvalidOverwriteException(String s)
    {
       super(s);
    }
}
