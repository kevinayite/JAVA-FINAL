/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author JOHN SHADARY
 */
@Entity
public class Employee implements Serializable{
    @Id
    @Column(name ="employee_id")
    private String employeeId;
    private String names;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String salary;
    private String address;
    @ManyToOne
    @JoinColumn(name ="manager_id")
    private Manager manager;
    
    @ManyToOne
    @JoinColumn(name = "dep_id")
    private Department department;

    public Employee() {
    }

    public Employee(String employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(String employeeId, String names, String email, String phoneNumber, String salary, String address, Manager manager, Department department) {
        this.employeeId = employeeId;
        this.names = names;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.address = address;
        this.manager = manager;
        this.department = department;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    
    
}
