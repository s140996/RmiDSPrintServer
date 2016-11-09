
package dk.dtu.ds.rmids;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    AccessControl access;
    String username = "";
    String password = "";
    
    public RmiDSPrintServant() throws RemoteException
    {
        super();
    }
    
    @Override
    public String startUp(String input) throws RemoteException
    {
        return "From PrintServer: " + input;
    }
    
    @Override
    public String print(String filename, String printer) throws RemoteException
    {
        if (access != null && access.isPrint())
        {
            writeLogfile("Print");
            return "Filename: " + filename + " was printed on: " + "Printername: " + printer;
        }
        else
        {            
            return "Access Denied";
        }
    }
    
    @Override
    public String queue() throws RemoteException
    {
        if (access != null && access.isQueue())
        {
            writeLogfile("Queue");
            return "Queue";
        }
        else
        {
           return "Access Denied!"; 
        }
    }
    
    @Override
    public void topQueue(int job) throws RemoteException
    {
        if (access != null && access.isTopQueue())
        {
            writeLogfile("Top Queue");
            System.out.println("Top Queue");
        }
        else
        {
            System.out.println("Access Denied!"); 
        }
    }
    
    @Override
    public void start() throws RemoteException
    {
        if (access != null && access.isStart())
        {
            writeLogfile("Start");
            System.out.println("Start");
        }
        else
        {
            System.out.println("Access Denied!"); 
        }
    }
    
    @Override
    public void stop() throws RemoteException
    {
        if (access != null && access.isStop())
        {
            writeLogfile("Stop");
            System.out.println("Stop");
        }
        else
        {
            System.out.println("Access Denied!"); 
        }
    }
    
    @Override
    public void restart() throws RemoteException
    {
        if (access != null && access.isRestart())
        {
            writeLogfile("Restart");
            System.out.println("Restart");
        }
        else
        {
            System.out.println("Access Denied!"); 
        }
    }
    
    @Override
    public void status() throws RemoteException
    {
        if (access != null && access.isStatus())
        {
            writeLogfile("Status");
            System.out.println("Status");
        }
        else
        {
            System.out.println("Access Denied!"); 
        }
    }
    
    @Override
    public void readConfig(String parameter) throws RemoteException
    {
        if (access != null && access.isReadConfig())
        {
            writeLogfile("Read Config");
            System.out.println("Read Config");
        }
        else
        {
            System.out.println("Access Denied!"); 
        }
    }
    
    @Override
    public void setConfig(String parameter, String value) throws RemoteException
    {
        if (access != null && access.isSetConfig())
        {
            writeLogfile("Set Config");
            System.out.println("Set Config");
        }
        else
        {
            System.out.println("Access Denied!"); 
        }
    }
    
    @Override
    public boolean login(User user) throws RemoteException {
        
        String fileName = "users.txt";
        BufferedReader br = null;
        String line = "";
        String split = ",";
        ArrayList<User> userList = new ArrayList<>();
        ArrayList<AccessControl> accessList = new ArrayList<>();
        
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
        
        try {
            
            br = new BufferedReader(new FileReader("AccessControl"));
            while ((line = br.readLine()) != null) {
                String[] newAccess = line.split(split);
                
                AccessControl temp = new AccessControl(newAccess[0], Boolean.parseBoolean(newAccess[1]), Boolean.parseBoolean(newAccess[2]), Boolean.parseBoolean(newAccess[3]), Boolean.parseBoolean(newAccess[4]), Boolean.parseBoolean(newAccess[5]), Boolean.parseBoolean(newAccess[6]), Boolean.parseBoolean(newAccess[7]), Boolean.parseBoolean(newAccess[8]), Boolean.parseBoolean(newAccess[9]));
                accessList.add(temp);
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
                    writeLogfile("Login");
                    return true;
                }
                else
                {
                    try {
                        Thread.sleep(5000);
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        
        for(AccessControl a : accessList){
            if(a.getUsername().equals(username))
            {
                access = a;
            }
        }
        
        return false;
    }
    
    public void writeLogfile(String method)
    {
        File logFile = new File("logfile.txt");
        
        try{
            if(logFile.exists() == false)
            {
                logFile.createNewFile();
            }
            
            PrintWriter pw = new PrintWriter(new FileWriter(logFile, true));
            pw.append(username + " has used method: " + method + ". Time: " + System.currentTimeMillis() + "\n");
            pw.close();
            System.out.println("Done writing");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
    }
}
