package wspclient2;

import java.net.*; 
import java.io.*; 
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JDialog;

// Weather Station Client

public class WSPClient2 {

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
                Socket s = new Socket(ip, 9091);

                // obtaining input and out streams 
                DataInputStream dis = new DataInputStream(s.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                // Setup gui form
                

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
    
    
    
    public static int getX(){
        int result = (int)(Math.random()*100);
        return result;
    }
    
    
    
}

