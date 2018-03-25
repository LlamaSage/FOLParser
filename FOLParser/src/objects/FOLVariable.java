package objects;

import exceptions.ForbiddenKeywordException;
import parser.FOLMatchEnum;

public class FOLVariable extends FOLElement
{

    public FOLVariable(String name)
    {
        super(name);
    }

    public static FOLVariable parse(String s) throws ForbiddenKeywordException
    {
        String[] split = s.split(" ");
        if (split[2].equals("lub") || split[2].equals("nie"))
        {
            throw new ForbiddenKeywordException("Forbidden keyword \"" + split[2] + "\"");
        }
        return new FOLVariable(split[2]);
    }

    @Override
    public FOLMatchEnum compareTo(FOLElement e)
    {
        if (e instanceof FOLVariable)
        {
            if (this == e)
                return FOLMatchEnum.FullMatch;
        }
        return FOLMatchEnum.PartialMatch;
    }
    
    @Override
    public FOLMatchEnum strictCompareTo(FOLElement e)
    {
        if (e instanceof FOLVariable)
        {
            if (this == e)
                return FOLMatchEnum.FullMatch;
        }
        return FOLMatchEnum.NoMatch;
    }

    public String toString()
    {
        return "Variable: "+super.toString();
    }


}
