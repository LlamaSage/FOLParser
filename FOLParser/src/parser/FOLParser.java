package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

import objects.FOLFunction;
import objects.FOLObject;
import objects.FOLVariable;
import exceptions.ForbiddenKeywordException;

public class FOLParser
{
    private String name = "[a-zA-Z0-9_]+";

    private String number = "[0-9]+";

    public void ParseFirstFile(String fileLocation, LinkedList<FOLObject> objectList, LinkedList<FOLVariable> variableList, LinkedList<FOLFunction> functionList) // DEFINITION
    // of
    // variables,
    // objects
    // and
    // functions
    {

        try
        {

            String line = null;
            String[] split;
            int lineNumber = 0;

            FileReader freader = new FileReader(fileLocation);
            BufferedReader reader = new BufferedReader(freader);
            while ((line = reader.readLine()) != null) // read & match line
            {
                lineNumber++;
                split = line.split(" ");
                if (line.matches("TELL object " + name))
                {
                    if (split[2].equals("lub") || split[2].equals("nie"))
                    {
                        throw new ForbiddenKeywordException("Forbidden keyword \"" + split[2] + "\" at line " + lineNumber);
                    }
                }
                else if (line.matches("TELL variable " + name))
                {
                    if (split[2].equals("lub") || split[2].equals("nie"))
                    {
                        throw new ForbiddenKeywordException("Forbidden keyword \"" + split[2] + "\" at line " + lineNumber);
                    }
                }
                else if (line.matches("TELL function " + name + " " + number))
                {
                    if (split[2].equals("lub") || split[2].equals("nie"))
                    {
                        throw new ForbiddenKeywordException("Forbidden keyword \"" + split[2] + "\" at line " + lineNumber);
                    }
                    if (Integer.parseInt(split[3]) == 0)
                    {
                        throw new IllegalArgumentException("Illegal number value \"" + split[3] + "\"at line " + lineNumber);
                    }
                }
                else
                // If line matches none of the expected inputs
                {

                }
            }
            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void ParseSecondFile(String fileLocation) // DEFINITION of
                                                     // terms/formulas
    {
        try
        {
            String line = null;
            FileReader freader = new FileReader(fileLocation);
            BufferedReader reader = new BufferedReader(freader);
            while ((line = reader.readLine()) != null)
            {
                // TODO: Split input, do things
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void ParseSingleLine(String singleString, LinkedList<FOLObject> objectList, LinkedList<FOLVariable> variableList, LinkedList<FOLFunction> functionList) throws ForbiddenKeywordException
    {
        String[] split = singleString.split(" ");
        if (singleString.matches("TELL object " + name))
        {
            if (split[2].equals("lub") || split[2].equals("nie"))
            {
                throw new ForbiddenKeywordException("Forbidden keyword \"" + split[2] + "\"");
            }
        }
        else if (singleString.matches("TELL variable " + name))
        {
            if (split[2].equals("lub") || split[2].equals("nie"))
            {
                throw new ForbiddenKeywordException("Forbidden keyword \"" + split[2] + "\"");
            }
        }
        else if (singleString.matches("TELL function " + name + " " + number))
        {
            if (split[2].equals("lub") || split[2].equals("nie"))
            {
                throw new ForbiddenKeywordException("Forbidden keyword \"" + split[2] + "\"");
            }
            if (Integer.parseInt(split[3]) == 0)
            {
                throw new IllegalArgumentException("Illegal number value \"" + split[3] + "\"");
            }
        }
        else
        // If line matches none of the expected inputs
        {

        }
    }

}