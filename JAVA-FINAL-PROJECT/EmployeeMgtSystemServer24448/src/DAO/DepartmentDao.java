/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.*;
import model.Department;
import org.hibernate.*;

/**
 *
 * @author JOHN SHADARY
 */
public class DepartmentDao {
    public Department registerDepartment(Department theDepartment){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.save(theDepartment);
            ss.beginTransaction().commit();
            ss.close();
            return theDepartment;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Department updateDepartment(Department theDepartment){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.update(theDepartment);
            ss.beginTransaction().commit();
            ss.close();
            return theDepartment;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Department deleteDepartment(Department theDepartment){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.delete(theDepartment);
            ss.beginTransaction().commit();
            ss.close();
            return theDepartment;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Department> allDepartments(){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            List<Department> theDepartments = ss.createQuery("select theDepartment from Department theDepartment").list();
            ss.close();
            return theDepartments;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Department searchDepartment ( Department theDepartment){
        
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Department departmentObj = (Department)ss.get(Department.class, theDepartment.getDepId());
            ss.close();
            
            return departmentObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
        
    }
    

    
}
