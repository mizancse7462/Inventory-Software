
package SuperShop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.persistence.Convert;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Purchse extends javax.swing.JFrame {
    Connection con = null;
    PreparedStatement post = null;
    ResultSet rs = null;
    double  OldStock=0;
    public Purchse() {
        initComponents();
          setResizable(false);
        setLocationRelativeTo(null);
        con = DBConnect.ConnectDb();
        productList();
        qun.setText("");
        netTotal.setText("0.0");
      //  netTotalPrice.setVisible(false);
    }
     private void productList() {
        try {
            productName.removeAllItems();
            String sql = "SELECT * FROM `productinfo` ";
            post = con.prepareStatement(sql);
            ResultSet rs = post.executeQuery();
            productName.addItem("Select Product");
            while (rs.next()) {
                String product = rs.getString("product_name");
                productName.addItem(product);
            }
            rs.close();
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                post.close();
            } catch (Exception e) {

            }
        }
    }
     
     
     private void productPrice() {
        try {
          qun.setText("");
            String sql = "SELECT * FROM `productinfo` where product_name='"+productName.getSelectedItem()+"' ";
            post = con.prepareStatement(sql);
            ResultSet rs = post.executeQuery();
         
            while (rs.next()) {
                String pdtpurchase = rs.getString("purchase_price");
                String pdtprice = rs.getString("sales_price");
               price.setText(pdtpurchase);
               saleprice.setText(pdtprice);
            }
            rs.close();
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                post.close();
            } catch (Exception e) {

            }
        }
    }
     
    
    private void viewtable(){
        try{
            String sql = "SELECT  * from purchase_form where  InvoiceNo='"+invoiceNo.getText()+"'";
            post =con.prepareStatement(sql);
            
            rs = post.executeQuery(sql);
            
            viewProduct.setModel(DbUtils.resultSetToTableModel(rs));
    
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
    
   void stockUpdate()
    {
       try
        {
               OldStock =OldStock+Double.valueOf(qun.getText());
               
           
            
        String sql="UPDATE  stock set `ProductStock`='"+OldStock+"',`Purchase_Price`='"+price.getText()+"',`Sales_Price`='"+saleprice.getText()+"' where Product_Name='"+productName.getSelectedItem()+"'";
        post=con.prepareStatement(sql);
        post.execute();
        }
        catch(Exception e)
        {
            
        }
    }
   
   void stockInsert()
   {
        try
        {
        String sql="INSERT INTO stock(`Product_Name`,`ProductStock`,`Purchase_Price`,`Sales_Price`) VALUES('"+productName.getSelectedItem()+"','"+qun.getText()+"','"+price.getText()+"','"+saleprice.getText()+"')";   
        post=con.prepareStatement(sql);
        post.execute();
        }
        catch(Exception e)
        {
            
        }
   }
    void insert()
    {
        try
        {
             java.util.Date date = voucherDate.getDate();
            SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy");
            String voucherDate = formate.format(date);
            
              java.util.Date dateinvoice = invoiceDate.getDate();
            SimpleDateFormat invoiceDate = new SimpleDateFormat("dd-MM-yyyy");
            String invoice = formate.format(dateinvoice);
            
            String sql="INSERT INTO purchase_form(`voucherNo`,`VoucherDate`,`InvoiceNo`,`InvoiceDate`,`SupplyerName`,`SupplyerPhone`,`ProductName`,`ProductQun`,`ProductPrice`,`Total_Purchase_Price`,`Total_Sale_Price`) VALUES('"+voucherNo.getText()+"','"+voucherDate+"','"+invoiceNo.getText()+"','"+invoice+"','"+supplyerName.getText()+"','"+supplyerPhone.getText()+"','"+productName.getSelectedItem()+"','"+qun.getText()+"','"+price.getText()+"','"+total.getText()+"','"+saleprice.getText()+"')";   
            JOptionPane.showMessageDialog(this,sql);
            
            post=con.prepareStatement(sql);
            post.execute();
            
            
           
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"ERROR", "Unsuccessful", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        invoiceNo = new javax.swing.JTextField();
        voucherNo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        productName = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        qun = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        saleprice = new javax.swing.JTextField();
        Btnadd = new javax.swing.JButton();
        voucherDate = new com.toedter.calendar.JDateChooser();
        invoiceDate = new com.toedter.calendar.JDateChooser();
        supplyerPhone = new javax.swing.JTextField();
        supplyerName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewProduct = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        netTotal = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        paid = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        due = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Purchase Form");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Purchase Entry");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel2.setText("Invoice No.");

        jLabel3.setText("Voucher No.");

        jLabel4.setText("Voucher Date");

        jLabel5.setText("Entry Date");

        jLabel6.setText("Select Product");

        productName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameActionPerformed(evt);
            }
        });

        jLabel7.setText("Qun.");

        qun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qunActionPerformed(evt);
            }
        });
        qun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                qunKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qunKeyReleased(evt);
            }
        });

        jLabel8.setText("Price");

        jLabel9.setText("Total Price");

        jLabel10.setText("Sale Price");

        Btnadd.setText("Add");
        Btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnaddActionPerformed(evt);
            }
        });

        jLabel14.setText("Mobile");

        jLabel15.setText("Supplyer Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(productName, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(qun, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(124, 124, 124)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(saleprice, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btnadd, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(voucherNo, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                    .addComponent(invoiceNo)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(supplyerName, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(invoiceDate, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(voucherDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(supplyerPhone))
                        .addGap(115, 115, 115))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(voucherNo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(voucherDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(invoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2))
                                    .addComponent(invoiceDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(2, 2, 2))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5))))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplyerName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplyerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(qun, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saleprice, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        viewProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(viewProduct);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel11.setText("Net Total");

        netTotal.setEditable(false);

        jLabel12.setText("Paid");

        paid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                paidKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paidKeyReleased(evt);
            }
        });

        jLabel13.setText("Due");

        due.setEditable(false);

        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(paid, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(netTotal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(due, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(netTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(paid, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(due, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnaddActionPerformed
        // TODO add your handling code here:
        insert();
        try
        {
            String sql = "SELECT * FROM stock WHERE Product_Name='"+productName.getSelectedItem()+"' ";
            post = con.prepareStatement(sql);
            ResultSet rs = post.executeQuery();
         
            if (rs.next()) 
            {
                 OldStock =Double.valueOf(rs.getString("ProductStock"));
               
               stockUpdate(); 
            }
            else
            {
                stockInsert();
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
        
        viewtable();

        
        try
        {
            double x =Double.valueOf(total.getText());
            double y = Double.valueOf(netTotal.getText());
            double z = x + y;
           // netTotalPrice.setText(String.valueOf(z));
            netTotal.setText(String.valueOf(z));
        }
        catch(Exception e)
        {
            
        }
        
        qun.setText("");
        price.setText("");
        total.setText("");
        saleprice.setText("");
    }//GEN-LAST:event_BtnaddActionPerformed

    private void productNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameActionPerformed
        // TODO add your handling code here:
         productPrice();
    }//GEN-LAST:event_productNameActionPerformed

    private void qunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qunKeyPressed
        // TODO add your handling code here:
         try
        {
            double x =Double.valueOf(price.getText());
            double y = Double.valueOf(qun.getText());
            double z = x * y;
            total.setText(String.valueOf(z));
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_qunKeyPressed

    private void qunKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qunKeyReleased
        // TODO add your handling code here:
         try
        {
            double x =Double.valueOf(price.getText());
            double y = Double.valueOf(qun.getText());
            double z = x * y;
            total.setText(String.valueOf(z));
            
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_qunKeyReleased

    private void qunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qunActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:\

        try
        {

            String sql="INSERT INTO purchase_ledger(`purchaseInvoiceNo`,`TotalPrice`,`Paid`,`Due`) VALUES('"+invoiceNo.getText()+"','"+netTotal.getText()+"','"+paid.getText()+"','"+due.getText()+"')";
            post=con.prepareStatement(sql);
            post.execute();
            JOptionPane.showMessageDialog(null, "Insert Successfully");

            total.setText("");
            paid.setText("");
            due.setText("");
            //netTotalPrice.setText("");

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"ERROR", "Unsuccessful", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void paidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidKeyReleased
        // TODO add your handling code here:
        try
        {
            double x =Double.valueOf(netTotal.getText());
            double y = Double.valueOf(paid.getText());
            double z = x - y;
            due.setText(String.valueOf(z));
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
    }//GEN-LAST:event_paidKeyReleased

    private void paidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidKeyPressed
        // TODO add your handling code here:
        try
        {
            double x =Double.valueOf(netTotal.getText());
            double y = Double.valueOf(paid.getText());
            double z = x - y;
            due.setText(String.valueOf(z));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            

        }
    }//GEN-LAST:event_paidKeyPressed

    
   

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Purchse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Purchse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Purchse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Purchse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Purchse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btnadd;
    private javax.swing.JTextField due;
    private com.toedter.calendar.JDateChooser invoiceDate;
    private javax.swing.JTextField invoiceNo;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField netTotal;
    private javax.swing.JTextField paid;
    private javax.swing.JTextField price;
    private javax.swing.JComboBox<String> productName;
    private javax.swing.JTextField qun;
    private javax.swing.JTextField saleprice;
    private javax.swing.JTextField supplyerName;
    private javax.swing.JTextField supplyerPhone;
    private javax.swing.JTextField total;
    private javax.swing.JTable viewProduct;
    private com.toedter.calendar.JDateChooser voucherDate;
    private javax.swing.JTextField voucherNo;
    // End of variables declaration//GEN-END:variables
}
