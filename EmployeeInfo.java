package SuperShop;
import SuperShop.DBConnect;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;



public class EmployeeInfo extends javax.swing.JInternalFrame {
    Connection con =null;
    PreparedStatement post =null;
    ResultSet rs= null;
    
    public EmployeeInfo() {
        initComponents();
        con = DBConnect.ConnectDb();
        viewtable();
    }


    void clear(){
       txtId.setText("");
       txtName.setText("");
       txtDesignation.setText("");
       txtMobile.setText("");
       txtEmail.setText("");
       txtAddress.setText("");
       txtJoinDate.setText("");
       txtQualification.setText("");
       txtSalary.setText("");
       EmployeeType.setSelectedItem("Select Type");
   }
    
    void insert()
    {   
        if (txtId.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Insert the ID","Error",JOptionPane.ERROR_MESSAGE);
                return;
        }
       
        if (txtName.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Insert the Name","Error",JOptionPane.ERROR_MESSAGE);
                return;
        }

        if (EmployeeType.getSelectedItem().equals("Select Type")){
                JOptionPane.showMessageDialog(this,"Select Employee type","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
        
        try
        {
            String sql="INSERT INTO employeeinfo(`id`,`name`,`designation`,`mobile`,`email`,`address`,`joining_Date`,`educational_Qualification`,`basic_Salary`,`employee_Status`) VALUES (?,?,?,?,?,?,?,?,?,?)";   
            post=con.prepareStatement(sql);
            
            post.setString(1,txtId.getText());
            post.setString(2,txtName.getText());
            post.setString(3,txtDesignation.getText());
            post.setString(4,txtMobile.getText());
            post.setString(5,txtEmail.getText());
            post.setString(6,txtAddress.getText());
            post.setString(7,txtJoinDate.getText());
            post.setString(8,txtQualification.getText());
            post.setString(9,txtSalary.getText());
            post.setString(10,EmployeeType.getSelectedItem().toString());
            
            post.execute();
              
             JOptionPane.showMessageDialog(null, "Employee Information Inserted Successfully");
             clear();
             viewtable();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"ERROR", "Employee Information Insert Unsuccessful", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    void delete(){
        try{
            
        String sql="DELETE FROM employeeinfo  WHERE id=? and name=?";
        post=con.prepareStatement(sql);
        post.setString(1,txtId.getText());
        post.setString(2,txtName.getText());
      
        
        post.execute();
        JOptionPane.showMessageDialog(null, "Employee Information Deleted Successfully");
        clear();
        viewtable();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"ERROR", "Employee Information Delete Unsuccessful",JOptionPane.ERROR_MESSAGE);
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
   
        if (EmployeeType.getSelectedItem().equals("Select Type")){
                JOptionPane.showMessageDialog(this,"Select Employee type","Error",JOptionPane.ERROR_MESSAGE);
                return;
        }
       
        try
        {
            String sql="UPDATE employeeinfo SET name=?,designation=?,mobile=?,email=?,address=?,joining_Date=?,educational_Qualification=?,basic_Salary=?,employee_Status=? where id=? ";   
            post=con.prepareStatement(sql);
            
          
            post.setString(1,txtName.getText());
            post.setString(2,txtDesignation.getText());
            post.setString(3,txtMobile.getText());
            post.setString(4,txtEmail.getText());
            post.setString(5,txtAddress.getText());
            post.setString(6,txtJoinDate.getText());
            post.setString(7,txtQualification.getText());
            post.setString(8,txtSalary.getText());
            post.setString(9,EmployeeType.getSelectedItem().toString());
            post.setString(10,txtId.getText());
           
            post.execute();
            JOptionPane.showMessageDialog(null, "Employee Information Updated Successfully");
            clear();
            viewtable();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"ERROR", "Employee Information Update Unsuccessful", JOptionPane.ERROR_MESSAGE);
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
            String sql = "SELECT id as 'ID',name as 'Name',mobile as 'Mobile',basic_Salary as 'Basic Salary',employee_Status as 'Employee Status' FROM `employeeinfo` order by employee_Status ASC"; 
            post =con.prepareStatement(sql);
            rs = post.executeQuery(sql);
            employeeInfoTable.setModel(DbUtils.resultSetToTableModel(rs));
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
            int row=employeeInfoTable.getSelectedRow();
            String selectcoloum=(employeeInfoTable.getModel().getValueAt(row, 0).toString());
            //JOptionPane.showMessageDialog(null, selectcoloum);
            String sql="select * from employeeinfo where id='"+selectcoloum+"'";
            post=con.prepareStatement(sql);
            rs=post.executeQuery();
            while(rs.next()) {
                String id,name,designation,phone,email,address,joinDate,eduQualification ,salary,status;
            
                id=rs.getString("id");
                name=rs.getString("name");
                designation=rs.getString("designation");
                phone=rs.getString("mobile");
                email=rs.getString("email");
                address=rs.getString("address");
                joinDate=rs.getString("joining_Date");
                eduQualification=rs.getString("educational_Qualification");
                salary=rs.getString("basic_Salary");
                status=rs.getString("employee_Status");

                txtId.setText(id);
                txtName.setText(name);
                txtDesignation.setText(designation);
                txtMobile.setText(phone);
                txtEmail.setText(email);
                txtAddress.setText(address);
                txtJoinDate.setText(joinDate);
                txtQualification.setText(eduQualification);
                txtSalary.setText(salary);
                EmployeeType.setSelectedItem(status); 
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDesignation = new java.awt.TextField();
        txtName = new java.awt.TextField();
        Addbutton = new javax.swing.JButton();
        Editbutton = new javax.swing.JButton();
        Deletebutton = new javax.swing.JButton();
        viewButton = new javax.swing.JButton();
        ResetButton = new javax.swing.JButton();
        txtId = new java.awt.TextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        EmployeeType = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtMobile = new java.awt.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        txtJoinDate = new java.awt.TextField();
        txtSalary = new java.awt.TextField();
        txtEmail = new java.awt.TextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtQualification = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        employeeInfoTable = new javax.swing.JTable();
        ViewEmployee = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Employee Info");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Designation");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("ID");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Mobile");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Email");

        Addbutton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Addbutton.setText("Add");
        Addbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddbuttonActionPerformed(evt);
            }
        });

        Editbutton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Editbutton.setText("Edit");
        Editbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditbuttonActionPerformed(evt);
            }
        });

        Deletebutton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Deletebutton.setText("Delete");
        Deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletebuttonActionPerformed(evt);
            }
        });

        viewButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        viewButton.setText("View");
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        ResetButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResetButton.setText("Reset");
        ResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Name");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Address");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Joining Date");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Educational Qualification");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Basic Salary");

        EmployeeType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type", "Active", "Inactive" }));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Employee Status");

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jScrollPane1.setViewportView(txtAddress);

        txtSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalaryActionPerformed(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtQualification.setColumns(20);
        txtQualification.setRows(5);
        jScrollPane2.setViewportView(txtQualification);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Addbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Editbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Deletebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtJoinDate, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                            .addComponent(EmployeeType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSalary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDesignation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMobile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                            .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDesignation, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtJoinDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(EmployeeType, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Addbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Editbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Deletebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        employeeInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        employeeInfoTable.setCellSelectionEnabled(true);
        employeeInfoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeInfoTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(employeeInfoTable);
        employeeInfoTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        ViewEmployee.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ViewEmployee.setText("View Employee Info");

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ViewEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddbuttonActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_AddbuttonActionPerformed

    private void EditbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditbuttonActionPerformed
        Update();
        viewtable();
    }//GEN-LAST:event_EditbuttonActionPerformed

    private void DeletebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletebuttonActionPerformed
        delete();
    }//GEN-LAST:event_DeletebuttonActionPerformed

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        // TODO add your handling code here:
        viewtable();
    }//GEN-LAST:event_viewButtonActionPerformed

    private void ResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetButtonActionPerformed
        clear();
    }//GEN-LAST:event_ResetButtonActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalaryActionPerformed

    private void employeeInfoTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeInfoTableMouseClicked
        // TODO add your handling code here:
        TableClick();
    }//GEN-LAST:event_employeeInfoTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Addbutton;
    private javax.swing.JButton Deletebutton;
    private javax.swing.JButton Editbutton;
    private javax.swing.JComboBox<String> EmployeeType;
    private javax.swing.JButton ResetButton;
    private javax.swing.JLabel ViewEmployee;
    private javax.swing.JTable employeeInfoTable;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txtAddress;
    private java.awt.TextField txtDesignation;
    private java.awt.TextField txtEmail;
    private java.awt.TextField txtId;
    private java.awt.TextField txtJoinDate;
    private java.awt.TextField txtMobile;
    private java.awt.TextField txtName;
    private javax.swing.JTextArea txtQualification;
    private java.awt.TextField txtSalary;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
}
