/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

import DAO.DepartmentDao;
import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.List;
import model.Department;
import service.DepartmentService;

/**
 *
 * @author JOHN SHADARY
 */
public class DepartmentServiceImpl extends UnicastRemoteObject implements DepartmentService{
    
    public DepartmentServiceImpl() throws RemoteException{
        
    }
    // Initialization and declaration
    DepartmentDao dao = new DepartmentDao();

    @Override
    public Department recordDepartment(Department departmentObj) throws RemoteException {
        return dao.registerDepartment(departmentObj);
    }

    @Override
    public Department updateDepartment(Department departmentObj) throws RemoteException {
        return dao.updateDepartment(departmentObj);
    }

    @Override
    public Department deleteDepartment(Department departmentObj) throws RemoteException {
        return dao.deleteDepartment(departmentObj);
    }

    @Override
    public Department searchDepartment(Department departmentObj) throws RemoteException {
        return dao.searchDepartment(departmentObj);
    }

    @Override
    public List<Department> allDepartments() throws RemoteException {
        return dao.allDepartments();
    }
    
}
