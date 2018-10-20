import java.util.*;
import java.lang.*;
import java.io.*;
public class FileIO
{   
    private String filename;
    int maxship;
    int pos;
    boolean ComputerShipsVisible;
    boolean MultipleHitsAllowed;
    String ComputerShipsVisible1;
    String MultipleHitsAllowed1;
    Validation vaild= new Validation();

     public void readFile()
    {
        filename = ("game.txt");
        String setting;
        Ship loadFromFile;        
        try
        {
            FileReader inputFile = new FileReader(filename);
            Scanner parser = new Scanner(inputFile);;
            

            setting = parser.nextLine();
            String[] attribute = setting.split(",");
            pos = vaild.convertStringtoInt(attribute[0]);
            MultipleHitsAllowed = vaild.StringtoBoolean(attribute[1]);
            ComputerShipsVisible = vaild.StringtoBoolean(attribute[2]);
            maxship = vaild.convertStringtoInt(attribute[3]);
            
            
            if (ComputerShipsVisible=true)
            {
                ComputerShipsVisible1 = "ON";
            }
            else
            ComputerShipsVisible1 = "OFF";
            MultipleHitsAllowed1 = attribute[1];
            

            
            inputFile.close();
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(filename + " not found");
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
    }
    
     public void writeFile(String winner,int playerscore,int comscore)
    {
        filename = ("gameoutcome.txt");
        //try catch to handle IOException
        try
        {
            PrintWriter outputFile = new PrintWriter (filename);
            outputFile.println(winner +" wins. Final Score Player ("+playerscore+") and Computer ("+comscore+")");
            outputFile.close();    
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
    }
}