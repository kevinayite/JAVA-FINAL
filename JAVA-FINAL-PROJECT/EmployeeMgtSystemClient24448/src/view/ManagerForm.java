/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Chairman;
import model.Manager;
import service.ChairmanService;
import service.ManagerService;
import service.ChairmanService;
import org.dom4j.*;
import java.util.stream.*;
import org.dom4j.DocumentException;
import com.itextpdf.text.Document;
import com.itextpdf.text.*;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
/**
 *
 * @author JOHN SHADARY
 */
public class ManagerForm extends javax.swing.JFrame {
    DefaultTableModel tableModel = new DefaultTableModel();
    public int managerId;

    /**
     * Creates new form ManagerForm
     */
    public ManagerForm() {
        initComponents();
        addItemCombo();
        addColumnHeader();
        displayManager();
        clearTextFields();
    }
    private void addTableHeader(PdfPTable table) {
    Stream.of("ID", "Names of Manager", "Email", "Phone Number","Address" ,"CEO Names")
            .forEach(columnTitle -> {
                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(columnTitle));
                table.addCell(header);
            });
}
  
          private void addRows(PdfPTable table, List<Manager> managertList) {
        for (Manager manager : managertList) {
                           
            table.addCell(String.valueOf(manager.getManagerId()));
            table.addCell(manager.getNames());
            table.addCell(manager.getEmail());
            table.addCell(manager.getPhoneNumber());
            table.addCell(manager.getAddress());
            Chairman chairman = manager.getChairman();
            String chairmanName = "";
            if(chairman != null) {
          int chairmanId = chairman.getChairmanId();
          chairmanName = chairman.getNames();
            
        }
        table.addCell(chairmanName);
            
    }
}
          
          private void createReport(List<Manager> managerList) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                if (!file.getAbsolutePath().endsWith(".pdf")) {
                    file = new File(file.getAbsolutePath() + ".pdf");
                }

                Document document = new Document();
                try {
                    PdfWriter.getInstance(document, new FileOutputStream(file));
                    document.open();

                    PdfPTable table = new PdfPTable(6); 
                    addTableHeader(table);
                    addRows(table, managerList);

                    document.add(table);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, 
                            "Error generating report: " + ex.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    if (document != null) {
                        document.close();
                    }
                }

                JOptionPane.showMessageDialog(this, "Report generated successfully!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                    "Error generating report: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        private void addColumnHeader(){
            tableModel.addColumn("Manager ID");
            tableModel.addColumn("Names");
            tableModel.addColumn("Email");
            tableModel.addColumn("Phone Number");
            tableModel.addColumn("Address");
            tableModel.addColumn("CEO ");

            managerTable.setModel(tableModel);
    }
    public static boolean validateEmail(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.[a-zA-Z]{2,7})$";
    Pattern pattern = Pattern.compile(emailRegex);
    return pattern.matcher(email).matches();
}
    private void clearTextFields(){
        namesTxt.setText("");
        emailTxt.setText("");
        phoneNumberTxt.setText("");
        addressTxt.setText("");
        CEOCombo.setSelectedItem(null);
        
    }
    
    private void displayManager(){
        try{
            tableModel.setRowCount(0);
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            ManagerService service = (ManagerService) theRegistry.lookup("manager");
            Manager theManager = new Manager();
            List<Manager> managers = service.allManagers();
            for(Manager manager : managers){
                tableModel.addRow(new Object[]{
                    manager.getManagerId(),
                    manager.getNames(),
                    manager.getEmail(),
                    manager.getPhoneNumber(),
                    manager.getAddress(),
                    manager.getChairman()
                });
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    private void addItemCombo(){
        try{
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            ChairmanService service = (ChairmanService) theRegistry.lookup("chairman");
            List<Chairman> theChairmans = service.allChairmans();
            for(Chairman chairman : theChairmans){
                CEOCombo.addItem(chairman);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        previousBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        reportBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        namesTxt = new javax.swing.JTextField();
        emailTxt = new javax.swing.JTextField();
        phoneNumberTxt = new javax.swing.JTextField();
        addressTxt = new javax.swing.JTextField();
        CEOCombo = new javax.swing.JComboBox<>();
        registerBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        managerTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(648, 120));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("MANAGER MANAGEMENT");

        previousBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        previousBtn.setText("Previous");
        previousBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousBtnActionPerformed(evt);
            }
        });

        nextBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        nextBtn.setText("Next");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        reportBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        reportBtn.setText("Report");
        reportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(previousBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nextBtn)
                    .addComponent(reportBtn))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(previousBtn)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(nextBtn)
                        .addGap(18, 18, 18)
                        .addComponent(reportBtn)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Names");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Email");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Phone Number");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Address");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("CEO Names");

        namesTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                namesTxtKeyTyped(evt);
            }
        });

        phoneNumberTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneNumberTxtKeyTyped(evt);
            }
        });

        registerBtn.setBackground(new java.awt.Color(51, 51, 255));
        registerBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        registerBtn.setForeground(new java.awt.Color(255, 255, 255));
        registerBtn.setText("Register");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });

        updateBtn.setBackground(new java.awt.Color(51, 51, 255));
        updateBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(255, 0, 0));
        deleteBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        clearBtn.setBackground(new java.awt.Color(51, 51, 255));
        clearBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        refreshBtn.setBackground(new java.awt.Color(51, 51, 255));
        refreshBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        refreshBtn.setForeground(new java.awt.Color(255, 255, 255));
        refreshBtn.setText("Refresh");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(namesTxt)
                            .addComponent(emailTxt)
                            .addComponent(phoneNumberTxt)
                            .addComponent(addressTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registerBtn)
                            .addComponent(CEOCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(updateBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                        .addComponent(deleteBtn)
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(refreshBtn)
                        .addGap(58, 58, 58)
                        .addComponent(clearBtn)
                        .addGap(30, 30, 30))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(namesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(phoneNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addGap(31, 31, 31)
                .addComponent(addressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(clearBtn)
                        .addComponent(refreshBtn)))
                .addGap(10, 10, 10)
                .addComponent(CEOCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerBtn)
                    .addComponent(updateBtn)
                    .addComponent(deleteBtn))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/PictureManager.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        managerTable.setModel(new javax.swing.table.DefaultTableModel(
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
        managerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                managerTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(managerTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        // TODO add your handling code here:
        try{
            if(namesTxt.getText().trim().isEmpty() 
                || emailTxt.getText().trim().isEmpty() 
                || addressTxt.getText().trim().isEmpty()
		|| phoneNumberTxt.getText().trim().isEmpty()
                || CEOCombo.getSelectedItem() == null
                ){
            JOptionPane.showMessageDialog(this, "All fields are mandatory","Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
            if(!validateEmail(emailTxt.getText())){
            JOptionPane.showMessageDialog(this,
                    "Enter a valid email",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
            if(phoneNumberTxt.getText().length() < 10 || phoneNumberTxt.getText().length()> 10){
            JOptionPane.showMessageDialog(this,
                    "Phone Number should be  10 digits",
                    "Unacceptable Phone Number",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            ManagerService service = (ManagerService) theRegistry.lookup("manager");
            Manager theManager = new Manager();
            theManager.setNames(namesTxt.getText());
            theManager.setEmail(emailTxt.getText());
            theManager.setPhoneNumber(phoneNumberTxt.getText());
            theManager.setAddress(addressTxt.getText());
            Chairman theChairman = (Chairman) CEOCombo.getSelectedItem();
            theManager.setChairman(theChairman);
            
            Manager managerObj = service.recordManager(theManager);
            
            if(managerObj!=null){
                JOptionPane.showMessageDialog(this, "Data Saved!");
                displayManager();
                clearTextFields();
            }else{
                JOptionPane.showMessageDialog(this, "Data not Saved!");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_registerBtnActionPerformed

    private void managerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managerTableMouseClicked
        // TODO add your handling code here:
        try{
            int selectedRowIndex = managerTable.getSelectedRow();
            managerId = Integer.parseInt(tableModel.getValueAt(selectedRowIndex, 0).toString());
            namesTxt.setText(tableModel.getValueAt(selectedRowIndex, 1).toString());
            emailTxt.setText(tableModel.getValueAt(selectedRowIndex, 2).toString());
            phoneNumberTxt.setText(tableModel.getValueAt(selectedRowIndex, 3).toString());
            addressTxt.setText(tableModel.getValueAt(selectedRowIndex, 4).toString());
            String chairman = tableModel.getValueAt(selectedRowIndex, 5).toString();
            for(int i = 0; i < CEOCombo.getItemCount(); i++){
                if(CEOCombo.getItemAt(i).toString().equals(chairman)){
                    CEOCombo.setSelectedIndex(i);
                    break;
    }
  }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_managerTableMouseClicked

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        try{
            if(namesTxt.getText().trim().isEmpty() 
                || emailTxt.getText().trim().isEmpty() 
                || addressTxt.getText().trim().isEmpty()
		|| phoneNumberTxt.getText().trim().isEmpty()
                || CEOCombo.getSelectedItem() == null
                ){
            JOptionPane.showMessageDialog(this, "All fields are mandatory","Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
            if(!validateEmail(emailTxt.getText())){
            JOptionPane.showMessageDialog(this,
                    "Enter a valid email",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
            if(phoneNumberTxt.getText().length() < 10 || phoneNumberTxt.getText().length()> 10){
            JOptionPane.showMessageDialog(this,
                    "Phone Number should be  10 digits",
                    "Unacceptable Phone Number",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
            int option = JOptionPane.showConfirmDialog(this, "Are you sure to edit this record", "Warning", JOptionPane.YES_NO_OPTION);
            if(option ==0){
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            ManagerService service = (ManagerService) theRegistry.lookup("manager");
            Manager theManager = new Manager();
            theManager.setManagerId(managerId);
            theManager.setNames(namesTxt.getText());
            theManager.setEmail(emailTxt.getText());
            theManager.setPhoneNumber(phoneNumberTxt.getText());
            theManager.setAddress(addressTxt.getText());
            Chairman theChairman = (Chairman) CEOCombo.getSelectedItem();
            theManager.setChairman(theChairman);
            
            Manager managerObj = service.updateManager(theManager);
            
            if(managerObj!=null){
                JOptionPane.showMessageDialog(this, "Data Updated!");
                displayManager();
                clearTextFields();
            }else{
                JOptionPane.showMessageDialog(this, "Data not Updated!");
            }
            }else return;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        try{
            if(namesTxt.getText().trim().isEmpty() 
                || emailTxt.getText().trim().isEmpty() 
                || addressTxt.getText().trim().isEmpty()
		|| phoneNumberTxt.getText().trim().isEmpty()
                || CEOCombo.getSelectedItem() == null
                ){
            JOptionPane.showMessageDialog(this, "All fields are mandatory","Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
            int option = JOptionPane.showConfirmDialog(this, "Are you sure to edit this record", "Warning", JOptionPane.YES_NO_OPTION);
            if(option ==0){
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            ManagerService service = (ManagerService) theRegistry.lookup("manager");
            Manager theManager = new Manager();
            theManager.setManagerId(managerId);
            theManager.setNames(namesTxt.getText());
            theManager.setEmail(emailTxt.getText());
            theManager.setPhoneNumber(phoneNumberTxt.getText());
            theManager.setAddress(addressTxt.getText());
            Chairman theChairman = (Chairman) CEOCombo.getSelectedItem();
            theManager.setChairman(theChairman);
            
            Manager managerObj = service.deleteManager(theManager);
            
            if(managerObj!=null){
                JOptionPane.showMessageDialog(this, "Data Deleted!");
                displayManager();
                clearTextFields();
            }else{
                JOptionPane.showMessageDialog(this, "Data not Deleted!");
            }
            }else return;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // TODO add your handling code here:
        clearTextFields();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        // TODO add your handling code here:
        displayManager();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void namesTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namesTxtKeyTyped
        // TODO add your handling code here:
        char keyChar = evt.getKeyChar();
        boolean digit = Character.isDigit(keyChar);
        if(digit){
            evt.consume();
            
        }
    }//GEN-LAST:event_namesTxtKeyTyped

    private void phoneNumberTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneNumberTxtKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
        
    }//GEN-LAST:event_phoneNumberTxtKeyTyped

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        // TODO add your handling code here:
        DepartmentForm dF = new DepartmentForm();
        dF.setVisible(true);
        dispose();
    }//GEN-LAST:event_nextBtnActionPerformed

    private void previousBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousBtnActionPerformed
        // TODO add your handling code here:
        CEOForm cF = new CEOForm();
        cF.setVisible(true);
        dispose();
    }//GEN-LAST:event_previousBtnActionPerformed

    private void reportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBtnActionPerformed
        // TODO add your handling code here:
        
try{
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            ManagerService service = (ManagerService) theRegistry.lookup("manager");
            List<Manager> managerList = service.allManagers();
            createReport(managerList);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_reportBtnActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ManagerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Chairman> CEOCombo;
    private javax.swing.JTextField addressTxt;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable managerTable;
    private javax.swing.JTextField namesTxt;
    private javax.swing.JButton nextBtn;
    private javax.swing.JTextField phoneNumberTxt;
    private javax.swing.JButton previousBtn;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton registerBtn;
    private javax.swing.JButton reportBtn;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
