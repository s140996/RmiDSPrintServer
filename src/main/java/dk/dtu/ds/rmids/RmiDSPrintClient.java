
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
    private static String usernameClient = "";
    private static String passwordClient = "";
    private static String filename = "The Hidden Agenda";
    private static String printername = "HP Deskjet 8400 CJK";
    private static AESCrypto aes = new AESCrypto("ljksdf9342kjdfs9");
    private static boolean sessionOn = false;
    private static int printOperation;
    private static int counter = 0;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException
    {
        RmiDSPrintService print = (RmiDSPrintService) Naming.lookup("rmi://localhost:5019/printservice");
        System.out.println("***** " + print.startUp("Print Server Ready ") + "*****");
        System.out.println("*************************************************");
        
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
                System.out.println("Succesfull login!");
                System.out.println("*******************************");
            }
            else
            {
                System.out.println("Wrong password or username!");
                System.out.println("*******************************");
                TypeUsernameAndPassword();
            }
        }
        
        while (sessionOn && counter > 0)
        {
            System.out.println("*****************************************************************************************************************************************");
            System.out.println("Number of remaining operations in this session: " + counter);
            System.out.println("*****************************************************************************************************************************************");
            System.out.println("Choose Service: [1: Print, 2: Queue, 3: Top Queue, 4: Start Printer, 5: Stop Printer, 6: Restart, 7: Status, 8: Read Config, 9: Set Config]");
            
            printOperation = scanner.nextInt();
            System.out.println("*****************************************************************************************************************************************");
            
            
            switch (printOperation)
            {
                case 1: print.print(filename, printername);
                        System.out.println(filename + " Was printed on: " + printername);
                break;
                
                case 2: print.queue();
                        System.out.println("The print queue is empty");
                break;
                
                case 3: print.topQueue(19);
                        System.out.println("The print job was moved to the top of the queue");
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
                        System.out.println("No status available!");
                break;
                
                case 8: 
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
