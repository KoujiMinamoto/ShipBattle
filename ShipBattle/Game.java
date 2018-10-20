import java.util.*;
import java.lang.*;
import java.io.*;
public class Game
{
   private ShipList playerShips;
   private ShipList computerShips;
   CoordinateGenerator Random = new CoordinateGenerator();
   private String filename;
    int maxship;
    int pos;
    int playerscore;
    int comscore;
    boolean ComputerShipsVisible;
    boolean MultipleHitsAllowed;
    String ComputerShipsVisible1;
    String MultipleHitsAllowed1;
    String winner = null;
    
    
    public Game()
    {
        playerShips = new ShipList();
        computerShips =  new ShipList();
        playerscore = 0;
        comscore = 0;
    }
    
       public void  playGame()
    {
        
        readFile();
        boolean exit = false;
        int maxpos = pos;
        int i = 0;
        System.out.println("Loading settings");
        while(i < maxship)
        {
            System.out.println("Please insert shipname for ship"+ (i+1)); 
            SetShipName(i,maxpos);
            i++;        
        }
        i= 0 ;
        while(i < maxship)
        {
            SetcomShipName(i,maxpos);
            i++;        
        } 
        Scanner input = new Scanner(System.in);
        display(maxpos);
        displaycom(maxpos);
        int round = 1;
        while (!exit)
        {
            result(round);
            display(maxpos);
            if(ComputerShipsVisible==true)
            displaycom(maxpos);
            else
            displayhidden(maxpos);
            System.out.println(" ");
            guess(maxpos);
            winner(maxship);
            if(winner!=null)
            {
                System.out.println("Congrulations!!"+winner+"Win !!"); 
                writeFile();
                exit = true;
            }
            round++;
         }
    }
    
    public void SetShipName(int numberofship,int maxpos)
    {
        Ship ranklist = new Ship();
        Scanner input = new Scanner(System.in);
        System.out.println("=== Add ship ===");
        System.out.println("Please insert ship"+(numberofship+1)+" name"); 
        String newname = input.nextLine();
        while(!validshipname(newname))
        newname = input.nextLine();
        boolean exit = false;
        int x = 0;
        int y =0;
        while(!exit)
        {
        System.out.println("Please insert ship"+(numberofship+1)+" X(0-"+(maxpos-1)+")"); 
        String newx = input.nextLine();
        while(!validshipxy(newx,maxpos))
        newx = input.nextLine();
        x = convertStringtoInt(newx);
        
        System.out.println("Please insert ship"+(numberofship+1)+" Y(0-"+(maxpos-1)+")");
        String newy = input.nextLine();
        while(!validshipxy(newy,maxpos))
        newy = input.nextLine();
        y = convertStringtoInt(newy);
        if(validrepeat(x,y)==false)
        exit=true;
        else
        System.out.println("Please insert diffrernt x and y, can not be same");
        }
        ranklist.setshipName(newname);
        ranklist.setxPos(x);
        ranklist.setyPos(y);
        ranklist.setnoOfHitsNeeded(Random.randInt(1,5));
        playerShips.addship(ranklist);
    }
    
    public void SetcomShipName(int i,int maxpos)
    {
        Ship ranklist = new Ship();
        String newname = "comship"+(i+1);
        int x = Random.randInt(0,maxpos-1);
        int y = Random.randInt(0,maxpos-1);
        ranklist.setshipName(newname);
        ranklist.setxPos(x);
        ranklist.setyPos(y);
        ranklist.setnoOfHitsNeeded(Random.randInt(1,5));
        computerShips.addship(ranklist);
    }
    
    public void result(int i)
    {
        System.out.println("Beginning Round "+i); 
        System.out.println("Player score "+playerscore); 
        System.out.println("Computer score "+comscore); 
        System.out.println("ComputerShipsVisible mode is"+ComputerShipsVisible1);
    
    
    }
    
