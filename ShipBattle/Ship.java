import java.util.*;
public class Ship
{
   private String shipName;
   private int xPos, yPos,noOfHitsMade,noOfHitsNeeded;
   public Ship()
   {
       int noOfHitsMade = 0;
       int noOfHitsNeeded = 3;
       
    }
   
   public void setshipName(String ship)
   {
       shipName = ship;
   }
   
   public String getshipName()
   {
       return shipName;
   }
   
   public void setxPos(int x)
   {
       xPos = x;
   }
   
   public int getxPos()
   {
       return xPos;
   }
   
   public void setyPos(int y)
   {
       yPos = y;
   }
   
   public int getyPos()
   {
       return yPos;
   }
   
   public void setnoOfHitsMade(int hitsmade)
   {
       noOfHitsMade = hitsmade;
   }
   
   public int getnoOfHitsMade()
   {
       return noOfHitsMade;
   }
   
   public void setnoOfHitsNeeded(int hitsneed)
   {
       noOfHitsNeeded = hitsneed;
   }
   
   public int getnoOfHitsNeeded()
   {
       return noOfHitsNeeded;
   }
   
   
}
