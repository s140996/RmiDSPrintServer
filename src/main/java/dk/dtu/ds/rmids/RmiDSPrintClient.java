
package dk.dtu.ds.rmids;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;



/**
 *
 * @author Anders, Steen & Christoffer
 */
public class RmiDSPrintClient 
{
    static String username;
    static String password;
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException 
    {
        RmiDSPrintService print = (RmiDSPrintService) Naming.lookup("rmi://localhost:5019/printservice");
        System.out.println("***** " + print.ehco("Printer Ready ") + "*****");
        
        TypeUsernameAndPassword();
    }

    static void TypeUsernameAndPassword()
    {
        try (Scanner scanner = new Scanner(System.in)) 
        {
            System.out.println("Username:");
            username = scanner.nextLine();
            System.out.println("Password:");
            password = scanner.nextLine();
        }
    }

}
