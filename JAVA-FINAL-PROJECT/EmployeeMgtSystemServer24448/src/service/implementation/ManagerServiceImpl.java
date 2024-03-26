/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

import DAO.ManagerDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.Manager;
import service.ManagerService;

/**
 *
 * @author JOHN SHADARY
 */
public class ManagerServiceImpl extends UnicastRemoteObject implements ManagerService{
    
    public ManagerServiceImpl() throws RemoteException{
        
    }
    // Initialization and declaration
    ManagerDao dao = new ManagerDao();

    @Override
    public Manager recordManager(Manager managerObj) throws RemoteException {
        return dao.registerManager(managerObj);
    }

    @Override
    public Manager updateManager(Manager managerObj) throws RemoteException {
        return dao.updateManager(managerObj);
    }

    @Override
    public Manager deleteManager(Manager managerObj) throws RemoteException {
        return dao.deleteManager(managerObj);
    }

    @Override
    public Manager searchManager(Manager managerObj) throws RemoteException {
        return dao.searchManager(managerObj);
    }

    @Override
    public List<Manager> allManagers() throws RemoteException {
        return dao.allManagers();
    }
    
}
