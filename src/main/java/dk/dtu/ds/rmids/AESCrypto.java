/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ds.rmids;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author steen
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
            
        } catch (Exception e) {
        }
    }
}
