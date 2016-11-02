
package dk.dtu.ds.rmids;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anders, Steen & Christoffer
 */
public class RmiDSPrintServant extends UnicastRemoteObject implements RmiDSPrintService
{
    
    AESCrypto aes = new AESCrypto("ljksdf9342kjdfs9");
    
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
            String username = aes.decrypt(user.getUsername());
            String password = aes.decrypt(user.getPassword());
            
            
        } catch (Exception ex) {
            Logger.getLogger(RmiDSPrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        return false;
    }
}
