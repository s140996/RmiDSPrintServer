
package dk.dtu.ds.rmids;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author Anders, Steen & Christoffer
 */
public interface RmiDSPrintService extends Remote 
{
    // *** Log in ***
    public boolean login(User user) throws RemoteException;
    // *** Display message when client is connected ***
    public String startUp(String input) throws RemoteException;
    // *** Prints file filename on the specified printer ***
    public String print(String filename, String printer) throws RemoteException;
    // *** lists the print queue on the user's display in lines of the form <job number>   <file name> ***
    public String queue() throws RemoteException;
    // *** Moves job to the top of the queue
    public void topQueue(int job) throws RemoteException;   
    // *** Starts the print server ***
    public void start() throws RemoteException;   
    // *** Stops the print server ***
    public void stop() throws RemoteException;   
    // *** Stops the print server, clears the print queue and starts the print server again ***
    public void restart() throws RemoteException;   
    // *** Prints status of printer on the user's display ***
    public void status()throws RemoteException;  
    // *** Prints the value of the parameter on the user's display ***
    public void readConfig(String parameter) throws RemoteException;   
    // *** Sets the parameter to value ***
    public void setConfig(String parameter, String value) throws RemoteException;  
}
