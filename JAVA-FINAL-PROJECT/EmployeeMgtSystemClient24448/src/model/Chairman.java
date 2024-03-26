/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;


/**
 *
 * @author JOHN SHADARY
 */

public class Chairman implements Serializable{
    public static final long serialVersionUID = 80472344771699318L;
    
    private Integer chairmanId;
    private String names;
    private String email;
    
    private String phoneNumber;
    private String address;
    
    List <Manager> managers = new ArrayList<>();

    public Chairman() {
    }

    public Chairman(Integer chairmanId) {
        this.chairmanId = chairmanId;
    }

    public Chairman(Integer chairmanId, String names, String email, String phoneNumber, String address) {
        this.chairmanId = chairmanId;
        this.names = names;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Integer getChairmanId() {
        return chairmanId;
    }

    public void setChairmanId(Integer chairmanId) {
        this.chairmanId = chairmanId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    @Override
    public String toString() {
        return names;
    }
    
    

   
    
}
