/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspserver;

import java.net.*; 
import java.io.*; 


public class WSPServer {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
        
        ServerSocket server = new ServerSocket(9090);
        ServerSocket server2 = new ServerSocket(9091);
        
        System.out.println("Server Online");
        
        while (true) {
            Socket u = null;
            Socket ws = null;
            
            try
            {
                // socket object to receive incoming client requests 
                while(true){
                    try{
                        u = server.accept();
                        System.out.println(u);
                    }
                    finally{
                        break;
                    }
                }

                if (u != null)
                {
                  
                    System.out.println("A new Uclient is connected : " + u); 

                    // obtaining input and out streams 
                    DataInputStream dis = new DataInputStream(u.getInputStream()); 
                    DataOutputStream dos = new DataOutputStream(u.getOutputStream()); 

                    System.out.println("Assigning new thread for this client"); 

                    // create a new thread object 
                    Thread t = new ClientHandler(u, dis, dos); 

                    // Invoking the start() method 
                    t.start();
                    u = null;
                    break;
                }  
                
                while(true){
                    try{
                        ws = server2.accept();
                        System.out.println(ws);
                    }
                    finally{
                        break;
                    }
                }
                
                if (ws != null)
                {
                                      
                    System.out.println("A new WSclient is connected : " + ws); 

                    // obtaining input and out streams 
                    DataInputStream dis = new DataInputStream(ws.getInputStream()); 
                    DataOutputStream dos = new DataOutputStream(ws.getOutputStream()); 

                    System.out.println("Assigning new thread for this client"); 

                    // create a new thread object 
                    Thread t = new ClientHandler(ws, dis, dos); 

                    // Invoking the start() method 
                    t.start();
                    ws = null; 
                }
            }
            catch (Exception e){
                u.close();
                ws.close();
                e.printStackTrace();
            }
        }
    }
    
}


    
/*    public static void main(String[] args) throws IOException {
        
        ServerSocket server = new ServerSocket(9090);
        
        System.out.println("Server Online");
        
        while (true) {
                Socket client = server.accept();
                System.out.println("Client Connected");
                
                InputStreamReader in = new InputStreamReader(client.getInputStream());
                BufferedReader bf = new BufferedReader(in);
                
                String str = bf.readLine();
                System.out.println("client: "+ str);
        }

    }
    
}
*/