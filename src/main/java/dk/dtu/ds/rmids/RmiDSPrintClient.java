
package dk.dtu.ds.rmids;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import rmiProject.User;

/**
 *
 * @author Anders, Steen & Christoffer
 */
public class RmiDSPrintClient 
{
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException 
    {
        RmiDSPrintService print = (RmiDSPrintService) Naming.lookup("rmi://localhost:5019/printservice");
        System.out.println("***** " + print.ehco("Printer Ready ") + "*****");
        
        User user1 = new User("Anders","qwerty");
        User user2 = new User("Steen","ytrewq");
        User user3 = new User("Christoffer","password");
        
        //Test
    }
}
