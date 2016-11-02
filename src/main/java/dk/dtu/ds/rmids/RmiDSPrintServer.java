
package dk.dtu.ds.rmids;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
