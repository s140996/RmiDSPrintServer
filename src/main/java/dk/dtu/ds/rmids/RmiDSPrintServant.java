
package dk.dtu.ds.rmids;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author AndersTT
 */
public class RmiDSPrintServant extends UnicastRemoteObject implements RmiDSPrintService
{
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
}
