/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.*;
import model.Chairman;
import org.hibernate.*;

/**
 *
 * @author JOHN SHADARY
 */
public class ChairmanDao {
    public Chairman registerChairman(Chairman theChairman){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.save(theChairman);
            ss.beginTransaction().commit();
            ss.close();
            return theChairman;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Chairman updateChairman(Chairman theChairman){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.update(theChairman);
            ss.beginTransaction().commit();
            ss.close();
            return theChairman;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Chairman deleteChairman(Chairman theChairman){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.delete(theChairman);
            ss.beginTransaction().commit();
            ss.close();
            return theChairman;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Chairman> allChairmans(){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            List<Chairman> theChairmans = ss.createQuery("select theChairman from Chairman theChairman").list();
            ss.close();
            return theChairmans;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Chairman searchChairman ( Chairman theChairman){
        
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Chairman chairmanObj = (Chairman)ss.get(Chairman.class, theChairman.getChairmanId());
            ss.close();
            
            return chairmanObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
        
    }
    

    
}
