
package dk.dtu.ds.rmids;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

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
        //Test
    }
}
