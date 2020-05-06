package wspserver;

import java.net.*; 
import java.io.*; 


public class WSPServer {

    public static void main(String[] args) throws IOException  {  
        
        new ServerGUI().setVisible(true);
        
        WSPServer obj = new WSPServer();
        obj.run(args);
        
    }
    
    public void run (String[] args) throws IOException {
        
        // Search for users
        Runnable r = new SearchSocket(9090);
        new Thread(r).start();
        
        // Search for stations
        Runnable r2 = new SearchSocket(9091);
        new Thread(r2).start();
        
    }
    
    
    // Thread creator
    public class SearchSocket implements Runnable {
        
        int socketNumber;
        
        public SearchSocket(int socketNumber_){
            socketNumber = socketNumber_;
        }
        
        public void run() {
            try{
                connect(socketNumber);
            }
            catch (Exception e){
                
            }
        }
        
    }
    
    
    public void connect(int socketNumber) throws IOException {
        
        String type = (socketNumber == 9090)? "User" : "Weather Station";
        
        ServerSocket server = new ServerSocket(socketNumber);
        System.out.println(type + " Server Online");
        
        while (true) {
            Socket socket = null;

            try
            {
                // socket object to receive incoming client requests 
                socket = server.accept();
                
                System.out.println("A new " + type + " client is connected : " + socket); 

                DataInputStream DIS = new DataInputStream(socket.getInputStream()); 
                DataOutputStream DOS = new DataOutputStream(socket.getOutputStream()); 

                System.out.println("Assigning new thread for this " + type + " client");

                Thread t = new ClientHandler(socket, DIS, DOS);

                t.start();
            }
            catch (Exception e){
                socket.close();
                e.printStackTrace();
            }

        }
    }

    

            
}
        
    

