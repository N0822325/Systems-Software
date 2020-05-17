package wspwsclient1;

import java.net.*; 
import java.io.*; 
import java.util.Scanner;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JDialog;

// Weather Station Client

public class WSClient1 {

    public static void main(String[] args) throws IOException  
    {
        
        
        
        while(true){
            
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
                
                
                
                new WClient(dis,dos).setVisible(true);
                
                
                break;
                // closing resources 
                //scn.close();
                //dis.close();
                //dos.close();
            }catch(Exception e){

                String[] Options = {"Retry","Exit"};

                int response = JOptionPane.showOptionDialog(null, "Server is Offline", "Connection Error",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, Options, Options[0]);
                
                if(response == 1)
                    { break; }
                    
            }
            
            
        } 
        
    }
  
}

