package SuperShop;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Emply_Salary extends javax.swing.JInternalFrame {

    Connection con =null;
    PreparedStatement post =null;
    ResultSet rs= null;
    static int count=0;
    
    
    public Emply_Salary() {
        initComponents();
        
        con = DBConnect.ConnectDb();
        EmployeeList();
       // autoId();
    }
      
    
 private void ViewTable()
 {
        try{
            
             String sql = "SELECT id,Date,Month,EmployeeName,PaymentType,Comment,Amount FROM employeesalary WHERE InvoiceNo='"+VoucherID.getText()+"'";
             PreparedStatement ps =con.prepareStatement(sql);
             ResultSet rss = ps.executeQuery(sql);
             viewTable.setModel(DbUtils.resultSetToTableModel(rss));
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
       
  }

    void totalSalary()
    {
         try {
            String sql = "SELECT SUM(Amount) AS 'total' FROM employeesalary WHERE InvoiceNo='"+VoucherID.getText()+"'";
            post = con.prepareStatement(sql);
            ResultSet res = post.executeQuery();
           
            
            if (res.next()) {
                String total = res.getString("total");
                totalSalary.setText(total);
            }
            res.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        } 
         
         finally {
            try {
                post.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
   }
    
   private void EmployeeList() {
        try {
            
            String sql = "SELECT * FROM employeeinfo WHERE employee_Status='Active' ";
            post = con.prepareStatement(sql);
            ResultSet res = post.executeQuery();
            EmployeeName.addItem("Select Employee");
            while (res.next()) {
                String emplyeename = res.getString("name");
                EmployeeName.addItem(emplyeename);
            }
            res.close();
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
                post.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
    
    private void PhoneNumber() {
        try {
            String sql = "SELECT * FROM employeeinfo WHERE name='"+EmployeeName.getSelectedItem()+"' ";
            post = con.prepareStatement(sql);
            ResultSet res = post.executeQuery();
          
            if (res.next()) {
                String emplyeephone = res.getString("mobile");
                EmployeePhone.setText(emplyeephone);
            }
            res.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                post.close();
            } catch (Exception e) {

            }
        }
    }
    
    void clear()
    {
        SelectMonth.setSelectedItem("Select Month");
        EmployeeName.setSelectedItem("Select Employee");
        EmployeePhone.setText("");
        PaymentType.setSelectedItem("Payment Type");
        txtAmount.setText("0.0");
        txtComment.setText("");
        
        totalSalary.setText("0.0");
         
        PaidAmount.setText("0.0");
            
        DueAmount.setText("0.0");
    }   
     
    void insert()
     {
            if (EmployeeName.getSelectedItem().equals("Select Employee")){
                JOptionPane.showMessageDialog(this,"Select Employee","Error",JOptionPane.ERROR_MESSAGE);
                return;
        }
              if (txtAmount.getText().equals("") || txtAmount.getText().equals("0.00") ){
                JOptionPane.showMessageDialog(this,"Enter Emount","Error",JOptionPane.ERROR_MESSAGE);
                return;
        }
              if (SelectMonth.getSelectedItem().equals("Select Month")){
                JOptionPane.showMessageDialog(this,"Select Month","Error",JOptionPane.ERROR_MESSAGE);
                return;
        }
                 if (PaymentType.getSelectedItem().equals("Payment Type")){
                JOptionPane.showMessageDialog(this,"Select Payment Type","Error",JOptionPane.ERROR_MESSAGE);
                return;
        }
              
        
              
            
        count++;
       
         java.util.Date date = EntyDate.getDate();
            // id.setText(""+date);
            SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy");

            String Entrydate = formate.format(date);
        
        try {
            //InsertController insertDalyCost = new InsertController();
            String sql = "INSERT INTO employeesalary (`id`,`InvoiceNo`,`Date`,`Month`,`EmployeeName`,`Phone`,`PaymentType`,`Amount`,`Comment`,`Admin`) VALUES("
                     + "'" + count + "',"
                      + "'" + VoucherID.getText() + "',"
                    
                    + "'" + Entrydate + "',"
                    + "'" + SelectMonth.getSelectedItem() + "',"
                    + "'" + EmployeeName.getSelectedItem() + "',"
              
                    + "'" + EmployeePhone.getText() + "',"
                    + "'" + PaymentType.getSelectedItem() + "',"
                    
                    + "'" + txtAmount.getText() + "',"
                   
                    + "'" + txtComment.getText() + "',"
                   
                     + "'Admin')";
          
           // JOptionPane.showMessageDialog(null, sql);
           
           post=con.prepareStatement(sql);
           post.execute();
           
            
                 JOptionPane.showMessageDialog(null, "Success");
                 PaymentType.setSelectedItem("Payment Type");
                    txtAmount.setText("0.0");
                    txtComment.setText("");
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
            
           
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        EntyDate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        EmployeeName = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        SelectMonth = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        EmployeePhone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        PaymentType = new javax.swing.JComboBox();
        txtAmount = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtComment = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        txtClear = new javax.swing.JButton();
        VoucherID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        Submitbutton = new javax.swing.JButton();
        totalSalary = new javax.swing.JTextField();
        PaidAmount = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        DueAmount = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);

        jPanel1.setBackground(java.awt.SystemColor.info);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Date");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Select Month");

        EmployeeName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmployeeNameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Phone");

        SelectMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", " " }));
        SelectMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectMonthActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Employee Name");

        EmployeePhone.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Payment Type");

        PaymentType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Payment Type", "Salary", "Bonus" }));
        PaymentType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentTypeActionPerformed(evt);
            }
        });

        txtAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAmountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAmountFocusLost(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Comment");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Amount");

        txtComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCommentActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        txtClear.setText("Clear");
        txtClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClearActionPerformed(evt);
            }
        });

        jLabel8.setText("Invoice No");

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtClear, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAmount)
                            .addComponent(EntyDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SelectMonth, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PaymentType, 0, 181, Short.MAX_VALUE)
                            .addComponent(VoucherID))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(EmployeePhone)
                            .addComponent(EmployeeName, 0, 215, Short.MAX_VALUE)
                            .addComponent(txtComment))
                        .addGap(90, 90, 90))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(VoucherID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(EntyDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(10, 10, 10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SelectMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(PaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EmployeePhone, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtComment, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtClear, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        viewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        viewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(viewTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        Submitbutton.setText("Submit");
        Submitbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitbuttonActionPerformed(evt);
            }
        });

        totalSalary.setEditable(false);
        totalSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalSalaryActionPerformed(evt);
            }
        });

        PaidAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaidAmountActionPerformed(evt);
            }
        });
        PaidAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PaidAmountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PaidAmountFocusLost(evt);
            }
        });
        PaidAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PaidAmountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PaidAmountKeyReleased(evt);
            }
        });

        jLabel9.setText("Total");

        jLabel10.setText("Paid");

        DueAmount.setEditable(false);

        jLabel11.setText("Due");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Submitbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(PaidAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addComponent(totalSalary))
                    .addComponent(DueAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(PaidAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Submitbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DueAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(2, 2, 2))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCommentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCommentActionPerformed

    private void EmployeeNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmployeeNameActionPerformed
        // TODO add your handling code here:
        PhoneNumber();
        PaymentType.setSelectedItem("Payment Type");
    }//GEN-LAST:event_EmployeeNameActionPerformed

    private void SelectMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectMonthActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_SelectMonthActionPerformed

    private void PaymentTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentTypeActionPerformed
        // TODO add your handling code here:
        
        if(PaymentType.getSelectedItem().equals("Salary"))
        {
             salarySelect();
        }
        else
        {
            txtAmount.setText("0.0");
        }
    }//GEN-LAST:event_PaymentTypeActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
 
        
        insert();
        ViewTable();
         totalSalary();
        
         
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClearActionPerformed
        // TODO add your handling code here:
         try
        {
            String sql = "DELETE FROM employeesalary WHERE InvoiceNo='"+VoucherID.getText()+"'";
            post = con.prepareStatement(sql);
   
                post.execute(); 
            
        }
        catch(Exception e)
        {
            
        }
          
        clear();
        ViewTable();
        
    }//GEN-LAST:event_txtClearActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        
   
         clear();
         
           ViewTable();
         
         
        
    }//GEN-LAST:event_btnNewActionPerformed

    private void SubmitbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitbuttonActionPerformed

        // TODO add your handling code here:
          if (PaidAmount.getText().equals("0.00") || PaidAmount.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Enter Paid Amount","Error",JOptionPane.ERROR_MESSAGE);
                return;
        }
          double due=Double.valueOf(DueAmount.getText());
          
           if (due>1)
           {
                JOptionPane.showMessageDialog(this,"Due salary not accepted  ","Error",JOptionPane.ERROR_MESSAGE);
                return;
              }  
           
          
          
         try
         {
             
             java.util.Date date = EntyDate.getDate();
            //id.setText(""+date);
            SimpleDateFormat formate = new SimpleDateFormat("MMM d, yyyy");
            //java.sql.Date date_sql=new java.sql.Date(date.getTime());
            String Getdate = formate.format(date);
            
            
            

            String sql = "INSERT INTO employeesalaryleadger (`EntryDate`,`InvoiceNo`,`EmployeeName`,`Phone`,`Month`,`Salary`,`Paid`,`DueAmount`) VALUES(?,?,?,?,?,?,?,?)";
            post = con.prepareStatement(sql);
             //JOptionPane.showMessageDialog(null, sql);
            post.setString(1, Getdate);
            
            post.setString(2, VoucherID.getText());
           
            post.setString(3, EmployeeName.getSelectedItem().toString());
            post.setString(4, EmployeePhone.getText());
            post.setString(5, SelectMonth.getSelectedItem().toString());
           
            post.setString(6, totalSalary.getText());
            post.setString(7, PaidAmount.getText());
            post.setString(8, DueAmount.getText());          
            post.execute();

        }
         catch (Exception e) {
           
         }
           clear();
         
           ViewTable();
     
    
    
    }//GEN-LAST:event_SubmitbuttonActionPerformed

    private void viewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewTableMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_viewTableMouseClicked

    private void PaidAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PaidAmountKeyPressed
        // TODO add your handling code here:
         try
        {
            double x =Double.valueOf(totalSalary.getText());
            double y = Double.valueOf(PaidAmount.getText());
            double z = x - y;
            DueAmount.setText(String.valueOf(z));
        }
        catch(Exception e)
        {
        }
    }//GEN-LAST:event_PaidAmountKeyPressed

    private void PaidAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PaidAmountKeyReleased
        // TODO add your handling code here:
          try
        {
            double x =Double.valueOf(totalSalary.getText());
            double y = Double.valueOf(PaidAmount.getText());
            double z = x - y;
            DueAmount.setText(String.valueOf(z));
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_PaidAmountKeyReleased

    private void PaidAmountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PaidAmountFocusGained
        // TODO add your handling code here:
         if("0.0".equals(PaidAmount.getText()))
        {
            PaidAmount.setText("");
            
        }else{
            PaidAmount.setText(PaidAmount.getText());
        }
    }//GEN-LAST:event_PaidAmountFocusGained

    private void PaidAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PaidAmountFocusLost
        // TODO add your handling code here:
         if("".equals(PaidAmount.getText()))
        {
            PaidAmount.setText("0.0");
            
        }else{
            PaidAmount.setText(PaidAmount.getText());
        }
    }//GEN-LAST:event_PaidAmountFocusLost

    private void PaidAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaidAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PaidAmountActionPerformed

    private void totalSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalSalaryActionPerformed

    private void txtAmountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAmountFocusGained
        // TODO add your handling code here:
         if("0.0".equals(txtAmount.getText()))
        {
            txtAmount.setText("");
            
        }else{
            txtAmount.setText(txtAmount.getText());
        }
    }//GEN-LAST:event_txtAmountFocusGained

    private void txtAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAmountFocusLost
        // TODO add your handling code here:
         if("".equals(txtAmount.getText()))
        {
            txtAmount.setText("0.0");
            
        }else{
            txtAmount.setText(txtAmount.getText());
        }
    }//GEN-LAST:event_txtAmountFocusLost

    
    private void salarySelect() {
        try {
            
            String sql = "SELECT * FROM employeeinfo WHERE name='"+EmployeeName.getSelectedItem()+"' and  mobile='"+EmployeePhone.getText()+"'";
            post = con.prepareStatement(sql);
            ResultSet res = post.executeQuery();
          
            if (res.next()) {
                String salary = res.getString("basic_Salary");
                txtAmount.setText(salary);
            }
            res.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                post.close();
            } catch (Exception e) {

            }
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DueAmount;
    private javax.swing.JComboBox EmployeeName;
    private javax.swing.JTextField EmployeePhone;
    private com.toedter.calendar.JDateChooser EntyDate;
    private javax.swing.JTextField PaidAmount;
    private javax.swing.JComboBox PaymentType;
    private javax.swing.JComboBox SelectMonth;
    private javax.swing.JButton Submitbutton;
    private javax.swing.JTextField VoucherID;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnNew;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JTextField totalSalary;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JButton txtClear;
    private javax.swing.JTextField txtComment;
    private javax.swing.JTable viewTable;
    // End of variables declaration//GEN-END:variables
}
