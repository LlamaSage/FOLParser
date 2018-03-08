package exceptions;

public class ForbiddenKeywordException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = -3155140867025121075L;

    public ForbiddenKeywordException()
    {
        
    }
    
    public ForbiddenKeywordException(String s)
    {
       super(s);
    }
}
