package wspclient1;

import java.util.*;
import java.net.*; 
import java.io.*; 
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
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
    
    String selected = "";

    public UserClient(UserLogin LOG, String ID, DataInputStream DIS, DataOutputStream DOS) throws IOException {
        log = LOG;
        id = ID;
        dis = DIS;
        dos = DOS;
        initComponents();

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
                getData();
                
                try { TimeUnit.SECONDS.sleep(1); }
                catch (Exception e) {}
            }
            catch(IOException e){}
        
        }
    
    }

    //01158463558 //0758821881
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
        jList1 = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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

        jScrollPane2.setViewportView(jList1);

        jLabel7.setText("Available Stations");

        jButton1.setText("Log Out");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Registered Stations");

        jScrollPane3.setViewportView(jList2);

        jButton2.setText("Register");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton3.setText("Remove");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel1)
                        .addGap(37, 37, 37)))
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
                                .addComponent(HumidityOutputBox, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addComponent(HumidityOutputBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3))
                            .addComponent(jScrollPane3))))
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
        selected = WSList.getSelectedValue();
        
        try{ getData(); }
        catch (Exception e){ }
        
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

    private void getData() throws IOException {
        dos.writeUTF("Get Data");
        dos.writeUTF(selected);

        boolean trueeeee = dis.readBoolean();

        if(trueeeee){
            dis.readUTF();
            FieldOutputBox.setText(dis.readUTF());
            CropOutputBox.setText(dis.readUTF());
            TemperatureOutputBox.setText(dis.readUTF());                   
            HumidityOutputBox.setText(dis.readUTF());
        }
        else{
            FieldOutputBox.setText("");
            CropOutputBox.setText("");
            TemperatureOutputBox.setText("");                   
            HumidityOutputBox.setText("");
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CropOutputBox;
    private javax.swing.JTextField FieldOutputBox;
    private javax.swing.JTextField HumidityOutputBox;
    private javax.swing.JTextField TemperatureOutputBox;
    private javax.swing.JList<String> WSList;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
