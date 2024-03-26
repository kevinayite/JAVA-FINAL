/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Department;
import model.Manager;
import service.DepartmentService;
import service.ManagerService;

/**
 *
 * @author JOHN SHADARY
 */
public class DepartmentForm extends javax.swing.JFrame {
    DefaultTableModel tableModel = new DefaultTableModel();
    

    /**
     * Creates new form DepartmentForm
     */
    public DepartmentForm() {
        initComponents();
        addItemCombo();
        addColumnNames();
        displayDppts();
        clearTextFields();
    }
    
    
    private void addTableHeader(PdfPTable table) {
    Stream.of(" Depatment ID", "Name of The Department",  "Manager's Names")
            .forEach(columnTitle -> {
                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(columnTitle));
                table.addCell(header);
            });
}

    private void addRows(PdfPTable table, List<Department> departmentList) {
        for (Department dep : departmentList) {
                           
            table.addCell(dep.getDepId());
            table.addCell(dep.getDepName());
            Manager manager = dep.getManager();
            String managerName = "";
            if(manager != null) {
          int managerId = manager.getManagerId();
          managerName = manager.getNames();
            
        }
        table.addCell(managerName);
            
    }
}
    
    
    private void createReport(List<Department> departmentList) {
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

                PdfPTable table = new PdfPTable(3); 
                addTableHeader(table);
                addRows(table, departmentList);

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
    
    
    
    
    private void clearTextFields() {
    // Reset all text fields to an empty state or default values
    depIdTxt.setText("");
    depNameTxt.setText("");
    
    managerCombo.setSelectedItem(null);
    
}
    
    private void addColumnNames(){
        tableModel.addColumn("Department ID");
        tableModel.addColumn("Department Names");
        tableModel.addColumn("Manager of the Department");
        departmentTable.setModel(tableModel);
    }
    private void displayDppts(){
        try{
            tableModel.setRowCount(0);
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            DepartmentService service = (DepartmentService) theRegistry.lookup("department");
            Department theDepartment = new Department();
            List<Department> departments = service.allDepartments();
            for(Department department :departments){
                tableModel.addRow(new Object[]{
                    department.getDepId(),
                    department.getDepName(),
                    department.getManager()
                });
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void addItemCombo(){
        try{
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            ManagerService service = (ManagerService) theRegistry.lookup("manager");
            List<Manager> theManagers = service.allManagers();
            for(Manager manager : theManagers){
                managerCombo.addItem(manager);
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
        jLabel5 = new javax.swing.JLabel();
        previousBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        reportBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        depIdTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        depNameTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        managerCombo = new javax.swing.JComboBox<>();
        registerBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        departmentTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(650, 120));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DEPARTMENT MANAGEMENT");

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(previousBtn)
                        .addGap(148, 148, 148)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nextBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reportBtn)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(previousBtn)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(nextBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reportBtn)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Department ID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Department Name");

        depNameTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                depNameTxtKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Manager");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(depIdTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(depNameTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(managerCombo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(235, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(registerBtn)
                .addGap(81, 81, 81)
                .addComponent(updateBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearBtn)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteBtn)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(depIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(depNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addGap(30, 30, 30)
                .addComponent(managerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBtn)
                    .addComponent(refreshBtn))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerBtn)
                    .addComponent(updateBtn)
                    .addComponent(clearBtn))
                .addGap(57, 57, 57))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );

        departmentTable.setModel(new javax.swing.table.DefaultTableModel(
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
        departmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                departmentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(departmentTable);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(530, 359));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Department.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        // TODO add your handling code here:
        try{
            if( depIdTxt.getText().trim().isEmpty()
           || depNameTxt.getText().trim().isEmpty() 
           || managerCombo.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "Provide data", "Empty Fields", JOptionPane.WARNING_MESSAGE);
            return;
        }
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            DepartmentService service = (DepartmentService) theRegistry.lookup("department");
            Department theDepartment = new Department();
            theDepartment.setDepId(depIdTxt.getText());
            theDepartment.setDepName(depNameTxt.getText());
            Manager theManager = (Manager) managerCombo.getSelectedItem();
            theDepartment.setManager(theManager);
            
            Department departmentObj = service.recordDepartment(theDepartment);
            
            if(departmentObj!=null){
                JOptionPane.showMessageDialog(this, "Data Saved!");
                displayDppts();
                clearTextFields();
            }else{
                JOptionPane.showMessageDialog(this, "Data not saved!");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_registerBtnActionPerformed

    private void departmentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_departmentTableMouseClicked
        // TODO add your handling code here:
        try{
        int selectedRowIndex = departmentTable.getSelectedRow();
  
  depIdTxt.setText(tableModel.getValueAt(selectedRowIndex, 0).toString());
  depNameTxt.setText(tableModel.getValueAt(selectedRowIndex, 1).toString());
  
  String manager = tableModel.getValueAt(selectedRowIndex, 2).toString();
  for(int i = 0; i < managerCombo.getItemCount(); i++){
    if(managerCombo.getItemAt(i).toString().equals(manager)){
      managerCombo.setSelectedIndex(i);
      break;
    }
  }
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_departmentTableMouseClicked

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        
        try{
            if( depIdTxt.getText().trim().isEmpty()
           || depNameTxt.getText().trim().isEmpty() 
           || managerCombo.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "Provide data", "Empty Fields", JOptionPane.WARNING_MESSAGE);
            return;
        }
            int option = JOptionPane.showConfirmDialog(this, "Are you sure to edit this record", "Warning", JOptionPane.YES_NO_OPTION);
            if(option ==0){
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            DepartmentService service = (DepartmentService) theRegistry.lookup("department");
            Department theDepartment = new Department();
            theDepartment.setDepId(depIdTxt.getText());
            theDepartment.setDepName(depNameTxt.getText());
            Manager theManager = (Manager) managerCombo.getSelectedItem();
            theDepartment.setManager(theManager);
            
            Department departmentObj = service.updateDepartment(theDepartment);
            if(departmentObj!=null){
                JOptionPane.showMessageDialog(this, "Data Updated!");
                displayDppts();
                clearTextFields();
            }else{
                JOptionPane.showMessageDialog(this, "Data not Updated!");
            }
            }
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        try{
            if( depIdTxt.getText().trim().isEmpty()
           || depNameTxt.getText().trim().isEmpty() 
           || managerCombo.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "Provide data", "Empty Fields", JOptionPane.WARNING_MESSAGE);
            return;
        }
            int option = JOptionPane.showConfirmDialog(this, "Are you sure to delete this record", "Warning", JOptionPane.YES_NO_OPTION);
            if(option ==0){
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            DepartmentService service = (DepartmentService) theRegistry.lookup("department");
            Department theDepartment = new Department();
            theDepartment.setDepId(depIdTxt.getText());
            theDepartment.setDepName(depNameTxt.getText());
            Manager theManager = (Manager) managerCombo.getSelectedItem();
            theDepartment.setManager(theManager);
            
            Department departmentObj = service.deleteDepartment(theDepartment);
            if(departmentObj!=null){
                JOptionPane.showMessageDialog(this, "Data Deleted!");
                displayDppts();
                clearTextFields();
            }else{
                JOptionPane.showMessageDialog(this, "Data not Deleted!");
            }
            }
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        // TODO add your handling code here:
        displayDppts();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // TODO add your handling code here:
        clearTextFields();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void depNameTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_depNameTxtKeyTyped
        // TODO add your handling code here:
        char keyChar = evt.getKeyChar();
        boolean digit = Character.isDigit(keyChar);
        if(digit){
            evt.consume();
            
        }
    }//GEN-LAST:event_depNameTxtKeyTyped

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        // TODO add your handling code here:
        EmployeeForm eF = new EmployeeForm();
        eF.setVisible(true);
        dispose();
    }//GEN-LAST:event_nextBtnActionPerformed

    private void previousBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousBtnActionPerformed
        // TODO add your handling code here:
        ManagerForm mF = new ManagerForm();
        mF.setVisible(true);
        dispose();
    }//GEN-LAST:event_previousBtnActionPerformed

    private void reportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBtnActionPerformed
        // TODO add your handling code here:
        try{
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            DepartmentService service = (DepartmentService) theRegistry.lookup("department");
            List<Department> departmentList = service.allDepartments();
            createReport(departmentList);
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
            java.util.logging.Logger.getLogger(DepartmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DepartmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DepartmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DepartmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DepartmentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField depIdTxt;
    private javax.swing.JTextField depNameTxt;
    private javax.swing.JTable departmentTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<Manager> managerCombo;
    private javax.swing.JButton nextBtn;
    private javax.swing.JButton previousBtn;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton registerBtn;
    private javax.swing.JButton reportBtn;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
