package wspserver;

import java.net.*; 
import java.io.*; 
import java.text.*; 
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JDialog;

class ClientHandler extends Thread
{
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    
    int ID = -1;

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)  
    { 
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    } 
  
    @Override
    public void run()
    {
        while (true)
        {
            try {
                
                String received = dis.readUTF();
                
                // Login
                if(received.equals("Register") || received.equals("Login")){
                     
                        File file = new File(dis.readUTF() + ".csv");
                        if (!file.exists()) { file.createNewFile(); }
                        
                        String user = dis.readUTF();
                        String pass = dis.readUTF();
                        String[] loginInfo = {user, pass};
                        
                        
                        if(received.equals("Register"))
                        {
                            
                            if(!checkForUser(file,user))
                                { writeCSV(file,user,pass); }
                            else
                            { 
                                JOptionPane optionPane = new JOptionPane();
                                optionPane.setMessage("User Already Exists");
                                JDialog dialog = optionPane.createDialog("Registry Error");
                                dialog.setAlwaysOnTop(true);
                                dialog.setVisible(true);
                                
                                dos.writeBoolean(false);
                                continue;
                            }
                        } 
                       
                        dos.writeBoolean(checkPass(file, loginInfo));
                    
                }
                
                // 
                
            
            }
            catch(IOException e) {
                e.printStackTrace();
                break;
            }
            
        }
        try
        { 
            // closing resources 
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    }
    
    
    private boolean checkForUser(File file, String input) throws IOException  {
        
        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader(file));

        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");

            if(data[0].equals(input)) { return true; }
        }
        
        return false;
        
    }
    
    private boolean checkPass(File file, String[] input) throws IOException  {
        
        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader(file));

        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");

            // Check Username
            if(data[0].equals(input[0])) {

                // Check Password
                if(data[1].equals(input[1])){ return true; }
            }
        }
        
        return false;
        
    }
    
    public String[] readCSV(File file, String input) throws IOException  {
        
        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader(file));

        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");

            if(data[0] == input) { return data; }
        }
        
        return null;
        
    }
    
    public void writeCSV(File file, String ID, String Pass) throws IOException {
        
        FileWriter csvWriter = new FileWriter(file, true);
               
        csvWriter.append(ID);
        csvWriter.append("," + Pass);
        csvWriter.append("\n");
        
        csvWriter.close();
    }
    
} 