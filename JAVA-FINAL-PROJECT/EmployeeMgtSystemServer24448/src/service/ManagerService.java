/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.*;
import java.util.*;
import model.Manager;

/**
 *
 * @author JOHN SHADARY
 */
public interface ManagerService extends Remote{
    Manager recordManager (Manager managerObj) throws RemoteException;
    Manager updateManager (Manager managerObj) throws RemoteException;
    Manager deleteManager (Manager managerObj) throws RemoteException;
    Manager searchManager (Manager managerObj) throws RemoteException;
    List<Manager> allManagers() throws RemoteException;
    
}
