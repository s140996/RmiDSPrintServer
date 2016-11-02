
package dk.dtu.ds.rmids;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Anders, Steen & Christoffer
 */
public class RmiDSPrintClient 
{
    private static  String usernameClient;
    private static  String passwordClient;
    private static AESCrypto aes = new AESCrypto("ljksdf9342kjdfs9");
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException 
    {
        RmiDSPrintService print = (RmiDSPrintService) Naming.lookup("rmi://localhost:5019/printservice");
        System.out.println("***** " + print.ehco("Printer Ready ") + "*****");
        
        TypeUsernameAndPassword();
        
        try {
            usernameClient = aes.encrypt(usernameClient);
            passwordClient = aes.encrypt(passwordClient);
        } catch (Exception ex) {
            Logger.getLogger(RmiDSPrintClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        User user = new User(usernameClient, passwordClient);
        
        if(print.login(user) == true)
        {
            System.out.println("Jubiiii");
        }
        else
        {
            System.out.println("Wrong password or username!");
            //TypeUsernameAndPassword();
        }
        
    }

    static void TypeUsernameAndPassword()
    {
        try (Scanner scanner = new Scanner(System.in)) 
        {
            System.out.println("Username:");
            usernameClient = scanner.nextLine();
            System.out.println("Password:");
            passwordClient = scanner.nextLine();
            scanner.close();
        }
    }

}
