import java.util.*;
import java.lang.*;
public class Game
{
   private ShipList playerShips;
   private ShipList computerShips;
   CoordinateGenerator Random = new CoordinateGenerator();
   FileIO file;
   Validation vaild;
       public void  playGame()
    {
        
        file.readFile();
        boolean exit = false;
        int maxpos = file.pos;
        int i = 0;
        while(i < file.maxship)
        {
            System.out.println("Please insert playername for ship"+ i); 
            SetShipName(i,maxpos);
            i++;        
        }
        while(i < file.maxship)
        {
            SetcomShipName(i,maxpos);
            i++;        
        } 
        Scanner input = new Scanner(System.in);
        while (!exit)
        {
            displaymenu();
            //insert case
            String iobuffer = input.nextLine(); 
            System.out.println(" ");            
            if (vaild.validBlank(iobuffer))
            { 
                String iobuffer1 = iobuffer.toUpperCase();
                char option = iobuffer1.charAt(0);
                switch(option)
                {
                    case 'A': 
                            break;
                    case 'B':
                            break;
                    case 'C': 
                             break;
                    case 'D': 
                             break;
                    case 'E': 
                             break;                         
                    case 'X': file.writeFile();
                            exit = true;
                            System.out.println("Goodbye. ");
                            break; 
                }
            }
         }
    }
    
    public void SetShipName(int numberofship,int maxpos)
    {
        Ship ranklist = new Ship();
        Scanner input = new Scanner(System.in);
        System.out.println("=== Add Player ===");
        System.out.println("Please insert ship"+numberofship+" name"); 
        String newname = input.nextLine();
        while(vaild.validshipname(newname))
        newname = input.nextLine();
        
        System.out.println("Please insert ship"+numberofship+" X(0-"+(maxpos-1)+")"); 
        String newx = input.nextLine();
        while(vaild.validshipname(newx))
        newx = input.nextLine();
        int x = vaild.convertStringtoInt(newx);
        
        System.out.println("Please insert ship"+numberofship+" Y(0-"+(maxpos-1)+")");
        String newy = input.nextLine();
        while(vaild.validshipname(newy))
        newy = input.nextLine();
        int y = vaild.convertStringtoInt(newy);
        ranklist.setshipName(newname);
        ranklist.setxPos(x);
        ranklist.setyPos(y);
        playerShips.addship(ranklist);
    }
    
    public void SetcomShipName(int i,int maxpos)
    {
        Ship ranklist = new Ship();
        String newname = "comship"+i;
        int x = Random.randInt(0,maxpos-1);
        int y = Random.randInt(0,maxpos-1);
        ranklist.setshipName(newname);
        ranklist.setxPos(x);
        ranklist.setyPos(y);
        computerShips.addship(ranklist);
    }
    
    public void displaymenu()
    {}
    
    
 
    
}
