package objects;

import java.util.ArrayList;
import java.util.TreeMap;

import exceptions.CouldNotFindDefinitionException;
import exceptions.InvalidInputException;
import exceptions.StringQueueEmptyException;

public class FOLFormula
{

    public ArrayList<FOLTerm> terms = new ArrayList<FOLTerm>();

    public FOLFormula()
    {

    }

    public static FOLFormula parse(String s, TreeMap<String, FOLElement> names) throws InvalidInputException, StringQueueEmptyException, CouldNotFindDefinitionException
    {
        FOLFormula form = new FOLFormula();
        String[] split = s.split("lub");
        for (int i = 0; i < split.length; i++)
            form.terms.add(FOLTerm.parse(split[i].trim(), names));
        // form.printFormula(); TODO: Remove
        return form;
    }

    public void printFormula()
    {
        System.out.print("Formula: ");
        String s = "";
        for (int i = 0; i < terms.size(); i++)
        {
            if (!terms.get(i).notInverted)
            {
                s += "nie ";
            }
            if (terms.get(i).element instanceof FOLFunction)
                s += (terms.get(i).toString());
            else
                s += terms.get(i).element.name;
            if (i != terms.size() - 1)
                s += " lub ";

        }
        System.out.println(s);
    }

}
