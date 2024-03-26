/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;


/**
 *
 * @author JOHN SHADARY
 */

public class Utilisateur implements Serializable{
    
    private Integer adminId;
    private String names;
    private String email;
    
    private String phoneNumber;
    private String username;
    private String password;

    public Utilisateur() {
    }

    public Utilisateur(Integer adminId) {
        this.adminId = adminId;
    }

    public Utilisateur(Integer adminId, String names, String email, String phoneNumber, String username, String password) {
        this.adminId = adminId;
        this.names = names;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
