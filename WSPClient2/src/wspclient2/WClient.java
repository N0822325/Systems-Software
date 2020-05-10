package wspclient2;

import java.net.*; 
import java.io.*; 
import java.util.Scanner;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WClient extends javax.swing.JFrame {

    DataInputStream dis;
    DataOutputStream dos;
    
    List<String[]> WSList = new ArrayList<String[]>();
    
    public WClient(DataInputStream DIS, DataOutputStream DOS) throws IOException {
        dis = DIS;
        dos = DOS;
        initComponents();
        
        Runnable r = new MyRunnable();
        new Thread(r).start();
        
    }
    
    public class MyRunnable implements Runnable {


        public MyRunnable() {
            
        }

        public void run() {
            try{
               r(); 
            }
            catch (Exception e){
                
            }
        }
    }
    
    private void r() throws IOException {
        
        // Get Data
        dos.writeUTF("Get All");

        do
        {
            String WS = dis.readUTF();

            String[] Split = WS.split(",");
            
            if(WS == null)
                { System.out.println("sssssddddd"); }
            else
                { WSList.add(Split); }

        } while(dis.available() > 0);
        
        
        // Output Data Loop
        while (true){

            for(String[] s : WSList) {
                //System.out.println(s[0]);

                dos.writeUTF("Set Data");
                
                dos.writeUTF(s[0]);
                
                dos.writeInt(4);
           
                dos.writeUTF(Integer.toString(getNum()));
                dos.writeUTF(getCrop());
                dos.writeUTF(Integer.toString(getNum()));
                dos.writeUTF(Integer.toString(getNum()));

                dis.readBoolean();
                
            }
            
            try { TimeUnit.SECONDS.sleep(1); }
            catch (Exception e) {}
        }
        
        
    }
    
    
    public int getNum(){
        int result = (int)(Math.random()*100);
        return result;
    }
    
    public String getCrop(){
        
        List<String> List = Arrays.asList("Carrot", "Potato", "Onion");
        Random rand = new Random();
        String randomElement = List.get(rand.nextInt(List.size()));
        
        return randomElement;
    }
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Wether Client");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jLabel1)
                .addContainerGap(178, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addContainerGap(261, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
