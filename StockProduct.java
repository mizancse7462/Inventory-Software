
package SuperShop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class StockProduct extends javax.swing.JInternalFrame {
    Connection con =null;
    PreparedStatement post = null;
    ResultSet rs = null;
    public StockProduct() {
        initComponents();
         setResizable(false);
        con = DBConnect.ConnectDb();
        viewAllProduct();
    }
private void viewAllProduct(){
        try{
            String sql = "SELECT * FROM stock";
            post =con.prepareStatement(sql);
            
            rs = post.executeQuery(sql);
            
            viewStock.setModel(DbUtils.resultSetToTableModel(rs));
    
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
        finally{
        try{
            post.close();
        }
        catch(Exception e){
        }
        }
   }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        viewStock = new javax.swing.JTable();

        setClosable(true);
        setTitle("Product Stock");

        viewStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(viewStock);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable viewStock;
    // End of variables declaration//GEN-END:variables
}
