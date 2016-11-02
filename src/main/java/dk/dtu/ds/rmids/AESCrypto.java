/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ds.rmids;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Anders, Steen & Christoffer
 * Source: https://www.youtube.com/watch?v=uxyGJMBs2dI
 */
public class AESCrypto {
    
    private static String ALGO = "AES";
    
    private byte[] keyValue;
    
    public AESCrypto(String key)
    {
        keyValue = key.getBytes();
    }
    
    public String encrypt(String data) throws Exception
    {
        Key key = generateKey();
        
        Cipher c = Cipher.getInstance(ALGO);
        
        c.init(Cipher.ENCRYPT_MODE, key);
        
        byte[] encVal = c.doFinal(data.getBytes());
        
        String encryptedValue = new BASE64Encoder().encode(encVal);
        
        return encryptedValue;
    }
    
    
    public String decrypt(String encryptedData) throws Exception
    {
        Key key = generateKey();
        
        Cipher c = Cipher.getInstance(ALGO);
        
        c.init(Cipher.DECRYPT_MODE, key);
        
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        
        byte[] decValue = c.doFinal(decordedValue);
        
        String decryptedValue = new String(decValue);
        
        return decryptedValue;
    }
    
    public Key generateKey() throws Exception
    {
        Key key = new SecretKeySpec(keyValue,ALGO);
        
        return key;
    }
    
    
    public static void main(String[] args) {
        try {
            
            AESCrypto aes = new AESCrypto("ljksdf9342kjdfs9");
            
            String encData = aes.encrypt("Hej med dig :)");
            
            System.out.println("Encrypted data: " + encData);
            
            String decData = aes.decrypt(encData);
            
            System.out.println("Decrypted data: " + decData);
            
            String str = "qwerty";
            
            String salt = "0954i3jfd093";
            
            String salted = str + salt;
            
            System.out.println("Hash værdi af 'Test med hash' plus salt: " + salted.hashCode());
            
        } catch (Exception e) {
        }
        
//        //qwerty
//        User user1 = new User("Anders","1893950468", "gfdkl229ef0");
//        //ytrewq
//        User user2 = new User("Steen","-657510480", "dslæf20995we");
//        //password
//        User user3 = new User("Christoffer","-1911362740", "209f09di3fs");
//        //qwerty
//        User user4 = new User("Wannabe", "-2092530036", "0954i3jfd093");
        
        String fileName = "users.bin";
        
        
          ArrayList<User> userList = new ArrayList<User>();
//        userList.add(user1);
//        userList.add(user2);
//        userList.add(user3);
//        userList.add(user4);
//
//        try { 
//            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
//            os.writeObject(userList);
//            os.close();
//        } catch (FileNotFoundException ex) {
//        } catch (IOException ex) {
//        }
//        System.out.println("Done writing");

        try {
          ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
          userList = (ArrayList<User>) is.readObject();
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
        
        } catch (ClassNotFoundException ex) {
            
        }
        
        for (User users : userList) {
            if(users.getUsername().equals("Steen"))
            {
                System.out.println("Password = " + users.getPassword());
            }
            else
            {
                System.out.println(users.getPassword());
            }
        }
        
        System.out.println("done");
    }
}
