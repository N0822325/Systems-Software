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
    
    long wsAdd;

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)  
    { 
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        
        wsAdd = new File("ws.csv").lastModified();
    } 
  
    @Override
    public void run()
    {
        while (true)
        {
            try {
                
                String received = dis.readUTF();
                System.out.println(received);
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
                
                // Add Weather Station to User
                else if (received.equals("Add Station")){
                    
                    String ID = dis.readUTF();
                    String[] stationID = { dis.readUTF() };
                    
                    File file = new File("user.csv");
                    if (!file.exists()) { file.createNewFile(); }
                    
                    dos.writeBoolean(
                            appendCSV(file,ID,stationID)
                    );
                    
                }
                
                // Remove Weather Station to User
                else if (received.equals("Remove Station")){
                    
                    String ID = dis.readUTF();
                    String[] stationID = { dis.readUTF() };
                    
                    File file = new File("user.csv");
                    if (!file.exists()) { file.createNewFile(); }
                    
                    dos.writeBoolean(
                            removeCSV(file,ID,stationID)
                    );
                    
                }
                
                // Loading Weather Stations for User
                else if (received.equals("Load Stations")){
                    
                    String ID = dis.readUTF();
                    
                    File file = new File("user.csv");
                    if (!file.exists()) { file.createNewFile(); }
                    
                    String[] data = readCSV(file,ID);
                    
                    if (data.length < 3){ dos.writeUTF(""); continue; }

                    for(int i = 2; i < data.length; i ++)
                    {
                        dos.writeUTF(data[i]);
                    }
                
                }
                
                // Loading All Weather Stations
                else if (received.equals("All Stations")){
                    
                    File file = new File("ws.csv");
                    if (!file.exists()) { file.createNewFile(); }
                    boolean start = dis.readBoolean();
                    boolean add = file.lastModified() != wsAdd;
                    
                    dos.writeBoolean( (add||start) );
                    
                    if( !(add||start)) { continue; }
                    
                    System.out.println(true);
                    
                    wsAdd = file.lastModified();
                    List<String> data = readAll(file);
                    
                    dos.writeInt(data.size());
                    
                    if (data.isEmpty()) { dos.writeUTF(""); continue; }

                    for(String s : data)
                    {
                        dos.writeUTF(s);
                    }
                
                }
                
                
                // Pass Data to User
                else if (received.equals("Get Data")) {
                    
                    String stationID = dis.readUTF();
                    System.out.println(stationID);
                    File file = new File("WSData.csv");
                    if (!file.exists()) { file.createNewFile(); }
                    
                    String[] Data = readCSV(file, stationID);
                    
                    
                    if(Data == null) {
                        dos.writeBoolean(false);
                    }
                    else {
                        dos.writeBoolean(true);
                        for(String current : Data)
                            { dos.writeUTF(current); }
                    }
                }
                
                else if (received.equals("test")) {
                    
                    dos.writeBoolean(true);
                    
                }
                
            
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

            if(data[0].equals(input)) { csvReader.close(); return true; }
        }
        
        csvReader.close();
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
                if(data[1].equals(input[1])){ csvReader.close(); return true; }
            }
        }
        
        csvReader.close();
        return false;
        
    }
    
    private String[] readCSV(File file, String input) throws IOException  {
        
        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader(file));

        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");

            if(data[0].equals(input)) { csvReader.close(); return data; }
        }
        
        csvReader.close();
        return null;
        
    }
    
    private List<String> readAll(File file) throws IOException  {
        
        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader(file));

        List<String> l = new ArrayList<String>();
        
        while ((row = csvReader.readLine()) != null) {
            l.add(row);
        }
        
        csvReader.close();
        return l;
        
    }
    
    private void writeCSV(File file, String ID, String Pass) throws IOException {
        
        FileWriter csvWriter = new FileWriter(file, true);
               
        csvWriter.append(ID);
        csvWriter.append("," + Pass);
        csvWriter.append("\n");
        
        csvWriter.close();
    }
    
    private boolean appendCSV(File file, String ID, String[] WS) throws IOException {
   
        String oldLine = "";
      
        Scanner sc = new Scanner(file);
        StringBuffer buffer = new StringBuffer();
      
        while (sc.hasNextLine()) {
            String next = sc.nextLine();
            buffer.append(next + System.lineSeparator());
            
            String[] data = next.split(",");
            if(data[0].equals(ID)){ oldLine = next; }
        }
        String fileContents = buffer.toString();     
        
        sc.close();
        
      
        String newLine = oldLine;
        
        for (String current : WS)
        {
            boolean add = true;
            String[] elements = newLine.split(",");
            
            for(int i = 2; i < elements.length; i++)
            {
                if (current.equals(elements[i])) { add = false; }
            }
            
            if (add)
                { newLine += "," + current; }
        }
        
        if (newLine.equals(oldLine))
        {
            return false;
        }

      
        fileContents = fileContents.replaceAll(oldLine, newLine);
      
        FileWriter writer = new FileWriter(file);

        writer.append(fileContents);
        writer.flush();
        
        writer.close();
        
        return true;
    }
    
    private boolean removeCSV(File file, String ID, String[] WS) throws IOException {
   
        String oldLine = "";
      
        Scanner sc = new Scanner(file);
        StringBuffer buffer = new StringBuffer();
      
        while (sc.hasNextLine()) {
            String next = sc.nextLine();
            buffer.append(next + System.lineSeparator());
            
            String[] data = next.split(",");
            if(data[0].equals(ID)){ oldLine = next; }
        }
        String fileContents = buffer.toString();     
        
        sc.close();
        
      
        String newLine = "";
        String[] elements = oldLine.split(",");
        newLine += elements[0] + "," + elements[1];
        
        for (String current : WS)
        {

   
            for(int i = 2; i < elements.length; i++)
            {
                if (!current.equals(elements[i])) { 
                    newLine += "," + elements[i];
                }
            }
            

        }
        
        if (newLine.equals(oldLine))
        {
            return false;
        }

      
        fileContents = fileContents.replaceAll(oldLine, newLine);
      
        FileWriter writer = new FileWriter(file);

        writer.append(fileContents);
        writer.flush();
        
        writer.close();
        
        return true;
    }
    
} 