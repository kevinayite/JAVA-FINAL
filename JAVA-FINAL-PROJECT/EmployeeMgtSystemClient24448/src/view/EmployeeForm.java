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
import java.util.stream.Stream;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Department;
import model.Employee;
import model.Manager;
import service.DepartmentService;
import service.EmployeeService;
import service.ManagerService;
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
public class EmployeeForm extends javax.swing.JFrame {
    DefaultTableModel tableModel = new DefaultTableModel();
    

    /**
     * Creates new form EmployeeForm
     */
    public EmployeeForm() {
        initComponents();
        addManagerCombo();
        addDepartmentCombo();
        addColumnHeader();
        displayEmployee();
        clearTextFields();
    }
    
    private void addRows(PdfPTable table, List<Employee> employeeList) {
        for (Employee emp : employeeList) {
                           
            table.addCell(emp.getEmployeeId());
            table.addCell(emp.getNames());
            table.addCell(emp.getEmail());
            table.addCell(emp.getPhoneNumber());
            table.addCell(emp.getAddress());
            table.addCell(emp.getSalary());
            Manager manager = emp.getManager();
            String managerName = "";
            if(manager != null) {
          int managerId = manager.getManagerId();
          managerName = manager.getNames();
            
        }
        table.addCell(managerName);
        Department department = emp.getDepartment();
        String departmentName = "";
        if(department != null){
            String departmentId = department.getDepId();
            departmentName = department.getDepName();
        }table.addCell(departmentName);
            
    }
}
    
