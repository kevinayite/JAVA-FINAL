/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.*;
import model.Employee;
import org.hibernate.*;

/**
 *
 * @author JOHN SHADARY
 */
public class EmployeeDao {
    public Employee registerEmployee(Employee theEmployee){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.save(theEmployee);
            ss.beginTransaction().commit();
            ss.close();
            return theEmployee;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Employee updateEmployee(Employee theEmployee){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.update(theEmployee);
            ss.beginTransaction().commit();
            ss.close();
            return theEmployee;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Employee deleteEmployee(Employee theEmployee){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.delete(theEmployee);
            ss.beginTransaction().commit();
            ss.close();
            return theEmployee;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Employee> allEmployees(){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            List<Employee> theEmployees = ss.createQuery("select theEmployee from Employee theEmployee").list();
            ss.close();
            return theEmployees;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Employee searchEmployee ( Employee theEmployee){
        
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Employee employeeObj = (Employee)ss.get(Employee.class, theEmployee.getEmployeeId());
            ss.close();
            
            return employeeObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
        
    }
    

    
}
