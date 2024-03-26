/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Chairman;
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

/**
 *
 * @author JOHN SHADARY
 */
public class CEOForm extends javax.swing.JFrame {
    DefaultTableModel tableModel = new DefaultTableModel();
    public int chairmanId;

    /**
     * Creates new form CEOForm
     */
    public CEOForm() {
        initComponents();
        addColumns();
        displayCEO();
        clearTextFields();
        
    }
    
    private void addTableHeader(PdfPTable table) {
    Stream.of("ID", "Names", "Email", "Phone Number", "Address")
            .forEach(columnTitle -> {
                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(columnTitle));
                table.addCell(header);
            });
}

    private void addRows(PdfPTable table, List<Chairman> chairmanList) {
        for (Chairman chair : chairmanList) {
            table.addCell(String.valueOf(chair.getChairmanId()));
            table.addCell(chair.getNames());
            table.addCell(chair.getEmail());
            table.addCell(chair.getPhoneNumber());
            table.addCell(chair.getAddress());
    }
}
    
    
    private void createReport(List<Chairman> chairmanList) {
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

                PdfPTable table = new PdfPTable(5); 
                addTableHeader(table);
                addRows(table, chairmanList);

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
    
    public static boolean validateEmail(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.[a-zA-Z]{2,7})$";
    Pattern pattern = Pattern.compile(emailRegex);
    return pattern.matcher(email).matches();
}
    
    public void Check() {        
        if(namesTxt.getText().trim().isEmpty() 
                || emailTxt.getText().trim().isEmpty() 
                || addressTxt.getText().trim().isEmpty()
		|| phoneNumberTxt.getText().trim().isEmpty()
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
                    "Password should be 10 digits",
                    "Unacceptable Phone Number",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
     
        
    }
    private void addColumns(){
        tableModel.addColumn("Chairman ID");
        tableModel.addColumn("Names");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Address");
        CEOTable.setModel(tableModel);
    }
    private void clearTextFields(){
        namesTxt.setText("");
        emailTxt.setText("");
        phoneNumberTxt.setText("");
        addressTxt.setText("");
    }

    private void displayCEO(){
        try{
            tableModel.setRowCount(0);
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            ChairmanService service = (ChairmanService) theRegistry.lookup("chairman");
            Chairman theChairman = new Chairman();
            List<Chairman> chairmans = service.allChairmans();
            for(Chairman chairman : chairmans){
                tableModel.addRow(new Object[]{
                    chairman.getChairmanId(),
                    chairman.getNames(),
                    chairman.getEmail(),
                    chairman.getPhoneNumber(),
                    chairman.getAddress()
                    
                });
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
        jLabel6 = new javax.swing.JLabel();
        nextBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        namesTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        emailTxt = new javax.swing.JTextField();
        phoneNumberTxt = new javax.swing.JTextField();
        addressTxt = new javax.swing.JTextField();
        registerBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CEOTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        reportBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CEO MANAGEMENT");

        nextBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        nextBtn.setText("Next");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181)
                .addComponent(nextBtn)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextBtn))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Names");

        namesTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                namesTxtKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Email");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Phone Number");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Address");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(registerBtn)
                        .addGap(40, 40, 40)
                        .addComponent(updateBtn))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(namesTxt)
                        .addComponent(phoneNumberTxt)
                        .addComponent(emailTxt)
                        .addComponent(addressTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteBtn)
                    .addComponent(clearBtn))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(namesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(phoneNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerBtn)
                    .addComponent(updateBtn)
                    .addComponent(deleteBtn))
                .addGap(30, 30, 30))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        CEOTable.setModel(new javax.swing.table.DefaultTableModel(
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
        CEOTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CEOTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(CEOTable);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/CEO.png"))); // NOI18N

        reportBtn.setBackground(new java.awt.Color(51, 51, 255));
        reportBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        reportBtn.setForeground(new java.awt.Color(255, 255, 255));
        reportBtn.setText("Report");
        reportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(reportBtn)
                .addGap(53, 53, 53))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reportBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        // Validations
//        Check();
        
        try{
            if(namesTxt.getText().trim().isEmpty() 
                || emailTxt.getText().trim().isEmpty() 
                || addressTxt.getText().trim().isEmpty()
		|| phoneNumberTxt.getText().trim().isEmpty()
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
            // Create Registry
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            ChairmanService service = (ChairmanService) theRegistry.lookup("chairman");
            Chairman theChairman = new Chairman();
            
            theChairman.setNames(namesTxt.getText());
            theChairman.setEmail(emailTxt.getText());
            theChairman.setPhoneNumber(phoneNumberTxt.getText());
            theChairman.setAddress(addressTxt.getText());
            Chairman chairmanObj = service.recordChairman(theChairman);
            if(chairmanObj!=null){
                JOptionPane.showMessageDialog(this, "Data Saved!");
                displayCEO();
                clearTextFields();
            }else{
                JOptionPane.showMessageDialog(this, "Data not saved!");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }//GEN-LAST:event_registerBtnActionPerformed

    private void CEOTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CEOTableMouseClicked
        // TODO add your handling code here:
        try{
            int selectedRowIndex = CEOTable.getSelectedRow();
        chairmanId = Integer.parseInt(tableModel.getValueAt(selectedRowIndex, 0).toString());
        namesTxt.setText(tableModel.getValueAt(selectedRowIndex, 1).toString());
        emailTxt.setText(tableModel.getValueAt(selectedRowIndex, 2).toString());
        phoneNumberTxt.setText(tableModel.getValueAt(selectedRowIndex, 3).toString());
        addressTxt.setText(tableModel.getValueAt(selectedRowIndex, 4).toString());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_CEOTableMouseClicked

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        try{
            if(namesTxt.getText().trim().isEmpty() 
                || emailTxt.getText().trim().isEmpty() 
                || addressTxt.getText().trim().isEmpty()
		|| phoneNumberTxt.getText().trim().isEmpty()
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
                    "Phone Number should be at least more than 10 digits",
                    "Unacceptable Phone Number",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
                
            
            int option = JOptionPane.showConfirmDialog(this, "Are you sure to edit this record", "Warning", JOptionPane.YES_NO_OPTION);
            if(option==0){
                Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
                ChairmanService service = (ChairmanService) theRegistry.lookup("chairman");
                Chairman theChairman = new Chairman();
                theChairman.setChairmanId(chairmanId);
                theChairman.setNames(namesTxt.getText());
                theChairman.setEmail(emailTxt.getText());
                theChairman.setPhoneNumber(phoneNumberTxt.getText());
                theChairman.setAddress(addressTxt.getText());
                
                Chairman chairmanObj = service.updateChairman(theChairman);    
                if(chairmanObj!= null){
                    JOptionPane.showMessageDialog(this, "Data Updated");
                    displayCEO();
                    clearTextFields();
                }else{
                    JOptionPane.showMessageDialog(this, "Data not Updated");
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
                ){
            JOptionPane.showMessageDialog(this, "All fields are mandatory","Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
                
            
            int option = JOptionPane.showConfirmDialog(this, "Are you sure to delete this record", "Warning", JOptionPane.YES_NO_OPTION);
            if(option==0){
                Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
                ChairmanService service = (ChairmanService) theRegistry.lookup("chairman");
                Chairman theChairman = new Chairman();
                theChairman.setChairmanId(chairmanId);
                theChairman.setNames(namesTxt.getText());
                theChairman.setEmail(emailTxt.getText());
                theChairman.setPhoneNumber(phoneNumberTxt.getText());
                theChairman.setAddress(addressTxt.getText());
                
                Chairman chairmanObj = service.deleteChairman(theChairman);
                if(chairmanObj!= null){
                    JOptionPane.showMessageDialog(this, "Data deleted");
                    displayCEO();
                    clearTextFields();
                }else{
                    JOptionPane.showMessageDialog(this, "Data not deleted");
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
        ManagerForm mF = new ManagerForm();
        mF.setVisible(true);
        dispose();
    }//GEN-LAST:event_nextBtnActionPerformed

    private void reportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBtnActionPerformed
        // TODO add your handling code here:
        try{
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            ChairmanService service = (ChairmanService) theRegistry.lookup("chairman");
            List<Chairman> chairmanList = service.allChairmans();
            createReport(chairmanList);
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
            java.util.logging.Logger.getLogger(CEOForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CEOForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CEOForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CEOForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CEOForm().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CEOTable;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField namesTxt;
    private javax.swing.JButton nextBtn;
    private javax.swing.JTextField phoneNumberTxt;
    private javax.swing.JButton registerBtn;
    private javax.swing.JButton reportBtn;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
