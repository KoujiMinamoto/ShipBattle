import java.util.*;
import java.io.*;
public class ShipList
{
   private ArrayList<Ship> shipList;
 
   public ShipList()
   {
       shipList = new ArrayList<Ship>();
   }
   
   public ArrayList<Ship> getships()
    {
        return shipList;
    }
    
   public boolean addship (Ship newship)
    {
        if (newship != null)
        {
           shipList.add(newship);
           return true;
        }
        return false;       
    }
}
