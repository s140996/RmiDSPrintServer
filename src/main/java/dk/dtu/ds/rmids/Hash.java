/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dk.dtu.ds.rmids;

import java.security.MessageDigest;


/**
 *
 * @author Anders, Steen & Christoffer
 */
public class Hash {
    
    public String hash(String str)
    {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes("UTF-8"));
            StringBuffer buffer = new StringBuffer();
            
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) buffer.append('0');
                buffer.append(hex);
            }
            
            return buffer.toString();
            
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    
}