  private void addTableHeader(PdfPTable table) {
    Stream.of("Employee ID", "Names", "Email", "Phone Number","Address" ," Salary", "Manager", "Department")
            .forEach(columnTitle -> {
                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(columnTitle));
                table.addCell(header);
            });
}
        private void createReport(List<Employee> employeeList) {
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

                            PdfPTable table = new PdfPTable(8); 
                            addTableHeader(table);
                            addRows(table, employeeList);

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
        tableModel.addColumn("Employee ID");
        tableModel.addColumn("Names");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Address");
        tableModel.addColumn("Salary");
        tableModel.addColumn("Manager");
        tableModel.addColumn("Department");
        employeeTable.setModel(tableModel);
        
    }
    private void clearTextFields() {
    // Reset all text fields to an empty state or default values
    employeeIdTxt.setText("");
    namesTxt.setText("");
    emailTxt.setText("");
    phoneNumberTxt.setText("");
    addressTxt.setText("");
    salaryTxt.setText("");
    managerCombo.setSelectedItem(null);
    depCombo.setSelectedItem(null);
}
    private void displayEmployee(){
        try{
            tableModel.setRowCount(0);
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            EmployeeService service = (EmployeeService) theRegistry.lookup("employee");
            Employee theEmployee = new Employee();
            List<Employee> employees = service.allEmployees();
            for(Employee employee : employees){
                tableModel.addRow(new Object[]{
                    employee.getEmployeeId(),
                    employee.getNames(),
                    employee.getEmail(),
                    employee.getPhoneNumber(),
                    employee.getAddress(),
                    employee.getSalary(),
                    employee.getManager(),
                    employee.getDepartment()
                });
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    public static boolean validateEmail(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.[a-zA-Z]{2,7})$";
    Pattern pattern = Pattern.compile(emailRegex);
    return pattern.matcher(email).matches();
}
    
    private void addManagerCombo(){
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
    
    private void addDepartmentCombo(){
        try{
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            DepartmentService service = (DepartmentService) theRegistry.lookup("department");
            List<Department> theDepartments = service.allDepartments();
            for(Department department : theDepartments){
                depCombo.addItem(department);
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
        jLabel10 = new javax.swing.JLabel();
        previousBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        reportBtn = new javax.swing.JButton();
        logOutBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        employeeIdTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        namesTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        emailTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        phoneNumberTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        addressTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        salaryTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        managerCombo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        depCombo = new javax.swing.JComboBox<>();
        registerBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(940, 140));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("EMPLOYEE MANAGEMENT");

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

        logOutBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        logOutBtn.setText("Log Out");
        logOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(previousBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(163, 163, 163)
                        .addComponent(nextBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(logOutBtn)
                            .addComponent(reportBtn))))
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(logOutBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextBtn)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(previousBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reportBtn)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Employee ID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Names");

        namesTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                namesTxtKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Email");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Phone Number");

        phoneNumberTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneNumberTxtKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Address");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Salary");

        salaryTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                salaryTxtKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Manager");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Department");

        registerBtn.setBackground(new java.awt.Color(51, 51, 255));
        registerBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        registerBtn.setForeground(new java.awt.Color(255, 255, 255));
        registerBtn.setText("Register");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
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

        updateBtn.setBackground(new java.awt.Color(51, 51, 255));
        updateBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(employeeIdTxt)
                            .addComponent(namesTxt)
                            .addComponent(emailTxt)
                            .addComponent(jLabel4)
                            .addComponent(phoneNumberTxt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(addressTxt)
                            .addComponent(salaryTxt)
                            .addComponent(managerCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(depCombo, 0, 119, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(registerBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateBtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteBtn)
                        .addGap(18, 18, 18)
                        .addComponent(refreshBtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employeeIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salaryTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(managerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(depCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerBtn)
                    .addComponent(deleteBtn)
                    .addComponent(updateBtn)
                    .addComponent(refreshBtn))
                .addGap(23, 23, 23))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        employeeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(employeeTable);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/EmployeeOG.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        // TODO add your handling code here:
        try{
            if(namesTxt.getText().trim().isEmpty() 
                ||employeeIdTxt.getText().trim().isEmpty()
                || emailTxt.getText().trim().isEmpty() 
                || addressTxt.getText().trim().isEmpty()
		|| phoneNumberTxt.getText().trim().isEmpty()
                || salaryTxt.getText().trim().isEmpty()
                || depCombo.getSelectedItem() == null
                || managerCombo.getSelectedItem() == null
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
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1" , 6000);
            EmployeeService service = (EmployeeService) theRegistry.lookup("employee");
            Employee theEmployee = new Employee();
            theEmployee.setEmployeeId(employeeIdTxt.getText());
            theEmployee.setNames(namesTxt.getText());
            theEmployee.setEmail(emailTxt.getText());
            theEmployee.setPhoneNumber(phoneNumberTxt.getText());
            theEmployee.setSalary(salaryTxt.getText());
            theEmployee.setAddress(addressTxt.getText());
            Manager theManager = (Manager) managerCombo.getSelectedItem();
            theEmployee.setManager(theManager);
            Department theDepartment = (Department) depCombo.getSelectedItem();
            theEmployee.setDepartment(theDepartment);
            
            Employee employeeObj = service.recordEmployee(theEmployee);
            if(employeeObj!= null){
                JOptionPane.showMessageDialog(this, "Data Saved!");
                displayEmployee();
                clearTextFields();
            }else{
                JOptionPane.showMessageDialog(this, "Data not saved!");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_registerBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        try{
            if(namesTxt.getText().trim().isEmpty() 
                ||employeeIdTxt.getText().trim().isEmpty()
                || emailTxt.getText().trim().isEmpty() 
                || addressTxt.getText().trim().isEmpty()
		|| phoneNumberTxt.getText().trim().isEmpty()
                || salaryTxt.getText().trim().isEmpty()
                || depCombo.getSelectedItem() == null
                || managerCombo.getSelectedItem() == null
                ){
            JOptionPane.showMessageDialog(this, "All fields are mandatory","Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
            int option = JOptionPane.showConfirmDialog(this, "Are you sure to delete this record", "Warning", JOptionPane.YES_NO_OPTION);
            if(option ==0){
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            EmployeeService service = (EmployeeService) theRegistry.lookup("employee");
            Employee theEmployee = new Employee();
            theEmployee.setEmployeeId(employeeIdTxt.getText());
            theEmployee.setNames(namesTxt.getText());
            theEmployee.setEmail(emailTxt.getText());
            theEmployee.setAddress(addressTxt.getText());
            theEmployee.setSalary(salaryTxt.getText());
            Manager theManager = (Manager) managerCombo.getSelectedItem();
            theEmployee.setManager(theManager);
            Department theDepartment = (Department) depCombo.getSelectedItem();
            theEmployee.setDepartment(theDepartment);
            
            Employee employeeObj = service.deleteEmployee(theEmployee);
            if(employeeObj!=null){
                JOptionPane.showMessageDialog(this, "Data Deleted!");
                displayEmployee();
                clearTextFields();
            }else{
                JOptionPane.showMessageDialog(this, "Data not Deleted!");
            }
            }
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void employeeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeTableMouseClicked
        // TODO add your handling code here:
        try{
            
        int selectedRowIndex = employeeTable.getSelectedRow();
        
        employeeIdTxt.setText(tableModel.getValueAt(selectedRowIndex, 0).toString());
        namesTxt.setText(tableModel.getValueAt(selectedRowIndex, 1).toString());
        emailTxt.setText(tableModel.getValueAt(selectedRowIndex, 2).toString());
        phoneNumberTxt.setText(tableModel.getValueAt(selectedRowIndex, 3).toString());
        addressTxt.setText(tableModel.getValueAt(selectedRowIndex, 4).toString());
        salaryTxt.setText(tableModel.getValueAt(selectedRowIndex, 5).toString());
        String manager = tableModel.getValueAt(selectedRowIndex, 6).toString();
  for(int i = 0; i < managerCombo.getItemCount(); i++){
    if(managerCombo.getItemAt(i).toString().equals(manager)){
      managerCombo.setSelectedIndex(i);
      break;
    }
  }
//        managerCombo.setSelectedItem(tableModel.getValueAt(selectedRowIndex, 6).toString());
String dep = tableModel.getValueAt(selectedRowIndex, 7).toString();
  for(int i = 0; i < depCombo.getItemCount(); i++){
    if(depCombo.getItemAt(i).toString().equals(dep)){
      depCombo.setSelectedIndex(i);
      break;
    }
  }
//        depCombo.setSelectedItem(tableModel.getValueAt(selectedRowIndex, 7).toString());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_employeeTableMouseClicked

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        try{
            if(namesTxt.getText().trim().isEmpty() 
                ||employeeIdTxt.getText().trim().isEmpty()
                || emailTxt.getText().trim().isEmpty() 
                || addressTxt.getText().trim().isEmpty()
		|| phoneNumberTxt.getText().trim().isEmpty()
                || salaryTxt.getText().trim().isEmpty()
                || depCombo.getSelectedItem() == null
                || managerCombo.getSelectedItem() == null
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
            EmployeeService service = (EmployeeService) theRegistry.lookup("employee");
            Employee theEmployee = new Employee();
            theEmployee.setEmployeeId(employeeIdTxt.getText());
            theEmployee.setNames(namesTxt.getText());
            theEmployee.setEmail(emailTxt.getText());
            theEmployee.setAddress(addressTxt.getText());
            theEmployee.setSalary(salaryTxt.getText());
            Manager theManager = (Manager) managerCombo.getSelectedItem();
            theEmployee.setManager(theManager);
            Department theDepartment = (Department) depCombo.getSelectedItem();
            theEmployee.setDepartment(theDepartment);
            
            Employee employeeObj = service.updateEmployee(theEmployee);
            if(employeeObj!=null){
                JOptionPane.showMessageDialog(this, "Data Updated!");
                displayEmployee();
                clearTextFields();
            }else{
                JOptionPane.showMessageDialog(this, "Data not Updated!");
            }
            }
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        // TODO add your handling code here:
        displayEmployee();
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

    private void salaryTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salaryTxtKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_salaryTxtKeyTyped

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        // TODO add your handling code here:
        CEOForm cF = new CEOForm();
        cF.setVisible(true);
        dispose();
    }//GEN-LAST:event_nextBtnActionPerformed

    private void previousBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousBtnActionPerformed
        // TODO add your handling code here:
        DepartmentForm dF = new  DepartmentForm();
        dF.setVisible(true);
        dispose();
    }//GEN-LAST:event_previousBtnActionPerformed

    private void reportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBtnActionPerformed
        // TODO add your handling code here:
        
try{
            Registry theRegistry = LocateRegistry.getRegistry("127.0.0.1", 6000);
            EmployeeService service = (EmployeeService) theRegistry.lookup("employee");
            List<Employee> employeeList = service.allEmployees();
            createReport(employeeList);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_reportBtnActionPerformed

    private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutBtnActionPerformed
        // TODO add your handling code here:
        LoginUI login = new LoginUI();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_logOutBtnActionPerformed

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
            java.util.logging.Logger.getLogger(EmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTxt;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JComboBox<Department> depCombo;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JTextField employeeIdTxt;
    private javax.swing.JTable employeeTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JButton logOutBtn;
    private javax.swing.JComboBox<Manager> managerCombo;
    private javax.swing.JTextField namesTxt;
    private javax.swing.JButton nextBtn;
    private javax.swing.JTextField phoneNumberTxt;
    private javax.swing.JButton previousBtn;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton registerBtn;
    private javax.swing.JButton reportBtn;
    private javax.swing.JTextField salaryTxt;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
