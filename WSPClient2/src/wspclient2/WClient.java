package wspclient2;

import java.net.*; 
import java.io.*; 
import java.util.Scanner;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultListModel;

public class WClient extends javax.swing.JFrame {

    DataInputStream dis;
    DataOutputStream dos;
    
    DataStream DS = new DataStream();
    
    List<String> WSList = new ArrayList<String>();
    
    
    public WClient(DataInputStream DIS, DataOutputStream DOS) throws IOException {
        dis = DIS;
        dos = DOS;
        initComponents();

        
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

            for(String s : WSList) {

                DS.setData(s);
                
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

            dos.writeUTF(Integer.toString(getNum()));
            dos.writeUTF(getCrop());
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
                    { getStations(); jTextField1.setText(""); }
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
    
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        OnlineList = new javax.swing.JList<>();
        RemoveButton = new javax.swing.JButton();
        AddButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        RefreshButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Weather Client");

        OnlineList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                OnlineListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(OnlineList);

        RemoveButton.setText("Remove Selected");
        RemoveButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveButtonActionPerformed(evt);
            }
        });

        AddButton.setText("Add");
        AddButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        RefreshButton.setText("↻");
        RefreshButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        RefreshButton.setFocusable(false);
        RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Online Weather Stations");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(AddButton, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(RemoveButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(RefreshButton)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel1)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        
    // Accept only number inputs for ID
        
        int result = (int) evt.getKeyChar();

        if( result < 48 || result > 57 ){
            evt.setKeyChar(((char)0));
        }
        
    }//GEN-LAST:event_jTextField1KeyTyped

    private void RemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveButtonActionPerformed

        String remove = OnlineList.getSelectedValue(); 
        if(remove != null)
            { DS.removeStation(remove); }

    }//GEN-LAST:event_RemoveButtonActionPerformed

    private void OnlineListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_OnlineListValueChanged
        
    }//GEN-LAST:event_OnlineListValueChanged

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed

        String add = jTextField1.getText();
        if(!add.isBlank())
            { DS.addStation(add); }

    }//GEN-LAST:event_AddButtonActionPerformed

    private void RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshButtonActionPerformed
        DS.getStations();
    }//GEN-LAST:event_RefreshButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JList<String> OnlineList;
    private javax.swing.JButton RefreshButton;
    private javax.swing.JButton RemoveButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
