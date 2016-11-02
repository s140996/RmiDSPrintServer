
package dk.dtu.ds.rmids;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anders, Steen & Christoffer
 */
public class RmiDSPrintServant extends UnicastRemoteObject implements RmiDSPrintService
{
    
    AESCrypto aes = new AESCrypto("ljksdf9342kjdfs9");
    String username;
    String password;
    
    public RmiDSPrintServant() throws RemoteException
    {
        super(); 
    }
    
    @Override
    public String ehco(String input) throws RemoteException 
    {
        return "From PrintServer: " + input;
    }

    @Override
    public String print(String filename, String printer) throws RemoteException 
    {
        return "Filename: xx" +"  -  " + "Printername: xx";
    }

    @Override
    public String queue() 
    {
        return "Queue";
    }

    @Override
    public void topQueue(int job) throws RemoteException 
    {
        
    }

    @Override
    public void start() throws RemoteException 
    {
        
    }

    @Override
    public void stop() throws RemoteException 
    {
        
    }

    @Override
    public void restart() throws RemoteException 
    {
        
    }

    @Override
    public void status() throws RemoteException 
    {
        
    }

    @Override
    public void readConfig(String parameter) throws RemoteException 
    {
        
    }

    @Override
    public void setConfig(String parameter, String value) throws RemoteException 
    {
        
    }    

    @Override
    public boolean login(User user) throws RemoteException {
        try {
            username = aes.decrypt(user.getUsername());
            password = aes.decrypt(user.getPassword());
            
            
        } catch (Exception ex) {
            Logger.getLogger(RmiDSPrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<User> userList = new ArrayList<>();
        String fileName = "user.bin";
        
        try {
          ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
          userList = (ArrayList<User>) is.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RmiDSPrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RmiDSPrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RmiDSPrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (User users : userList) {
            if(users.getUsername().equals(username))
            {
                String saltedPassword = password + users.getSalt();
                if(users.getPassword().equals(saltedPassword.hashCode()))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
