package objects;

import java.util.HashMap;

import exceptions.ForbiddenKeywordException;
import parser.FOLMatchEnum;

public class FOLObject extends FOLElement
{

    public FOLObject(String name)
    {
        super(name);
    }

    public static FOLObject parse(String s) throws ForbiddenKeywordException
    {
        String[] split = s.split(" ");
        if (split[2].equals("lub") || split[2].equals("nie"))
        {
            throw new ForbiddenKeywordException("Forbidden keyword \"" + split[2] + "\"");
        }
        return new FOLObject(split[2]);
    }

    @Override
    public FOLMatchEnum compareTo(FOLElement e, HashMap<FOLElement, FOLElement> subs)
    {
        if(e instanceof FOLVariable)
        {
            if(subs.containsKey(e))
            {
                if(subs.get(e).strictCompareTo(this, subs) == FOLMatchEnum.FullMatch)
                {
                    return FOLMatchEnum.PartialMatch;
                }
                else
                    return FOLMatchEnum.NoMatch;
            }
            else
            {
                subs.put(e, this);
                return FOLMatchEnum.PartialMatch;
            }
              
        }
        if (this == e)
            return FOLMatchEnum.FullMatch;
        return FOLMatchEnum.NoMatch;
    }
    
    @Override
    public FOLMatchEnum strictCompareTo(FOLElement e, HashMap<FOLElement, FOLElement> subs)
    {
        if (this == e)
            return FOLMatchEnum.FullMatch;
        return FOLMatchEnum.NoMatch;
    }

    public String toString()
    {
        return "Object: "+super.toString();
    }
}
