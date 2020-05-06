package wspclient2;

import java.net.*; 
import java.io.*; 
import java.util.Scanner;

// Weather Station Client

public class WSPClient2 {

    public static void main(String[] args) throws IOException  
    { 
        try
        { 
            Scanner scn = new Scanner(System.in); 
              
            // getting localhost ip 
            InetAddress ip = InetAddress.getByName("localhost"); 
      
            // establish the connection with server port 9091 
            Socket socket = new Socket(ip, 9091);
      
            DataInputStream DIS = new DataInputStream(socket.getInputStream()); 
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream()); 
      
            // exchanges information between client and client handler 
            while (true)  
            { 
                System.out.println(DIS.readUTF()); 
                String tosend = scn.nextLine(); 
                DOS.writeUTF(tosend); 
                  
                // If client sends exit,close this connection  
                // and then break from the while loop 
                if(tosend.equals("Exit")) 
                { 
                    System.out.println("Closing this connection : " + socket); 
                    socket.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 
                  
                // printing date or time as requested by client 
                String received = DIS.readUTF(); 
                System.out.println(received); 
            } 
              
            // closing resources 
            scn.close(); 
            DIS.close(); 
            DOS.close(); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
    } 
} 