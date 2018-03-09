package parser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import objects.*;

public class RunTimeMethods
{
    public ArrayList<FOLObject> objects = new ArrayList<FOLObject>();

    public ArrayList<FOLVariable> variables = new ArrayList<FOLVariable>();

    public ArrayList<FOLFunction> functions = new ArrayList<FOLFunction>();

    FOLParser parser = new FOLParser();

    public void initialFileInput()
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Input file name/location:");
        Path path = Paths.get(scan.nextLine());
        while (Files.notExists(path))
        {
            System.out.println("Cannot find file, try again");
            path = Paths.get(scan.nextLine());
        }

        parser.ParseFile(path.toString(), objects, variables, functions);
        
        scan.close();
    }

    public void waitForInput()
    {
        Scanner scan = new Scanner(System.in);
        while (true)
        {
            System.out.println("Awaiting Input:");
            System.out.println(scan.nextLine());
            if (true)
            {
                break;
            }
        }

        scan.close();
    }
}
