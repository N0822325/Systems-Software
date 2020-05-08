
package wspserver;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;


public class ServerGUI extends javax.swing.JFrame {

    ArrayList<WSConnectedInfo> wsconnectedinfo;
    ArrayList<UConnectedInfo> uconnectedinfo;
    String [] connectedWSArray;
    String [] connectedUArray;
    int [] a = new int[20];
    
    public ServerGUI() {
        initComponents();
        
        wsconnectedinfo = new ArrayList<WSConnectedInfo>();
        uconnectedinfo = new ArrayList<UConnectedInfo>();
        populateArrayList();
        
        connectedWSArray = new String[wsconnectedinfo.size()];
        connectedUArray = new String[uconnectedinfo.size()];
        
        for (int i = 0 ; i < wsconnectedinfo.size() ; i++)
        {
            connectedWSArray[i] = wsconnectedinfo.get(i).getWsid() + ": " + wsconnectedinfo.get(i).getWssocket();
        }

        DefaultListModel<String> newList1 = new DefaultListModel<String>();

        for(String s : connectedWSArray)
            {
                newList1.addElement(s);
            }
        
        jList2.setModel(newList1);
        
        
        for (int i = 0 ; i < uconnectedinfo.size() ; i++)
        {
            connectedUArray[i] = uconnectedinfo.get(i).getUid();
        }

        DefaultListModel<String> newList2 = new DefaultListModel<String>();

        for(String s : connectedUArray)
            {
                newList2.addElement(s);
            }
        
        jList3.setModel(newList2);
        
        showlist();
    }
    
    public void populateArrayList()
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        list1 = new java.awt.List();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");
        setResizable(false);

        jLabel2.setText("Connected Clients");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel3.setText("Connected Weather Stations");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList2);

        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList3);

        list1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabel3)
                        .addContainerGap(191, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jScrollPane3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(130, 130, 130)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void list1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_list1ActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerGUI().setVisible(true);
            }
        });
    }
    
    public void showlist()
    {
        list1.removeAll();
        for(int k = 0 ; k < a.length; k++)
        {
            int id = a[k];
            list1.add(Integer.toString(id));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private java.awt.List list1;
    // End of variables declaration//GEN-END:variables
}
