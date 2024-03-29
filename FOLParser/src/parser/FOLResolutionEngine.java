package parser;

import java.util.LinkedList;
import objects.FOLTerm;

public class FOLResolutionEngine
{
    public LinkedList<FOLTerm> memory = new LinkedList<FOLTerm>();
    
    public FOLMatchEnum inMemory(FOLTerm toCheck) //TODO: Throw exception for empty list!
    {
        FOLMatchEnum result = FOLMatchEnum.FullMatch;
        for (int i = 0; i<memory.size(); i++)
        {
            if(toCheck.strictCompareTo(memory.get(i), null) == FOLMatchEnum.NoMatch) //TODO: Check this, I added the null part
            {
                result = FOLMatchEnum.NoMatch;
                break;
            }
        }
        return result;
    }
}
