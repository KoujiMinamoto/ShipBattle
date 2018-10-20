import java.util.*;
import java.lang.*;
public class Game
{
   private ShipList playerShips;
   private ShipList computerShips;
   CoordinateGenerator Random = new CoordinateGenerator();
    int playerscore;
    int comscore;
    int maxship;
    String winner = null;
    FileIO file = new FileIO();
    Validation vaild= new Validation();
    
    public Game()
    {
        playerShips = new ShipList();
        computerShips =  new ShipList();
        playerscore = 0;
        comscore = 0;
    }
    
       public void  playGame()
    {
        
        file.readFile();
        boolean exit = false;
        int maxpos = file.pos;
        maxship = file.maxship;
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
            if(file.ComputerShipsVisible==true)
            displaycom(maxpos);
            else
            displayhidden(maxpos);
            System.out.println(" ");
            guess(maxpos);
            winner(maxship);
            if(winner!=null)
            {
                System.out.println("Congrulations!!"+winner+"Win !!"); 
                file.writeFile(winner,playerscore,comscore);
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
        while(!vaild.validshipname(newname))
        newname = input.nextLine();
        boolean exit = false;
        int x = 0;
        int y =0;
        while(!exit)
        {
        System.out.println("Please insert ship"+(numberofship+1)+" X(0-"+(maxpos-1)+")"); 
        String newx = input.nextLine();
        while(!vaild.validshipxy(newx,maxpos))
        newx = input.nextLine();
        x = vaild.convertStringtoInt(newx);
        
        System.out.println("Please insert ship"+(numberofship+1)+" Y(0-"+(maxpos-1)+")");
        String newy = input.nextLine();
        while(!vaild.validshipxy(newy,maxpos))
        newy = input.nextLine();
        y = vaild.convertStringtoInt(newy);
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
        boolean exit = false;
        int x = 0;
        int y =0;
        while(!exit)
        {
        x = Random.randInt(0,maxpos-1);
        y = Random.randInt(0,maxpos-1);
        if(validrepeatcom(x,y)==false)
        exit=true;
        }
        ranklist.setshipName(newname);
        ranklist.setxPos(x);
        ranklist.setyPos(y);
        ranklist.setnoOfHitsNeeded(Random.randInt(1,5));
        computerShips.addship(ranklist);
    }
    
    public void result(int i)
    {
        System.out.println("------------------------------"); 
        System.out.println("Beginning Round "+i); 
        System.out.println("Player score "+playerscore); 
        System.out.println("Computer score "+comscore); 
        System.out.println("ComputerShipsVisible mode is"+file.ComputerShipsVisible1);
    
    
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
                winner = "Computer";
            }
            else
            winner= "Player";
        
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
    
     
    //vaild
    
    
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
    
    public boolean validrepeatcom(int x,int y) //method to check insert any empties or blanks
    {
        int a = 0;
        for(int i=0;i<computerShips.getnumberofships();i++)
        {
            if(x==computerShips.getships().get(i).getxPos()&&y==computerShips.getships().get(i).getyPos())
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
        checkhit = false;
        int comx=Random.randInt(0,maxpos-1);
        int comy=Random.randInt(0,maxpos-1);
        System.out.println("Computer to make a guess"); 
        System.out.println("Computer guess x"+comx); 
        System.out.println("Computer guess y"+comy); 
        while(i < maxship)
        {    
                if(comx==playerShips.getships().get(i).getxPos()&&comy==playerShips.getships().get(i).getyPos())
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
