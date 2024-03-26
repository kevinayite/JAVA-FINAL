/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

import DAO.EmployeeDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.Employee;
import service.EmployeeService;

/**
 *
 * @author JOHN SHADARY
 */
public class EmployeeServiceImpl extends UnicastRemoteObject implements EmployeeService{
    
    public EmployeeServiceImpl() throws RemoteException{
        
    }
    // Initialization and declaration
    EmployeeDao dao = new EmployeeDao();

    @Override
    public Employee recordEmployee(Employee employeeObj) throws RemoteException {
        return dao.registerEmployee(employeeObj);
    }

    @Override
    public Employee updateEmployee(Employee employeeObj) throws RemoteException {
        return dao.updateEmployee(employeeObj);
    }

    @Override
    public Employee deleteEmployee(Employee employeeObj) throws RemoteException {
        return dao.deleteEmployee(employeeObj);
    }

    @Override
    public Employee searchEmployee(Employee employeeObj) throws RemoteException {
        return dao.searchEmployee(employeeObj);
    }

    @Override
    public List<Employee> allEmployees() throws RemoteException {
        return dao.allEmployees();
    }
    
}
