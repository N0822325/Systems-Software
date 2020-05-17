package wspwsclient1;

import java.net.*; 
import java.io.*; 
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class WClient extends javax.swing.JFrame {

    DataInputStream dis;
    DataOutputStream dos;
    
    
    
    DataStream DS = new DataStream();
    
    int field = 1;
    String crop = "Onion";
    String wsID = "WS1";
    
    List<String> WSList = new ArrayList<String>();
    
    
    public WClient(DataInputStream DIS, DataOutputStream DOS) throws IOException {
        dis = DIS;
        dos = DOS;
        initComponents();

        DS.addStation(wsID);
        DS.getStations();
        
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

        // Output Data Loop
        while (true){
            
            DS.setData(wsID);
//            for(String s : WSList) {
//
//                DS.setData(s);
//                
//            }
            
            try { TimeUnit.SECONDS.sleep(10); }
            catch (Exception e) {}
        }
        
        
    }
    
    
    public int getNum(){
        int result = (int)(Math.random()*100);
        return result;
    }
    
    private class DataStream {
        
    
    
        synchronized private void getStations() {

            try {
            
                dos.writeUTF("All Stations");

                int Count = dis.readInt();


                DefaultListModel<String> newList = new DefaultListModel<String>(); 

                if(Count < 1) { OnlineList.setModel(newList) ; return; }

                for(int i = 0; i < Count; i++){

                    String WS = dis.readUTF();

                    newList.addElement(WS); 

                    WSList.add(WS);

                }

                OnlineList.setModel(newList);
            
            }
            catch(IOException e){}

        }

        synchronized private void setData(String ID) throws IOException {

            dos.writeUTF("Set Data");

            dos.writeUTF(ID);

            dos.writeInt(4);

            dos.writeUTF(Integer.toString(field));
            dos.writeUTF(crop);
            dos.writeUTF(Integer.toString(getNum()));
            dos.writeUTF(Integer.toString(getNum()));

            dis.readBoolean();

        }
    
        
        synchronized private void addStation(String ID){
            
            try
            {
                dos.writeUTF("Add Station");
                
                dos.writeUTF(ID);
   
                if(dis.readBoolean())
                    { getStations(); }
            }
            catch(IOException e){}
        }
        
        synchronized private void removeStation(String ID){
            
            try
            {
                dos.writeUTF("Remove Station");
            
                dos.writeUTF(ID);
                 
                if(dis.readBoolean())
                    { getStations(); }
            }
            catch(IOException e){}
        }
        
        
//        public void windowClosing(java.awt.event.WindowEvent evt) 
//        {
//            DS.removeStation(wsID);
//            System.exit(0); 
//        }
        
        
        
    
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        OnlineList = new javax.swing.JList<>();
        RefreshButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        jLabel1.setText("Weather Client");

        OnlineList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                OnlineListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(OnlineList);

        RefreshButton.setText("↻");
        RefreshButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        RefreshButton.setFocusable(false);
        RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Online Weather Stations");

        jButton1.setText("Station Offline");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(RefreshButton)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RefreshButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton1)
                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void OnlineListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_OnlineListValueChanged
        
    }//GEN-LAST:event_OnlineListValueChanged

    private void RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshButtonActionPerformed
        DS.getStations();
    }//GEN-LAST:event_RefreshButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            DS.removeStation(wsID);
            System.exit(0); 
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> OnlineList;
    private javax.swing.JButton RefreshButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
