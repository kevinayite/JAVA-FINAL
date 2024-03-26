/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author JOHN SHADARY
 */
@Entity
public class Manager implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="manager_id")
    private Integer managerId;
    private String names;
    private String email;
    private String phoneNumber;
    private String address;
    @ManyToOne
    @JoinColumn(name ="chairman_id")
    private Chairman chairman;
    
    @OneToOne(mappedBy = "manager")
    private Department department;
    
    @OneToMany(mappedBy = "manager")
    List<Employee> employees = new ArrayList<>();

    public Manager() {
    }

    public Manager(Integer managerId) {
        this.managerId = managerId;
    }

    public Manager(Integer managerId, String names, String email, String phoneNumber, String address, Chairman chairman, Department department) {
        this.managerId = managerId;
        this.names = names;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.chairman = chairman;
        this.department = department;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Chairman getChairman() {
        return chairman;
    }

    public void setChairman(Chairman chairman) {
        this.chairman = chairman;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
    
    
    
}
