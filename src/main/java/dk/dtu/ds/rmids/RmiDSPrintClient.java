
package dk.dtu.ds.rmids;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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
    private static  String usernameClient = "";
    private static  String passwordClient = "";
    private static AESCrypto aes = new AESCrypto("ljksdf9342kjdfs9");
    private static boolean sessionOn = false;
    private static int printOperation;
    private static int counter = 0;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException
    {
        RmiDSPrintService print = (RmiDSPrintService) Naming.lookup("rmi://localhost:5019/printservice");
        System.out.println("***** " + print.ehco("Printer Ready ") + "*****");
        
        TypeUsernameAndPassword();
        while(sessionOn == false)
        {
            try {
                usernameClient = aes.encrypt(usernameClient);
                passwordClient = aes.encrypt(passwordClient);
            } catch (Exception ex) {
                Logger.getLogger(RmiDSPrintClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            User user = new User(usernameClient, passwordClient);
            
            if(print.login(user) == true)
            {
                sessionOn = true;
                counter = 10;
            }
            else
            {
                System.out.println("Wrong password or username!");
                TypeUsernameAndPassword();
            }
        }
        
        while (sessionOn && counter > 0)
        {
            
            System.out.println("Choose Service: [4: Start Printer, 5: Stop Printer]");
            printOperation = scanner.nextInt();
            
            
            
            switch (printOperation)
            {
                case 1: print.print("Kageopskrift - Brunkager", "HP Deskjet 8867");
                break;
                
                case 2: print.queue();
                break;
                
                case 3: print.topQueue(19);
                break;
                
                case 4: print.start();
                System.out.println("The printer was started!");
                break;
                
                case 5: print.stop();
                System.out.println("The printer was stoped!");
                break;
                
                case 6: print.restart();
                System.out.println("The printer was restarted!");
                break;
                
                case 7: print.status();
                break;
                
                case 8: print.start();
                break;
            }
            
            counter--;
        }
        
    }
    
    static void TypeUsernameAndPassword()
    {
        
        System.out.println("Username:");
        usernameClient = scanner.nextLine();
        System.out.println("Password:");
        passwordClient = scanner.nextLine();
        
        
    }
    
}
