package wspuserclient1;

import java.net.*; 
import java.io.*; 
import java.util.Scanner;
import javax.swing.JOptionPane;

// User Client 

public class UserClient1 {

 public static void main(String[] args) throws IOException  
    {
        
        boolean run = true;
        
        while(run){
            
            try
            { 
                Scanner scn = new Scanner(System.in);

                // getting localhost ip 
                InetAddress ip = InetAddress.getByName("localhost"); 

                // establish the connection with server port 9090 
                Socket s = new Socket(ip, 9090);

                // obtaining input and out streams 
                DataInputStream dis = new DataInputStream(s.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                // Setup gui form
                new UserLogin("user",dis,dos).setVisible(true);;
                
                
                run = false;

                // closing resources 
                //scn.close();
                //dis.close();
                //dos.close();
            }catch(Exception e){

                String[] Options = {"Retry","Exit"};

                int response = JOptionPane.showOptionDialog(null, "Server is Offline", "Connection Error",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, Options, Options[0]);

                run = (response == 0)? true : false;
            } 
        }
    } 
} 