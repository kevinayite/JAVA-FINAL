/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import model.Utilisateur;
import org.hibernate.*;

/**
 *
 * @author JOHN SHADARY
 */
public class UtilisateurDao {
    public Utilisateur registerUtilisateur(Utilisateur theUtilisateur){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.save(theUtilisateur);
            ss.beginTransaction().commit();
            ss.close();
            return theUtilisateur;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Utilisateur loginUtilisateur(String email, String password) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Query query = ss.createQuery("FROM Utilisateur WHERE email = :email AND password = :password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            Utilisateur utilisateur = (Utilisateur) query.uniqueResult();
            ss.close();
            return utilisateur;

        } catch (HibernateException ex) {
            ex.printStackTrace(); 
        } catch (Exception ex) {
            ex.printStackTrace(); 
        }
        return null;
    }
    
}
