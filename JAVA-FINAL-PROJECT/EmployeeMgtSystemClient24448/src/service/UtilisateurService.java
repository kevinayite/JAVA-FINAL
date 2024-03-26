/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Utilisateur;

/**
 *
 * @author JOHN SHADARY
 */
public interface UtilisateurService extends Remote{
    Utilisateur registerUtilisateur(Utilisateur utilisateurObj) throws RemoteException;
    Utilisateur loginUtilisateur(String email, String password) throws RemoteException;
    
}
