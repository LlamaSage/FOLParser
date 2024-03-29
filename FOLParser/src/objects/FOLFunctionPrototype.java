package objects;

import java.util.HashMap;

import exceptions.ForbiddenKeywordException;
import parser.FOLMatchEnum;

public class FOLFunctionPrototype extends FOLElement
{
    public int number;

    public FOLFunctionPrototype(String name, int number)
    {
        super(name);
        this.number = number;
    }

    public static FOLFunctionPrototype parse(String s) throws ForbiddenKeywordException
    {
        String[] split = s.split(" ");
        if (split[2].equals("lub") || split[2].equals("nie"))
        {
            throw new ForbiddenKeywordException("Forbidden keyword \"" + split[2] + "\"");
        }
        int parsedNumber = Integer.parseInt(split[3]);
        if (parsedNumber <= 0)
        {
            throw new IllegalArgumentException("Illegal number value \"" + split[3] + "\"");
        }
        return new FOLFunctionPrototype(split[2], parsedNumber);
    }

    @Override
    public FOLMatchEnum compareTo(FOLElement e, HashMap<FOLElement, FOLElement> subs)
    {
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
    
    @Override
    public boolean equals(Object o)
    {
        if(super.equals(o) && this.number == ((FOLFunctionPrototype) o).number)
            return true;
        return false;
    }
    
    public String toString()
    {
        return "Function Prototype: "+super.toString()+" ["+this.number+"]";
    }



}
