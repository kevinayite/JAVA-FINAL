/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.*;
import java.util.*;
import model.Chairman;

/**
 *
 * @author JOHN SHADARY
 */
public interface ChairmanService extends Remote{
    Chairman recordChairman (Chairman chairmanObj) throws RemoteException;
    Chairman updateChairman (Chairman chairmanObj) throws RemoteException;
    Chairman deleteChairman (Chairman chairmanObj) throws RemoteException;
    Chairman searchChairman (Chairman chairmanObj) throws RemoteException;
    List<Chairman> allChairmans() throws RemoteException;
    
}
