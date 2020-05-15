package wspserver;

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;



class WSHandler extends Thread
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    ArrayList<WSConnectedInfo> wsconnectedinfo;
    
    
    public WSHandler (Socket s, DataInputStream dis, DataOutputStream dos)  
    { 
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        wsconnectedinfo = new ArrayList<WSConnectedInfo>();
    }

    
    @Override
    public void run()
    {
        while (true)
        {
            try {
                
                String received = dis.readUTF();
                
                // Login
                if(received.equals("ID")){
                     
                        File file = new File(dis.readUTF() + ".csv");
                        if (!file.exists()) { file.createNewFile(); }
                        
                        String id = dis.readUTF();

                        WSConnectedInfo wsconnectedinfoitems = new WSConnectedInfo(id, s);
                        
                        wsconnectedinfo.add(wsconnectedinfoitems); 
                        
                        saveCurrentWStoFile();
                }
                
                if (received.equals("Get All")){
                    
                    File file = new File("ws.csv");
                    if (!file.exists()) { file.createNewFile(); }
                    
                    List<String[]> l = readAll(file);
                    
                    //if(l.isEmpty()) { continue; }
                    
                    for (String[] s : l){
                        
                        dos.writeUTF(String.join(",", s));                       
                    }
                }

                
                if (received.equals("Get Data")) {

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
                
                //
                if (received.equals("Set Data")){
                    
                    String ID = dis.readUTF();
                    List<String> stationData = new ArrayList<String>();
                    
                    int Count = dis.readInt();
                    
                    for(int i = 0; i < Count; i++) {
                        stationData.add(dis.readUTF());
                    }
                    
                    File file = new File("WSData.csv");
                    if (!file.exists()) { file.createNewFile(); }
                    
                    writeCSV(file,ID,stationData);
                    
                    dos.writeBoolean(true);
                }
                
                
                
                // Loading All Weather Stations
                else if (received.equals("All Stations")){
                    
                    File file = new File("ws.csv");
                    if (!file.exists()) { file.createNewFile(); }

                    List<String[]> data = readAll(file);
                    
                    dos.writeInt(data.size());
                    
                    //if (data.isEmpty()) { dos.writeUTF(""); continue; }

                    for(String[] s : data)
                    {
                        dos.writeUTF(s[0]);
                    }
                
                }
                
                
                //
                else if (received.equals("Add Station")){
                    
                    File file = new File("ws.csv");
                    if (!file.exists()) { file.createNewFile(); }
                    
                    String ID = dis.readUTF();
                    
                    dos.writeBoolean(addWS(file,ID));
                    
                }
                
                else if (received.equals("Remove Station")){
                    
                    File file = new File("ws.csv");
                    if (!file.exists()) { file.createNewFile(); }
                    
                    String ID = dis.readUTF();
                    
                    dos.writeBoolean(removeWS(file,ID));
                    
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
    
    private void populateArrayList()
    {
        try
        {    
            {
                File file = new File("ConnectedWS.dat");
                if (!file.exists()) { file.createNewFile(); }
            }
            
            
            FileInputStream file = new FileInputStream("ConnectedWS.dat");
            ObjectInputStream inputFile = new ObjectInputStream(file);
            
            boolean endOfFile = false;
            
            while(!endOfFile)
            {
                try
                {
                    wsconnectedinfo.add((WSConnectedInfo) inputFile.readObject());
                }
                catch(EOFException e)
                {
                    endOfFile = true;     
                }
                catch (Exception f)
                {
                    //JOptionPane.showMessageDialog(null, f.getMessage());
                }
                
            }
            
            inputFile.close();
        }
        catch (IOException e)
        {
            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private void saveCurrentWStoFile()
    {
        try
        {    
            FileOutputStream file = new FileOutputStream("ConnectedWS.dat");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
            
            for (int i = 0 ; i < wsconnectedinfo.size() ; i++)
            {
                outputFile.writeObject(wsconnectedinfo.get(i));
            }
            
            outputFile.close();
        }
        catch (IOException e)
        {
            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
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
    
    private static List<String[]> readAll(File file) throws IOException {
        
        List<String[]> Output = new ArrayList<String[]>();

        BufferedReader csvReader = new BufferedReader(new FileReader(file));

        String row;
        int Count = 0;
        
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            
            Output.add(data);
        }
        
        csvReader.close();
        
        return Output;
    }
    
    private void writeCSV(File file, String ID, List<String> WS) throws IOException
    {
        // Check if exists
        if (readCSV(file, ID) == null){
            
            FileWriter csvWriter = new FileWriter(file, true);
               
            csvWriter.append(ID);
            for (String s : WS){
                csvWriter.append("," + s);
            }
            csvWriter.append("\n");

            csvWriter.close();
            
            return;
        }
        
        
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
        

        String[] elements = oldLine.split(",");
         
        String newLine = elements[0];
       
        for (String s : WS){
            newLine += "," + s;
        }
        

      
        fileContents = fileContents.replaceAll(oldLine, newLine);
      
        FileWriter writer = new FileWriter(file);

        writer.append(fileContents);
        writer.flush();
        
        writer.close();
        
    }
    
    
    private boolean appendCSV(File file, String ID, List<String> WS) throws IOException {

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
    
    private boolean addWS(File file, String ID) throws IOException {
        
        List<String[]> l = readAll(file);
        
        for(String[] s : l)
        {
            if(s[0].equals(ID))
            {
                return false;
            }
        }
        
        FileWriter csvWriter = new FileWriter(file, true);
        
        csvWriter.append("\n");
        csvWriter.append(ID);
        
        csvWriter.close();
        
        return true;
        
    }
    
    private boolean removeWS(File file, String ID) throws IOException {
      
        Scanner sc = new Scanner(file);
        List<String> L = new ArrayList<String>();
      
        while (sc.hasNextLine()) {
            String next = sc.nextLine();
            
            if(!next.equals(ID))
            {
                L.add(next);
                if(sc.hasNextLine()) { L.add("\n"); }
            }
            
        } 
        
        sc.close();


      
        FileWriter writer = new FileWriter(file);
        
        for(String s : L){
            writer.append(s);
        }
        
        writer.flush();
        
        writer.close();
        
        return true;

    }
} 

