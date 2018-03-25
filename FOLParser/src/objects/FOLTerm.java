package objects;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

import exceptions.CouldNotFindDefinitionException;
import exceptions.InvalidInputException;
import exceptions.InvalidCopyException;
import exceptions.StringQueueEmptyException;
import parser.FOLMatchEnum;

public class FOLTerm
{
    public FOLElement element; // In practice you don't really expect it to be a
                               // variable, but it can be either an object or a
                               // function.

    public boolean notInverted;

    public FOLTerm(FOLTerm toCopy) throws InvalidCopyException
    {
        this.notInverted = toCopy.notInverted;
        if (toCopy.element instanceof FOLObject)
        {
            this.element = toCopy.element;
        }
        else if (toCopy.element instanceof FOLVariable)
        {
            this.element = toCopy.element;
        }
        else if (toCopy.element instanceof FOLFunction)
        {
            this.element = new FOLFunction(toCopy.element.name, ((FOLFunction) toCopy.element).prototype, ((FOLFunction) toCopy.element).copyArguments());
        }
        else
        {
            throw new InvalidCopyException("Element of Term of invalid type!");
        }

    }

    public FOLTerm()
    {

    }

    public FOLMatchEnum compareTo(FOLTerm term)
    {
        if (this.notInverted == term.notInverted)
            return element.compareTo(term.element);

        return FOLMatchEnum.NoMatch;
    }

    public FOLMatchEnum compCompareTo(FOLTerm term)
    {
        if (this.notInverted != term.notInverted)
            return compareTo(term);
        return FOLMatchEnum.NoMatch;
    }

    public FOLMatchEnum strictCompareTo(FOLTerm term)
    {
        if (this.notInverted == term.notInverted)
            return element.strictCompareTo(term.element);
        else
            return FOLMatchEnum.NoMatch;
    }

    public static FOLTerm parse(String s, TreeMap<String, FOLElement> names) throws InvalidInputException, StringQueueEmptyException, CouldNotFindDefinitionException
    {
        FOLTerm term = new FOLTerm();

        if (s.startsWith("nie "))
        {
            s = s.substring(3).trim();
            term.notInverted = false;
        }
        else
        {
            term.notInverted = true;
        }

        s = s.replace('(', ' ').replace(')', ' ').replace(',', ' ');
        String[] split = s.split(" +");

        Queue<String> stringQ = new LinkedList<String>();
        for (int i = 0; i < split.length; i++)
        {
            stringQ.offer(split[i]);
        }

        term.element = FOLTerm.parseElement(stringQ, names);
        if (!stringQ.isEmpty())
            throw new InvalidInputException("Malformed term");
        return term;
    }

    public static FOLElement parseElement(Queue<String> q, TreeMap<String, FOLElement> names) throws StringQueueEmptyException, CouldNotFindDefinitionException
    {
        String s = q.poll();
        if (s == null)
        {
            throw new StringQueueEmptyException("StringQueue is empty"); // TODO:
                                                                         // Clean
                                                                         // up
        }
        FOLElement element = names.get(s);
        if (element == null)
            throw new CouldNotFindDefinitionException("No prior definition found");
        if (element instanceof FOLVariable || element instanceof FOLObject)
            return element;
        if (element instanceof FOLFunctionPrototype)
        {
            FOLFunctionPrototype functionElement = (FOLFunctionPrototype) element;
            FOLElement[] arguments = new FOLElement[functionElement.number];
            for (int i = 0; i < arguments.length; i++)
            {
                arguments[i] = FOLTerm.parseElement(q, names);
            }
            return new FOLFunction(functionElement.name, functionElement, arguments);
        }
        return null;
    }

    public String toString()
    {
        String s = "";
        if (!this.notInverted)
        {
            s+="nie ";
        }
        if (this.element instanceof FOLObject || this.element instanceof FOLVariable)
            return s+element.name;
        else
            return s+((FOLFunction) this.element).toString();
    }

    public void substitution(HashMap<FOLElement, FOLElement> subs) throws Exception // TODO:
    // Check
    {
        System.out.println("Prior Element: " + this.element.toString());
        this.element = this.element.substitute(subs);
        System.out.println("New Element: " + this.element.toString());
    }
}
