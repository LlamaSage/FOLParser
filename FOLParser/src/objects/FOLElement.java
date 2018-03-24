package objects;

import java.util.TreeMap;

import parser.FOLMatchEnum;

public abstract class FOLElement 
{
    public String name;

    public FOLElement(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this.getClass() != o.getClass() || ! (this.name.equals(((FOLElement) o).name)))
            return false;
        return true;
    }
    
    public String toString()
    {
        return this.name;
    }
    
    //TODO: Not done yet, probably doesn't work yet
    public void substitute(FOLTerm term, TreeMap<FOLElement, FOLElement> subs) throws Exception //TODO: Check
    {
        if (this instanceof FOLVariable)
        {
            FOLElement valueInMap = subs.get(this);

            if (valueInMap != null)
            {
                if (valueInMap instanceof FOLObject || valueInMap instanceof FOLVariable)
                    term.element = valueInMap;
                else if (valueInMap instanceof FOLFunction)
                {
                    term.element = new FOLFunction(valueInMap.name, ((FOLFunction) valueInMap).prototype, ((FOLFunction) valueInMap).copyArguments());
                }
            }
        }
        else if(this instanceof FOLFunction)
        {
            FOLFunction func = (FOLFunction) this;
        for (int i = 0; i < func.arguments.length; i++)
        {
            if (func.arguments[i] instanceof FOLVariable)
            {
                FOLElement valueInMap = subs.get(func.arguments[i]);
                if (valueInMap != null)
                    if (valueInMap instanceof FOLObject || valueInMap instanceof FOLVariable)
                    {
                        func.arguments[i] = subs.get(func.arguments[i]);
                    }
                    else if (valueInMap instanceof FOLFunction)
                    {
                        func.arguments[i] = new FOLFunction(subs.get(func.arguments[i]).name, ((FOLFunction) subs.get(func.arguments[i])).prototype, ((FOLFunction) subs.get(func.arguments[i])).copyArguments());
                    }
            }
            else if (func.arguments[i] instanceof FOLFunction)
            {
                ((FOLFunction) func.arguments[i]).substituteFunction(subs);
            }
        }
        }

    }
    
    public abstract FOLMatchEnum compareTo(FOLElement e);

}
