package exceptions;

public class InvalidInputException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 3927129673595804326L;

    public InvalidInputException()
    {

    }

    public InvalidInputException(String s)
    {
        super(s);
    }
}