    public void winner(int maxship)
    {
        int numberofhitneed = 0;
        int numberofhitneedcom = 0;
        for(int i=0;i < maxship;i++)
        {
            numberofhitneed =numberofhitneed+playerShips.getships().get(i).getnoOfHitsNeeded();
        }
        for(int i=0;i < maxship;i++)
        {
            numberofhitneedcom =numberofhitneedcom+computerShips.getships().get(i).getnoOfHitsNeeded();
        }
        if (numberofhitneed == 0||numberofhitneedcom == 0)
        {
            if(numberofhitneed == 0)
            {
                winner = "Player";
            }
            else
            winner= "Computer";
        
        }
        else
        winner = null;
    }
    
    public void displayhidden(int maxpos)
    {
        
        for(int y=0;y < maxpos;y++)
        {
            for(int x=0;x < maxpos;x++)
            {
                
                System.out.print("~");
                
            }
            System.out.print("\n");
        }

    
    }
    
    public void display(int maxpos)
    {
        System.out.println("Player gird");
        for(int y=0;y < maxpos;y++)
        {
            for(int x=0;x < maxpos;x++)
            {
                boolean checkhit = false;
                int i=0;
                while(i < maxship)
                {    
                if(x==playerShips.getships().get(i).getxPos()&&y==playerShips.getships().get(i).getyPos())
                {   
                    checkhit=true;
                    if(playerShips.getships().get(i).getnoOfHitsMade()==0)
                    {
                        System.out.print("O");
                    }
                    else
                    if(playerShips.getships().get(i).getnoOfHitsNeeded()==0)
                    {
                        System.out.print("X");
                    }
                    else
                    System.out.print("D");
                }
                i++;
                }
                
                if(checkhit == false)
                System.out.print("~");
                
            }
            System.out.print("\n");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("----------------------------");
    
    }
    
    public void displaycom(int maxpos)
    {
        System.out.println("Computer gird");
        for(int y=0;y < maxpos;y++)
        {
            for(int x=0;x < maxpos;x++)
            {
                boolean checkhit = false;
                int i=0;
                while(i < maxship)
                {    
                if(x==computerShips.getships().get(i).getxPos()&&y==computerShips.getships().get(i).getyPos())
                {   
                    checkhit=true;
                    if(computerShips.getships().get(i).getnoOfHitsMade()==0)
                    {
                        System.out.print("O");
                    }
                    else
                    if(computerShips.getships().get(i).getnoOfHitsNeeded()==0)
                    {
                        System.out.print("X");
                    }
                    else
                    System.out.print("D");
                }
                i++;
                }
                
                if(checkhit == false)
                System.out.print("~");
                
            }
            System.out.print("\n");
        }
        
    
    }
    
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
            pos = convertStringtoInt(attribute[0]);
            MultipleHitsAllowed = StringtoBoolean(attribute[1]);
            ComputerShipsVisible = StringtoBoolean(attribute[2]);
            maxship = convertStringtoInt(attribute[3]);
            
            
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
    
     public void writeFile()
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
    //vaild
    public int convertStringtoInt(String input) //method to convert String to Integer
    {
        //intialised variables
        String S = input;
        int number = 0;
        //try catch to handle NumberFormatException
        try
        {
            // the String to int conversion happens here
            number = Integer.parseInt(input.trim());
            // print out the value after the conversion
            //System.out.println("int i = " + i);
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormatException: " + nfe.getMessage() + ", please input an integer!");
        }
        return number;
    }
    
    public boolean StringtoBoolean(String input)
    {
        if (input.equals("true"))
        {
        
            return true;
        }
        else
        return false;
    
    }
    
    public boolean validshipname(String iobuffer) //method to check insert any empties or blanks
    {
        if (iobuffer.matches("[a-zA-Z0-9]*"))
        {        
            if (iobuffer.trim().isEmpty())
            {
                System.out.println("Error : please insert Not blANK !");
                return false;
            }
            return true;
         }
        System.out.println("Error: opition shouldn't be #!..Please enter again:");
        return true;
    }
    
    public boolean validshipxy(String iobuffer,int maxpos) //method to check insert any empties or blanks
    {
        if (iobuffer.matches("[0-9]*"))
        {        
            if (iobuffer.trim().isEmpty())
            {
                System.out.println("Error : please insert Not blANK !");
                return false;
            }
            else if(convertStringtoInt(iobuffer)>=0&&convertStringtoInt(iobuffer)<maxpos)
            {
                return true;
            }
            else
            {
            
            System.out.println("Error: should in range");
            return false;
            }
         }
         else
        {
        System.out.println("Error: opition should be numbers.Please enter again:");
        return false;
        }
        
        
        
    }
    
    public boolean validrepeat(int x,int y) //method to check insert any empties or blanks
    {
        int a = 0;
        for(int i=0;i<playerShips.getnumberofships();i++)
        {
            if(x==playerShips.getships().get(i).getxPos()&&y==playerShips.getships().get(i).getyPos())
            {
                a =1;
                
            }

        }
        if(a!=0)
        return true;
        else
        return false;
    }
    
    public void guess(int maxpos)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("guess"); 
        System.out.println("Please insert ship X(0-"+(maxpos-1)+")"); 
        int x = input.nextInt();
        System.out.println("Please insert ship Y(0-"+(maxpos-1)+")"); 
        int y = input.nextInt();
        int i = 0;
        boolean checkhit = false;
        while(i < maxship)
        {    
                if(x==computerShips.getships().get(i).getxPos()&&y==computerShips.getships().get(i).getyPos())
                {   
                    System.out.println("PLAYER HITTTTTT!"); 
                    checkhit = true; 
                    playerscore = playerscore+10;
                    if(computerShips.getships().get(i).getnoOfHitsNeeded()==0)
                    {
                        System.out.print("Unfortuntely computer ship"+(i+1)+"has be destroyed");
                    }
                    else if(computerShips.getships().get(i).getnoOfHitsNeeded()==1)
                    {
                        System.out.print("Unfortuntely computer ship"+(i+1)+"has be destroyed");
                        computerShips.getships().get(i).setnoOfHitsMade(computerShips.getships().get(i).getnoOfHitsMade()+1);
                        computerShips.getships().get(i).setnoOfHitsNeeded(computerShips.getships().get(i).getnoOfHitsNeeded()-1);
                        
                    }
                    else
                    { 
                        computerShips.getships().get(i).setnoOfHitsMade(computerShips.getships().get(i).getnoOfHitsMade()+1);
                        computerShips.getships().get(i).setnoOfHitsNeeded(computerShips.getships().get(i).getnoOfHitsNeeded()-1);
                        
                    }
                }
                i++;
        }
        if(checkhit == false)
        System.out.println("PLAYER MISSSSSSS");
        
        
        //for com
        i= 0;
        int comx=Random.randInt(0,maxpos-1);
        int comy=Random.randInt(0,maxpos-1);
        System.out.println("Computer to make a guess"); 
        System.out.println("Computer guess x"+comx); 
        System.out.println("Computer guess y"+comy); 
        while(i < maxship)
        {    
                if(x==playerShips.getships().get(i).getxPos()&&y==playerShips.getships().get(i).getyPos())
                {   
                    System.out.println("COMPUTER HITTTTTT!"); 
                    checkhit = true; 
                    comscore = comscore+10;
                    if(playerShips.getships().get(i).getnoOfHitsNeeded()==0)
                    {
                        System.out.print("Unfortuntely player ship"+(i+1)+"has be destroyed");
                    }
                    else if (playerShips.getships().get(i).getnoOfHitsNeeded()==1)
                    {
                        System.out.print("Unfortuntely player ship"+(i+1)+"has be destroyed");
                        playerShips.getships().get(i).setnoOfHitsMade(playerShips.getships().get(i).getnoOfHitsMade()+1);
                        playerShips.getships().get(i).setnoOfHitsNeeded(playerShips.getships().get(i).getnoOfHitsNeeded()-1);
                    }
                    else
                    { 
                        playerShips.getships().get(i).setnoOfHitsMade(playerShips.getships().get(i).getnoOfHitsMade()+1);
                        playerShips.getships().get(i).setnoOfHitsNeeded(playerShips.getships().get(i).getnoOfHitsNeeded()-1);
                        
                    }
                }
                i++;
        }
        if(checkhit == false)
        System.out.println("COMPUTER MISSSSSSS");
    }
    
    
}
