
package dk.dtu.ds.rmids;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Anders, Steen & Christoffer
 */
public class RmiDSPrintServer 
{
    public static void main(String[] args) throws RemoteException 
    {
        Registry registry = LocateRegistry.createRegistry(5019);
        registry.rebind("printservice", new RmiDSPrintServant());

    }
    
    
}
