package wspclient1;

import java.util.*;
import java.net.*; 
import java.io.*; 
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

public class UserClient extends javax.swing.JFrame {

    UserLogin log;
    DataInputStream dis;
    DataOutputStream dos;
    String id;
    
    DataStreams DS = new DataStreams();
    String selected = "";

    public UserClient(UserLogin LOG, String ID, DataInputStream DIS, DataOutputStream DOS) throws IOException {
        log = LOG;
        id = ID;
        dis = DIS;
        dos = DOS;
        initComponents();

        DS.getStations(true);
        DS.getRegStations();
        WSOutput();
        
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
        
        while(true){

            try {

                if(!(onlineList.getSelectedValue() == null))
                { 
                    DS.getData();
                }
                DS.getStations(false);

                
                try { TimeUnit.SECONDS.sleep(1); }
                catch (Exception e) {}
                
                
            }
            catch(IOException e){}
            
            
        }
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        WSList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        FieldOutputBox = new javax.swing.JTextField();
        CropOutputBox = new javax.swing.JTextField();
        TemperatureOutputBox = new javax.swing.JTextField();
        HumidityOutputBox = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        onlineList = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        AllWSList = new javax.swing.JList<>();
        RegisterButton = new javax.swing.JButton();
        RemoveButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User Client");
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        WSList.setFocusable(false);
        WSList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                WSListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(WSList);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("All Online Stations");
        jLabel2.setFocusable(false);

        jLabel3.setText("Field");

        jLabel4.setText("Temperature");

        jLabel5.setText("Humidity");

        jLabel6.setText("Crop Type");

        FieldOutputBox.setEditable(false);
        FieldOutputBox.setFocusable(false);
        FieldOutputBox.setMinimumSize(new java.awt.Dimension(0, 0));
        FieldOutputBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldOutputBoxActionPerformed(evt);
            }
        });

        CropOutputBox.setEditable(false);
        CropOutputBox.setFocusable(false);
        CropOutputBox.setMinimumSize(new java.awt.Dimension(0, 0));
        CropOutputBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CropOutputBoxActionPerformed(evt);
            }
        });

        TemperatureOutputBox.setEditable(false);
        TemperatureOutputBox.setFocusable(false);
        TemperatureOutputBox.setMinimumSize(new java.awt.Dimension(0, 0));
        TemperatureOutputBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TemperatureOutputBoxActionPerformed(evt);
            }
        });

        HumidityOutputBox.setEditable(false);
        HumidityOutputBox.setFocusable(false);
        HumidityOutputBox.setMinimumSize(new java.awt.Dimension(0, 0));
        HumidityOutputBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HumidityOutputBoxActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(onlineList);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Available Stations");

        jButton1.setText("Log Out");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registered Stations");

        AllWSList.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                AllWSListComponentResized(evt);
            }
        });
        jScrollPane3.setViewportView(AllWSList);

        RegisterButton.setText("Register");
        RegisterButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        RegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterButtonActionPerformed(evt);
            }
        });

        RemoveButton.setText("Remove");
        RemoveButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveButtonActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RemoveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(RegisterButton, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addComponent(FieldOutputBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addComponent(CropOutputBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addComponent(TemperatureOutputBox, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(HumidityOutputBox, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(RegisterButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(RemoveButton))
                                    .addComponent(jScrollPane3)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FieldOutputBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CropOutputBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TemperatureOutputBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HumidityOutputBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FieldOutputBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldOutputBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldOutputBoxActionPerformed

    private void CropOutputBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CropOutputBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CropOutputBoxActionPerformed

    private void TemperatureOutputBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TemperatureOutputBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TemperatureOutputBoxActionPerformed

    private void HumidityOutputBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HumidityOutputBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HumidityOutputBoxActionPerformed

    private void WSListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_WSListValueChanged

    }//GEN-LAST:event_WSListValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        log.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        
    }//GEN-LAST:event_formComponentHidden

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        
    }//GEN-LAST:event_formWindowStateChanged

    private void RegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterButtonActionPerformed

        DS.register();
        WSOutput();
        
    }//GEN-LAST:event_RegisterButtonActionPerformed

    private void RemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveButtonActionPerformed
      
        DS.remove();
        WSOutput();
        
    }//GEN-LAST:event_RemoveButtonActionPerformed

    private void AllWSListComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_AllWSListComponentResized
        
    }//GEN-LAST:event_AllWSListComponentResized

    private class DataStreams {
 
    
        synchronized private void getData() throws IOException {
 
            String s = onlineList.getSelectedValue();

            dos.writeUTF("Get Data");

            dos.writeUTF(s);

            boolean trueeeee = dis.readBoolean();

            if(trueeeee){
                dis.readUTF();

                FieldOutputBox.setText(dis.readUTF());
                CropOutputBox.setText(dis.readUTF());
                TemperatureOutputBox.setText(dis.readUTF());                   
                HumidityOutputBox.setText(dis.readUTF());
            }
            else{
                resetOutput();
            }

        }

        synchronized private void getRegStations() throws IOException{

            dos.writeUTF("Load Stations");
            DefaultListModel<String> newList = new DefaultListModel<String>();

            dos.writeUTF(id);

            do 
            {           
                String WS = dis.readUTF();
                if(WS.isBlank()) { break; }

                newList.addElement(WS);

            } while(dis.available() > 0);

            WSList.setModel(newList);

        }

        synchronized private void getStations(boolean start) throws IOException {
       
            dos.writeUTF("All Stations");
            dos.writeBoolean(start);
            boolean t = dis.readBoolean();


            if(t){

                DefaultListModel<String> newList = new DefaultListModel<String>();

                int Count = dis.readInt();

                for(int i = 0; i < Count; i++){

                    String WS = dis.readUTF();

                    newList.addElement(WS);

                }

                AllWSList.setModel(newList);
                WSOutput();
            }
        
        }
        
        
        synchronized private void register(){
            
            String s = AllWSList.getSelectedValue();
            if(s == null) { return; }
            if(s.isBlank()) { return; }

            try{
                dos.writeUTF("Add Station");

                dos.writeUTF(id);
                dos.writeUTF(s);

                if(dis.readBoolean())
                {
                    getRegStations();
                }


            }
            catch (IOException e){}
            
            resetOutput();
        }
        
        synchronized private void remove(){
            
            String s = WSList.getSelectedValue();
            if(s == null) { return; }
            if(s.isBlank()) { return; }

            try {
                dos.writeUTF("Remove Station");

                dos.writeUTF(id);
                dos.writeUTF(s);

                if(dis.readBoolean())
                {
                    getRegStations();

                    resetOutput();
                }

            }
            catch (IOException e){}
            
            resetOutput();
            
        }
        
        private void resetOutput(){
        
            FieldOutputBox.setText("");
            CropOutputBox.setText("");
            TemperatureOutputBox.setText("");                   
            HumidityOutputBox.setText("");
        
        }
        
    }
    
    private void WSOutput(){

        ListModel model1 = WSList.getModel();
        ListModel model2 = AllWSList.getModel();
        
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        
        for (int i = 0; i < model1.getSize(); i++){
            list1.add(model1.getElementAt(i).toString());
        }
        for (int i = 0; i < model2.getSize(); i++){
            list2.add(model2.getElementAt(i).toString());
        }
        

        DefaultListModel<String> newList = new DefaultListModel<String>();
        
        for (String s : list2){
            if(list1.contains(s)){
                newList.addElement(s);
            }
        }

        onlineList.setModel(newList);
      
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> AllWSList;
    private javax.swing.JTextField CropOutputBox;
    private javax.swing.JTextField FieldOutputBox;
    private javax.swing.JTextField HumidityOutputBox;
    private javax.swing.JButton RegisterButton;
    private javax.swing.JButton RemoveButton;
    private javax.swing.JTextField TemperatureOutputBox;
    private javax.swing.JList<String> WSList;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> onlineList;
    // End of variables declaration//GEN-END:variables
}
