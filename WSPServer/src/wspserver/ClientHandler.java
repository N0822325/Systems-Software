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
    public ArrayList<UConnectedInfo> uconnectedinfo;
    
    

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)  
    { 
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        populateArrayList();
        uconnectedinfo = new ArrayList<UConnectedInfo>();
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
                
                // Pass Data to User
                else if (received.equals("Get Data")) {
                    
                    String stationID = dis.readUTF();
                    
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
                if(data[1].equals(input[1]))
                { 
                    
                    csvReader.close(); 
                    
                    UConnectedInfo uconnectedinfoitems = new UConnectedInfo(input[1]);
                        
                    uconnectedinfo.add(uconnectedinfoitems); 
                        
                    saveCurrentWStoFile();
                    
                    return true; 


                    
                }
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
                System.out.println(elements[i]);
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
    
     private void populateArrayList()
    {
        try
        {    
            {
                File file = new File("ConnectedU.dat");
                if (!file.exists()) { file.createNewFile(); }
            }
            
            
            FileInputStream file = new FileInputStream("ConnectedU.dat");
            ObjectInputStream inputFile = new ObjectInputStream(file);
            
            boolean endOfFile = false;
            
            while(!endOfFile)
            {
                try
                {
                    uconnectedinfo.add((UConnectedInfo) inputFile.readObject());
                }
                catch(EOFException e)
                {
                    endOfFile = true;     
                }
                catch (Exception f)
                {
                    JOptionPane.showMessageDialog(null, f.getMessage());
                }
                
            }
            
            inputFile.close();
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
     
    private void saveCurrentWStoFile()
    {
        try
        {    
            FileOutputStream file = new FileOutputStream("ConnectedU.dat");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
            
            for (int i = 0 ; i < uconnectedinfo.size() ; i++)
            {
                outputFile.writeObject(uconnectedinfo.get(i));
            }
            
            outputFile.close();
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
} 