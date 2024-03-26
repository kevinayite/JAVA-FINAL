/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

import DAO.UtilisateurDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.Utilisateur;
import service.UtilisateurService;

/**
 *
 * @author JOHN SHADARY
 */
public class UtilisateurServiceImpl extends UnicastRemoteObject implements UtilisateurService{
    
    public UtilisateurServiceImpl() throws RemoteException{
        
    }
    UtilisateurDao dao = new UtilisateurDao();

    @Override
    public Utilisateur registerUtilisateur(Utilisateur utilisateurObj) throws RemoteException {
        return dao.registerUtilisateur(utilisateurObj);
    }

    @Override
    public Utilisateur loginUtilisateur(String email, String password) throws RemoteException {
        return dao.loginUtilisateur(email, password);
    }
    
    
}
