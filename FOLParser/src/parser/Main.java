package parser;

import java.util.HashMap;
import objects.FOLElement;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        RunTimeMethods run = new RunTimeMethods();
        run.setup();
        // run.FileInput();
        run.waitForInput();
        // run.printListToConsole();

        HashMap<FOLElement, FOLElement> subs = new HashMap<FOLElement, FOLElement>();
        subs.put(run.names.get("A"), run.names.get("B"));
        for (int i = 0; i < run.formulas.size(); i++)
        {
            for (int e = 0; e < run.formulas.get(i).terms.size(); e++)
                run.formulas.get(i).terms.get(e).substitution(subs);
        }

    }

}
