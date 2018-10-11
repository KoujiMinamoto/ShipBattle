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
        int i = 0;
        while(i < file.maxship)
        {
                  
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
    
    public void displaymenu()
    {}
    
    
 
    
}
