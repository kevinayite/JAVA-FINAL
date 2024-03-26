/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

import DAO.ChairmanDao;
import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.List;
import model.Chairman;
import service.ChairmanService;

/**
 *
 * @author JOHN SHADARY
 */
public class ChairmanServiceImpl extends UnicastRemoteObject implements ChairmanService{
    
    public ChairmanServiceImpl() throws RemoteException{
        
    }
    // Initialization and declaration
    ChairmanDao dao = new ChairmanDao();

    @Override
    public Chairman recordChairman(Chairman chairmanObj) throws RemoteException {
        return dao.registerChairman(chairmanObj);
    }

    @Override
    public Chairman updateChairman(Chairman chairmanObj) throws RemoteException {
        return dao.updateChairman(chairmanObj);
    }

    @Override
    public Chairman deleteChairman(Chairman chairmanObj) throws RemoteException {
        return dao.deleteChairman(chairmanObj);
    }

    @Override
    public Chairman searchChairman(Chairman chairmanObj) throws RemoteException {
        return dao.searchChairman(chairmanObj);
    }

    @Override
    public List<Chairman> allChairmans() throws RemoteException {
        return dao.allChairmans();
    }
    
}
