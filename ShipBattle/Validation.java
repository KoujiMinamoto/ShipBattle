public class Validation
{
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
    
    
}