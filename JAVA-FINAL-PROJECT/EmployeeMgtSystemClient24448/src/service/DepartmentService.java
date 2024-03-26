/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.*;
import java.util.*;
import model.Department;

/**
 *
 * @author JOHN SHADARY
 */
public interface DepartmentService extends Remote{
    Department recordDepartment (Department departmentObj) throws RemoteException;
    Department updateDepartment (Department departmentObj) throws RemoteException;
    Department deleteDepartment (Department departmentObj) throws RemoteException;
    Department searchDepartment (Department departmentObj) throws RemoteException;
    List<Department> allDepartments() throws RemoteException;
    
}
