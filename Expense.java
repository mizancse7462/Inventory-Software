
package SuperShop;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

public class Expense extends javax.swing.JInternalFrame {
    Connection con = null;
    PreparedStatement post = null;
    ResultSet rs = null;

    public Expense() {
        initComponents();
         con = DBConnect.ConnectDb();
         autoId();
         ExpenceSourse();
         viewtable();
         hiddenId.setVisible(false);
         
    }
    
      public void autoId() {
        try {
             invoiceNo.setText("EVNO-01");
            String sql = "SELECT MAX(ID) AS 'id' FROM expensesource";
            post = con.prepareStatement(sql);
            ResultSet rs = post.executeQuery();
            if(rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                id++;
                if( id<=9)
                invoiceNo.setText("EVNO-0" + "" + Integer.toString(id));
                else
                invoiceNo.setText("EVNO-" + "" + Integer.toString(id));
                

               
            }
            
             if (invoiceNo.getText().equals("")) {
                    invoiceNo.setText("EVNO-01");
                    
            }
            rs.close();

        } catch (Exception e) {
           // JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                post.close();
            } catch (Exception e) {

            }
        }
      }
      
       private void ExpenceSourse(){
        try {
            selectExpenceSourse.removeAllItems();
            
            String sql = "SELECT * FROM incomesource WHERE select_Type='Expense'";
              selectExpenceSourse.addItem("Select Title");
            post = con.prepareStatement(sql);
            ResultSet rs = post.executeQuery();
            if (rs.next()) {
                selectExpenceSourse.addItem(rs.getString("source_Title"));
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
       
       public void clear()
       {
           hiddenId.setText("");
           invoiceNo.setText("");
           VoucherId.setText("");
           txtDetails.setText("");
           txtAmount.setText("");
            selectExpenceSourse.setSelectedItem("Select Title");
              btnAdd.setVisible(true);
               ExpenceSourse();
              
                 invoiceNo.setText("");
                 autoId();
       }    
       
       void search()
       {
            java.util.Date date = searchDate.getDate();
            SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy");
            String searchDate = formate.format(date);
            try{
            String sql = "SELECT VoucherNo,BillNo,EntryDate,ExpenseSource,Amount FROM expensesource where EntryDate='"+searchDate+"' ";
           PreparedStatement ps =con.prepareStatement(sql);
           ResultSet rss = ps.executeQuery(sql);
            viewTable.setModel(DbUtils.resultSetToTableModel(rss));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
            
       }
       void invoice()
       {
           
          
          DBConnect con_db=new DBConnect();
            
            Connection con=con_db.ConnectDb();
               String path="E:\\AccountsSoftware\\src\\accountssoftware\\";
            String report =path+"Report\\DalyConstReport.jrxml";
     
      
        try {
            
            

            JasperDesign jd = JRXmlLoader.load(report);

            String sql = "SELECT * FROM expensesource WHERE  VoucherNo='"+invoiceNo.getText()+"'";
            
        //   JOptionPane.showMessageDialog(null,  "SELECT * FROM expensesource WHERE  VoucherNo='"+invoiceNo.getText()+"'");

            JRDesignQuery deq = new JRDesignQuery();

            deq.setText(sql);

            jd.setQuery(deq);

            JasperReport jr = JasperCompileManager.compileReport(jd);

            JasperPrint pp = JasperFillManager.fillReport(jr, null, con);

            //JasperViewer.viewReport(pp);
            JFrame frame = new JFrame();
            frame.getContentPane().add(new JRViewer(pp));
            frame.pack();
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
            frame.setSize(1000, 800);
            frame.setVisible(true);

        } catch (JRException ex) {
           // Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
      
            System.out.println(ex);
        }
       }
    void Insert()
    {
        java.util.Date date = EntryDate.getDate();
        SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy");
        String Entrydate = formate.format(date);

        if (selectExpenceSourse.getSelectedItem().equals("Select Title")) {
            JOptionPane.showMessageDialog(this, "Select Title", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (invoiceNo.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Invoice No", "Error", JOptionPane.ERROR_MESSAGE);
              return;
        }

        if (VoucherId.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Voucher No", "Error", JOptionPane.ERROR_MESSAGE);
              return;
        }

         if (hiddenId.getText().equals("")) {
         }
         else
         {
           
              return;
        }
          
        try {
            String sql = "INSERT INTO expensesource(`VoucherNo`,`BillNo`,`EntryDate`,`ExpenseSource`,`Details`,`Amount`,`EntryInfo`) VALUES(?,?,?,?,?,?,?)";
            post = con.prepareStatement(sql);
           
            post.setString(1, invoiceNo.getText());
            post.setString(2, VoucherId.getText());
            post.setString(3, Entrydate);
            post.setString(4, selectExpenceSourse.getSelectedItem().toString());

            post.setString(5, txtDetails.getText());

            post.setString(6, txtAmount.getText());
            post.setString(7, "Admin");

            post.execute();

            JOptionPane.showMessageDialog(null, "Inserted Successfully");
            invoice();
            clear();
            autoId();
            viewtable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void viewtable()
 {
        try{
            String sql = "SELECT VoucherNo,BillNo,EntryDate,ExpenseSource,Amount FROM expensesource";
           PreparedStatement ps =con.prepareStatement(sql);
           ResultSet rss = ps.executeQuery(sql);
            viewTable.setModel(DbUtils.resultSetToTableModel(rss));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
        finally{
            
            try{
               
            }
            catch(Exception e){
            }
        }  
   }
    
      void  TableClick()
    {
        try
        {
            int row=viewTable.getSelectedRow();
            String selectcoloum=(viewTable.getModel().getValueAt(row, 0).toString());
            String sql="select * from expensesource where VoucherNo='"+selectcoloum+"'";
            post = con.prepareStatement(sql); 
            rs = post.executeQuery();
      
            if(rs.next()) {      
                    hiddenId.setText(rs.getString("ID"));   
                   invoiceNo.setText(rs.getString("VoucherNo"));
                   VoucherId.setText(rs.getString("BillNo"));
                   txtDetails.setText(rs.getString("Details"));
                   txtAmount.setText(rs.getString("Amount"));
                   ExpenceSourse();
                    selectExpenceSourse.setSelectedItem(rs.getString("ExpenseSource"));
                    btnAdd.setVisible(false);
                
            }
        }
        catch(SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.WARNING_MESSAGE);  
        }
        finally{
            
            try{
                post.close();
                rs.close();
            }
            catch(Exception e){
            }
        }  
     }
      
      
      
void delete(){
        try{
            
        String sql="DELETE FROM expensesource  WHERE   ID='"+hiddenId.getText()+"'";
        post=con.prepareStatement(sql);
       post.execute();
        JOptionPane.showMessageDialog(null, " Deleted Successfully");
        clear();
        viewtable();
        autoId();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"ERROR", " Delete Unsuccessful",JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        EntryDate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        selectExpenceSourse = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDetails = new javax.swing.JTextArea();
        btnAdd = new javax.swing.JButton();
        hiddenId = new javax.swing.JTextField();
        btnAdd1 = new javax.swing.JButton();
        invoiceNo = new javax.swing.JTextField();
        VoucherId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        searchDate = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        viewTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Daly Expense");
        setAutoscrolls(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLayer(1);
        setVisible(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Date");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Voucher No.");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Details");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Expense Source");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Amount");

        txtDetails.setColumns(20);
        txtDetails.setRows(5);
        jScrollPane1.setViewportView(txtDetails);

        btnAdd.setText("Add with invoice");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnAdd1.setText("Delete");
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Invoice No");

        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Save");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel2)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(40, 40, 40)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(VoucherId)
                            .addComponent(EntryDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAmount)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                            .addComponent(selectExpenceSourse, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hiddenId)
                            .addComponent(invoiceNo)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(hiddenId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(EntryDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectExpenceSourse, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(VoucherId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel3)))
                    .addComponent(jLabel2))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jButton1.setText("Get Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
        jScrollPane3.setViewportView(viewTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(searchDate, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchDate, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
         Insert();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void viewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewTableMouseClicked
        // TODO add your handling code here:
        TableClick();
    }//GEN-LAST:event_viewTableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        search();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        java.util.Date date = searchDate.getDate();
            SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy");
            String searchDate = formate.format(date);
            
          DBConnect con_db=new DBConnect();
            
            Connection con=con_db.ConnectDb();
            String path="E:\\AccountsSoftware\\src\\accountssoftware\\";
            String report =path+"Report\\DalyConstReport.jrxml";
            
       
        try {
            
            

            JasperDesign jd = JRXmlLoader.load(report);

            String sql = "SELECT * FROM expensesource WHERE  EntryDate='"+searchDate+"'";
            
           

            JRDesignQuery deq = new JRDesignQuery();

            deq.setText(sql);

            jd.setQuery(deq);

            JasperReport jr = JasperCompileManager.compileReport(jd);

            JasperPrint pp = JasperFillManager.fillReport(jr, null, con);

            //JasperViewer.viewReport(pp);
            JFrame frame = new JFrame();
            frame.getContentPane().add(new JRViewer(pp));
            frame.pack();
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
            frame.setSize(1000, 800);
            frame.setVisible(true);

        } catch (JRException ex) {
           // Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
      
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
     
        java.util.Date date = EntryDate.getDate();
        SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy");
        String Entrydate = formate.format(date);

        if (selectExpenceSourse.getSelectedItem().equals("Select Title")) {
            JOptionPane.showMessageDialog(this, "Select Title", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (invoiceNo.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Invoice No", "Error", JOptionPane.ERROR_MESSAGE);
              return;
        }

        if (VoucherId.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Voucher No", "Error", JOptionPane.ERROR_MESSAGE);
              return;
        }

         if (hiddenId.getText().equals("")) {
         }
         else
         {
           
              return;
        }
          
        try {
            String sql = "INSERT INTO expensesource(`VoucherNo`,`BillNo`,`EntryDate`,`ExpenseSource`,`Details`,`Amount`,`EntryInfo`) VALUES(?,?,?,?,?,?,?)";
            post = con.prepareStatement(sql);
           
            post.setString(1, invoiceNo.getText());
            post.setString(2, VoucherId.getText());
            post.setString(3, Entrydate);
            post.setString(4, selectExpenceSourse.getSelectedItem().toString());

            post.setString(5, txtDetails.getText());

            post.setString(6, txtAmount.getText());
            post.setString(7, "Admin");

            post.execute();

            JOptionPane.showMessageDialog(null, "Inserted Successfully");
         
            clear();
            autoId();
            viewtable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser EntryDate;
    private javax.swing.JTextField VoucherId;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd1;
    private javax.swing.JTextField hiddenId;
    private javax.swing.JTextField invoiceNo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser searchDate;
    private javax.swing.JComboBox selectExpenceSourse;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextArea txtDetails;
    private javax.swing.JTable viewTable;
    // End of variables declaration//GEN-END:variables
}
