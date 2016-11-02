
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
        //qwerty
        User user1 = new User("Anders","1893950468", "gfdkl229ef0");
        //ytrewq
        User user2 = new User("Steen","-657510480", "dslæf20995we");
        //password
        User user3 = new User("Christoffer","-1911362740", "209f09di3fs");
        //qwerty
        User user4 = new User("Wannabe", "-2092530036", "0954i3jfd093");
    }
    
    
}
