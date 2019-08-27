
package SuperShop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnect {
     Connection con = null;
     public static Connection ConnectDb(){
        try{
              Class.forName("com.mysql.jdbc.Driver");
             // Connection conn = DriverManager.getConnection("jdbc:mysql://www.serveboxbd.com/servebox_Conductor","servebox_root","CWz(traz{I+?");
              Connection con = DriverManager.getConnection("jdbc:mysql://localhost/supershop","root","");
         
               // JOptionPane.showMessageDialog(null, "Connection successful");
                return con;
         
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e, "Database Connection Faild", JOptionPane.WARNING_MESSAGE);
            return null;
           }
      
     }
}
