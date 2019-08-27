
package SuperShop;
import java.sql.*;
import java.awt.*;

import net.proteanit.sql.DbUtils;
import javax.swing.JOptionPane;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;



public class customerAndSupplyer extends javax.swing.JInternalFrame {

    Connection con =null;
    PreparedStatement post = null;
    ResultSet rs = null;
    
    
    public customerAndSupplyer() {
        initComponents();
        con = DBConnect.ConnectDb();
        viewtable();
        txtId.setVisible(false);
    }

    
    void clear(){
       txtId.setText("");
       txtName.setText("");
       txtMobile.setText("");
       txtEmail.setText("");
       txtPresentAdd.setText("");
       txtPermanentAdd.setText("");
       selectMemberType.addItem("");
   }
    
    void insert()
    {   
       
       
       if (txtName.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Insert the Name","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
       
       if (txtMobile.getText().equals("") && txtPresentAdd.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Please Fill out the all fields","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
       if (selectMemberType.getSelectedItem().equals("Select Type")){
                JOptionPane.showMessageDialog(this,"Select member type","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
       try
       {
            
            String sql="INSERT INTO customeAndSupplyer (`name`,`mobile`,`email`,`present_address`,`permanent_address`,`member_type`)VALUES ('"+txtName.getText()+"','"+txtMobile.getText()+"','"+txtEmail.getText()+"','"+txtPresentAdd.getText()+"','"+txtPermanentAdd.getText()+"','"+selectMemberType.getSelectedItem()+"')";   
            post=con.prepareStatement(sql);
            post.execute();
            JOptionPane.showMessageDialog(null, " Insert Successfully");
            clear();
            viewtable();
             
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"ERROR", "Unsuccessful", JOptionPane.ERROR_MESSAGE);
        }
        
        finally{
            
            try{
                post.close();
            }
            catch(Exception e){
            }
        }  
    }
    
    void delete(){
        try{
            
        String sql="DELETE FROM add_conductor  WHERE id='"+txtId.getText()+"'and name='"+txtName.getText()+"'";
        post=con.prepareStatement(sql);
        post.execute();
        JOptionPane.showMessageDialog(null, "Conductor Deleted Successfully");
        clear();
        viewtable();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"ERROR", "Conductor Delete Unsuccessful",JOptionPane.ERROR_MESSAGE);
        }
        finally{
            try{
               post.close();
            }
            catch(Exception e){
                
            }
        }
    }
    
    
    void Update()
    {   
        if (txtId.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Insert the ID","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
       
       if (txtName.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Insert the Name","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
       
       if (txtMobile.getText().equals("") && txtPresentAdd.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Please fill up all the fields","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
        if (selectMemberType.getSelectedItem().equals("Select Type")){
                JOptionPane.showMessageDialog(this,"Select member type","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
       
        try
        {
            String sql="UPDATE customeAndSupplyer SET name='"+txtName.getText()+"',mobile='"+txtMobile.getText()+"',email='"+txtEmail.getText()+"',present_address='"+txtPresentAdd.getText()+"',permanent_address='"+txtPermanentAdd.getText()+"',member_type='"+selectMemberType.getSelectedItem().toString()+"' WHERE id='"+txtId.getText()+"'";   
            post=con.prepareStatement(sql);
            post.execute();
            JOptionPane.showMessageDialog(null, "Updated Successfully");
            clear();
            viewtable();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"ERROR", "Unsuccessful", JOptionPane.ERROR_MESSAGE);
        }
        
        finally{
            
            try{
                post.close();
            }
            catch(Exception e){
            }
        }  
    }
    
    private void viewtable(){
        try{
            String sql = "SELECT * FROM `customeAndSupplyer` order by member_type ASC ";
            post =con.prepareStatement(sql);
            
            rs = post.executeQuery(sql);
            
               view_table.setModel(DbUtils.resultSetToTableModel(rs));
    
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
    
    
    void  TableClick()
    {
        try
        {
            int row=view_table.getSelectedRow();
            String selectcoloum=(view_table.getModel().getValueAt(row, 0).toString());
            //JOptionPane.showMessageDialog(null, selectcoloum);
            String sql="select * from customeAndSupplyer where id='"+selectcoloum+"'";
            post=con.prepareStatement(sql);
            rs=post.executeQuery();
            if(rs.next()) {
                String id,name,phone,email,paddress,peraddress,status;
            
                //id=rs.getString("id");
                name=rs.getString("name");
                phone=rs.getString("mobile");
                email=rs.getString("email");
                paddress=rs.getString("present_address");
                peraddress=rs.getString("permanent_address");
                status=rs.getString("member_type");

                txtId.setText(rs.getString("id"));
                txtName.setText(name);    
                txtMobile.setText(phone);
                txtEmail.setText(email);
                txtPresentAdd.setText(paddress);
                txtPermanentAdd.setText(peraddress);
                selectMemberType.setSelectedItem(status);
            }
        }
        catch(SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.WARNING_MESSAGE);  
        }
     }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtName = new java.awt.TextField();
        txtEmail = new java.awt.TextField();
        txtMobile = new java.awt.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPermanentAdd = new javax.swing.JTextArea();
        selectMemberType = new javax.swing.JComboBox();
        buttonadd = new javax.swing.JButton();
        editbutton = new javax.swing.JButton();
        deletebutton = new javax.swing.JButton();
        resetbutton = new javax.swing.JButton();
        viewbutton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPresentAdd = new javax.swing.JTextArea();
        txtId = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        view_table = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Customer/Supplyer Info");
        setEnabled(false);
        setFocusCycleRoot(false);
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);
        setVisible(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Name");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Mobile");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Email");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Present Address");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Member Type");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Permanent Address");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtMobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMobileActionPerformed(evt);
            }
        });

        txtPermanentAdd.setColumns(20);
        txtPermanentAdd.setRows(5);
        jScrollPane1.setViewportView(txtPermanentAdd);

        selectMemberType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Type", "Customer", "Supplyer" }));
        selectMemberType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMemberTypeActionPerformed(evt);
            }
        });

        buttonadd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonadd.setText("Add");
        buttonadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonaddActionPerformed(evt);
            }
        });

        editbutton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editbutton.setText("Edit");
        editbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbuttonActionPerformed(evt);
            }
        });

        deletebutton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deletebutton.setText("Delete");
        deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebuttonActionPerformed(evt);
            }
        });

        resetbutton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        resetbutton.setText("Reset");
        resetbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetbuttonActionPerformed(evt);
            }
        });

        viewbutton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        viewbutton.setText("View");
        viewbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewbuttonActionPerformed(evt);
            }
        });

        txtPresentAdd.setColumns(20);
        txtPresentAdd.setRows(5);
        jScrollPane2.setViewportView(txtPresentAdd);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonadd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deletebutton, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(viewbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMobile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(selectMemberType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectMemberType, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonadd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deletebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("View Customer");

        view_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        view_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(view_table);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(168, 168, 168))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 21, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtMobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMobileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMobileActionPerformed

    private void buttonaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonaddActionPerformed
        // TODO add your handling code here:
        insert();
       
    }//GEN-LAST:event_buttonaddActionPerformed

    private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebuttonActionPerformed
        delete();
        viewtable();
    }//GEN-LAST:event_deletebuttonActionPerformed

    private void resetbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetbuttonActionPerformed
         clear();
    }//GEN-LAST:event_resetbuttonActionPerformed

    private void editbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbuttonActionPerformed
        Update();
        viewtable();
    }//GEN-LAST:event_editbuttonActionPerformed

    private void viewbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewbuttonActionPerformed
       viewtable();
    }//GEN-LAST:event_viewbuttonActionPerformed

    private void selectMemberTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMemberTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMemberTypeActionPerformed

    private void view_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view_tableMouseClicked
        // TODO add your handling code here:
        TableClick();
    }//GEN-LAST:event_view_tableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonadd;
    private javax.swing.JButton deletebutton;
    private javax.swing.JButton editbutton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton resetbutton;
    private javax.swing.JComboBox selectMemberType;
    private java.awt.TextField txtEmail;
    private javax.swing.JTextField txtId;
    private java.awt.TextField txtMobile;
    private java.awt.TextField txtName;
    private javax.swing.JTextArea txtPermanentAdd;
    private javax.swing.JTextArea txtPresentAdd;
    private javax.swing.JTable view_table;
    private javax.swing.JButton viewbutton;
    // End of variables declaration//GEN-END:variables
}
