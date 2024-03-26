/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.*;
import java.util.*;
import model.Employee;

/**
 *
 * @author JOHN SHADARY
 */
public interface EmployeeService extends Remote{
    Employee recordEmployee (Employee employeeObj) throws RemoteException;
    Employee updateEmployee (Employee employeeObj) throws RemoteException;
    Employee deleteEmployee (Employee employeeObj) throws RemoteException;
    Employee searchEmployee (Employee employeeObj) throws RemoteException;
    List<Employee> allEmployees() throws RemoteException;
    
}
