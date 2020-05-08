package wspclient1;

import java.net.*; 
import java.io.*; 
import java.util.Scanner;
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
        WSAddBtn = new javax.swing.JButton();
        WSAddTextBox = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User Client");
        setResizable(false);

        WSList.setFocusable(false);
        WSList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                WSListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(WSList);

        jLabel2.setText("All Weather Stations");
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

        WSAddBtn.setText("Add Weather Station");
        WSAddBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        WSAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WSAddBtnActionPerformed(evt);
            }
        });

        WSAddTextBox.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        WSAddTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WSAddTextBoxActionPerformed(evt);
            }
        });
        WSAddTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                WSAddTextBoxKeyTyped(evt);
            }
        });

        jScrollPane2.setViewportView(jList1);

        jLabel7.setText("Online Weather Stations");

        jButton1.setText("Log Out");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(WSAddBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                    .addComponent(WSAddTextBox, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addComponent(FieldOutputBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addComponent(CropOutputBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addComponent(TemperatureOutputBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addComponent(HumidityOutputBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(38, 38, 38))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(TemperatureOutputBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WSAddTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HumidityOutputBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WSAddBtn))
                .addContainerGap(16, Short.MAX_VALUE))
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

    private void WSAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WSAddBtnActionPerformed

        try {
            
            String station = WSAddTextBox.getText();
            if (station.isBlank()) return;
            
            dos.writeUTF("Add Station");
            
            dos.writeUTF(id);
            dos.writeUTF(station);

            if (dis.readBoolean()){
                DefaultListModel<String> newList = new DefaultListModel<String>();
                ListModel<String> existing = WSList.getModel();
                for(int i = 0; i < existing.getSize(); i++){
                    newList.addElement(existing.getElementAt(i));
                }
                newList.addElement(station);
                WSList.setModel(newList);
            }
            else{
                
                JOptionPane optionPane = new JOptionPane();
                optionPane.setMessage("Weather Station is Already Added");
                JDialog dialog = optionPane.createDialog("Inclusion Error");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
                
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_WSAddBtnActionPerformed

    private void WSListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_WSListValueChanged
        String stationID = WSList.getSelectedValue();
        
        try {
            dos.writeUTF("Get Data");
            dos.writeUTF(stationID);
            
            if(!dis.readBoolean())
            {
                
            }           
            else while(dis.available() > 0)
            {
                FieldOutputBox.setText(dis.readUTF());
                CropOutputBox.setText(dis.readUTF());
                TemperatureOutputBox.setText(dis.readUTF());
                HumidityOutputBox.setText(dis.readUTF());
            }
            
        }
        catch(IOException e){}
    }//GEN-LAST:event_WSListValueChanged

    private void WSAddTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WSAddTextBoxActionPerformed
        
    }//GEN-LAST:event_WSAddTextBoxActionPerformed

    private void WSAddTextBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WSAddTextBoxKeyTyped
        
        // Accept only number inputs for ID
        
        int result = (int) evt.getKeyChar();

        if( result < 48 || result > 57 ){
            evt.setKeyChar(((char)0));
        }
        
    }//GEN-LAST:event_WSAddTextBoxKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        log.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CropOutputBox;
    private javax.swing.JTextField FieldOutputBox;
    private javax.swing.JTextField HumidityOutputBox;
    private javax.swing.JTextField TemperatureOutputBox;
    private javax.swing.JButton WSAddBtn;
    private javax.swing.JTextField WSAddTextBox;
    private javax.swing.JList<String> WSList;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
