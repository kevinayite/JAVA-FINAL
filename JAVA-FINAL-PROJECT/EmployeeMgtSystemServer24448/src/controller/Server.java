/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.rmi.registry.*;
import service.implementation.ChairmanServiceImpl;
import service.implementation.DepartmentServiceImpl;
import service.implementation.EmployeeServiceImpl;
import service.implementation.ManagerServiceImpl;
import service.implementation.UtilisateurServiceImpl;


/**
 *
 * @author JOHN SHADARY
 */
public class Server {
    public static void main(String[] args) {
        try{
            // Set properties
            System.setProperty("java.rmi.server.hostaname", "127.0.0.1");
            // Create Registry
            Registry theRegistry = LocateRegistry.createRegistry(6000);
            theRegistry.rebind("chairman", new ChairmanServiceImpl());
            theRegistry.rebind("manager", new ManagerServiceImpl());
            theRegistry.rebind("department", new DepartmentServiceImpl());
            theRegistry.rebind("employee", new EmployeeServiceImpl());
            theRegistry.rebind("utilisateur", new UtilisateurServiceImpl());

            
            System.out.println("Server is running on port 6000");
            Thread.currentThread().join();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
