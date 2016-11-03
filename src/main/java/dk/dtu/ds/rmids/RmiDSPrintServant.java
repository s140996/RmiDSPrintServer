
package dk.dtu.ds.rmids;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    Hash shaHash = new Hash();
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
        return "Filename: " + filename + "  -  " + "Printername: " + printer;
    }
    
    @Override
    public String queue() throws RemoteException
    {
        return "Queue";
    }
    
    @Override
    public void topQueue(int job) throws RemoteException
    {
        System.out.println("topQueue");
    }
    
    @Override
    public void start() throws RemoteException
    {
        System.out.println("Start");
    }
    
    @Override
    public void stop() throws RemoteException
    {
        System.out.println("Stop");
    }
    
    @Override
    public void restart() throws RemoteException
    {
        System.out.println("Restart");
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
        
        String fileName = "users.txt";
        BufferedReader br = null;
        String line = "";
        String split = ",";
        ArrayList<User> userList = new ArrayList<>();
        
        try {
            username = aes.decrypt(user.getUsername());
            password = aes.decrypt(user.getPassword());
            
        } catch (Exception ex) {
            Logger.getLogger(RmiDSPrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                String[] newUser = line.split(split);
                
                User temp = new User(newUser[0],newUser[1],newUser[2]);
                userList.add(temp);
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
            if(users.getUsername().equals(username))
            {
                
                String saltedPassword = password + users.getSalt();
                if(users.getPassword().equals(shaHash.hash(saltedPassword)))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
}
