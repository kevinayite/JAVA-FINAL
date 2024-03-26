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
public class Department implements Serializable{
    @Id
    @Column(name = "dep_id")
    private String depId;
    @Column(name = "dep_name")
    private String depName;
    @OneToOne
    @JoinColumn(name ="manager_id")
    private Manager manager;
    
    
    @OneToMany(mappedBy = "department")
    List<Employee> employees = new ArrayList<>();

    public Department() {
    }

    public Department(String depId) {
        this.depId = depId;
    }

    public Department(String depId, String depName, Manager manager) {
        this.depId = depId;
        this.depName = depName;
        this.manager = manager;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
    
    
    
}
