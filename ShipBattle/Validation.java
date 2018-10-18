
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
    
    public boolean validBlank(String iobuffer) //method to check insert any empties or blanks
    {
        if (iobuffer.matches("[abcdexABCDEX]*"))
        {        
            if (iobuffer.trim().isEmpty() || iobuffer.length() > 1)
            {
                System.out.println("Error : please insert from A to E OR Z !");
                return false;
            }
            return true;
         }
        System.out.println("Error: opition shouldn't be #!123...Please enter again:");
        return true;
    }
    
    public boolean validshipname(String iobuffer) //method to check insert any empties or blanks
    {
        if (iobuffer.matches("[a-zA-z\\-]*"))
        {           
            if (iobuffer.trim().isEmpty() || iobuffer.length() < 2)
            {
                System.out.println("Error : please insert more than 2!");
                return false;
            }
            int position = 0;
            char hyphen = '-';
            int count = 0;
            for (position = 0; position < iobuffer.length(); position++)
            {
                if (iobuffer.charAt(position) == hyphen)
                {
                    count++;
                    if (count > 1)
                    return false;
                }
                if (iobuffer.charAt(0) == hyphen || iobuffer.charAt(iobuffer.length() - 1) == hyphen)
                {
                    return false;
                }           
            }
            return true;
        }
        else
        {
            System.out.println("Error: opition shouldn't be #!123...Please enter again:");
            return false;
        }
    }
}
