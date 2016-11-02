/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ds.rmids;

import java.io.Serializable;

/**
 *
 * @author Anders, Steen & Christoffer
 */
public class User implements Serializable{
    
    private String username;
    
    private String password;
    
    private String salt;
    
    public User(String username, String password)
    {
        this.password = password;
        this.username = username;
    }
    
    public User(String username, String password, String salt)
    {
        this.password = password;
        this.username = username;
        this.salt = salt;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getSalt()
    {
        return salt;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public void setSalt(String salt)
    {
        this.salt = salt;
    }
}
