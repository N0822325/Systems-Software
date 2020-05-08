/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspserver;

import java.util.*;
import java.io.*;
import java.net.*;
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
                     
                        //File file = new File(dis.readUTF() + ".csv");
                        //if (!file.exists()) { file.createNewFile(); }
                        
                        String id = dis.readUTF();

                        WSConnectedInfo wsconnectedinfoitems = new WSConnectedInfo(id, s);
                        
                        wsconnectedinfo.add(wsconnectedinfoitems); 
                        
                        saveCurrentWStoFile();
                }   

                //Pass Data to User
                if (received.equals("Get Data")) {
                    
                    String stationID = dis.readUTF();
                    
                    //File file = new File("WSData.csv");
//                    if (!file.exists()) { file.createNewFile(); }
//                    
//                    String[] Data = readCSV(file, stationID);
//                    
//                    if(Data == null) {
//                        dos.writeBoolean(false);
//                    }
//                    else {
//                        dos.writeBoolean(true);
//                        for(String current : Data)
//                            { dos.writeUTF(current); }
//                    }
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
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
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
    
} 

