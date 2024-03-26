/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.*;
import model.Manager;
import org.hibernate.*;

/**
 *
 * @author JOHN SHADARY
 */
public class ManagerDao {
    public Manager registerManager(Manager theManager){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.save(theManager);
            ss.beginTransaction().commit();
            ss.close();
            return theManager;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Manager updateManager(Manager theManager){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.update(theManager);
            ss.beginTransaction().commit();
            ss.close();
            return theManager;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Manager deleteManager(Manager theManager){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.delete(theManager);
            ss.beginTransaction().commit();
            ss.close();
            return theManager;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Manager> allManagers(){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            List<Manager> theManagers = ss.createQuery("select theManager from Manager theManager").list();
            ss.close();
            return theManagers;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Manager searchManager ( Manager theManager){
        
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Manager managerObj = (Manager)ss.get(Manager.class, theManager.getManagerId());
            ss.close();
            
            return managerObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
        
    }
    

    
}
