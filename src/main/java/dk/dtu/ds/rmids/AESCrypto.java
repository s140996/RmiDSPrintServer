/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dk.dtu.ds.rmids;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
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
            
            String str = "password";
            
            String salt = "209f09di3fs";
            
            String salted = str + salt;
            
            Hash hashfunktion = new Hash();
            System.out.println(hashfunktion.hash(salted));
            
        } catch (Exception e) {
        }
        
        //qwerty
        User user1 = new User("Anders","4d1953b6227c93642fb327fb98472cbf6c45670fa67bf282bb68b533f508f017", "gfdkl229ef0");
        //ytrewq
        User user2 = new User("Steen","943e8b44624bfc43439c13148997397cd7be1f1e517371294b87798cffdb854c", "dslæf20995we");
        //password
        User user3 = new User("Christoffer","285d4f2e86e928672435fba7356c0dd5a64a0b5440d15992998b6ce8fad5151d", "209f09di3fs");
        //qwerty
        User user4 = new User("Wannabe", "a1229a6e156af10ceb4ddc2dfa120048a77e7b9caa275a257f01a97dff03d3c5", "0954i3jfd093");
        
//        String fileName = "users.bin";


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

//        try {
//          ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
//          userList = (ArrayList<User>) is.readObject();
//          is.close();
//        } catch (FileNotFoundException ex) {
//
//        } catch (IOException ex) {
//
//        } catch (ClassNotFoundException ex) {
//
//        }


String fileName = "users.txt";


//        try {
//            PrintWriter outputStream = new PrintWriter(fileName);
//            outputStream.println(user1.getUsername() + "," + user1.getPassword() + "," + user1.getSalt());
//            outputStream.println(user2.getUsername() + "," + user2.getPassword() + "," + user2.getSalt());
//            outputStream.println(user3.getUsername() + "," + user3.getPassword() + "," + user3.getSalt());
//            outputStream.println(user4.getUsername() + "," + user4.getPassword() + "," + user4.getSalt());
//            outputStream.close();
//            System.out.println("DONE");
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(AESCrypto.class.getName()).log(Level.SEVERE, null, ex);
//        }

BufferedReader br = null;
String line = "";
String split = ",";

try {
    
    br = new BufferedReader(new FileReader(fileName));
    while ((line = br.readLine()) != null) {
        
        // use comma as separator
        String[] newUser = line.split(split);
        
        User user = new User(newUser[0],newUser[1],newUser[2]);
        userList.add(user);
    }
    
} catch (FileNotFoundException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (br != null) {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
