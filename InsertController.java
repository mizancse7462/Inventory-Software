
package SuperShop;
import java.sql.*;

public class InsertController {
    
   // Insert insert=new Insert();
    public  boolean insert(String query)
    {
        DBConnect db=new DBConnect();
        Connection con=db.ConnectDb();
        PreparedStatement post;
        
        try
        {
           post=con.prepareStatement(query);
           post.execute();
           post.close();
           return true;
       }
       catch(Exception ex)
       {
          System.out.println(ex);
           return false;
       }
       
    }
}