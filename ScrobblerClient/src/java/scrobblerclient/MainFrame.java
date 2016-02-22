/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrobblerclient;

import data.ArtistInfoData;
import data.ScrobblerData;
import data.UserInfoData;
import ejb.ScrobblerSessionRemote;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingWorker;




public class MainFrame extends javax.swing.JFrame {

    @EJB
    private static ScrobblerSessionRemote scrobblerSession;
    
    private String userName;
    private int trackLimit;
    private ArrayList<ArtistInfoData> artistInfoData;
    private ArrayList<ScrobblerData> scrobblerData;
    private ArrayList<UserInfoData> userInfoData;

    private ProgressInfinityDownload taskInfinityDownload;
    private ProgressInfinityUpload taskInfinityUpload;
    private ProgressInfinityDatabase taskInfinityDatabase;
    

    public MainFrame() {
        initComponents();
        
    }
    
    class ProgressInfinityDatabase extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            jProgressBar2.setIndeterminate(true);
            downloadArtistInfo();
            downloadUserInfo();
            
            return null;
        }
        
        @Override
        public void done(){
            jProgressBar2.setIndeterminate(false);
        }
    }
    
    class ProgressInfinityDownload extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            jProgressBar1.setIndeterminate(true);
            downloadScrobblerData();
            
            return null;
        }
        
        @Override
        public void done(){
            jProgressBar1.setIndeterminate(false);
        }
    }
    
    class ProgressInfinityUpload extends SwingWorker<Void, Void>{

        @Override
        public Void doInBackground() {
            jProgressBar1.setIndeterminate(true);
            uploadScrobblerData();
            
            return null;
        }
        
        @Override
        public void done(){
            jProgressBar1.setIndeterminate(false);
        }
    }
    
    private void getUserName(){
        this.userName = jTextFieldUsername.getText();
        
        if(this.userName == null || this.userName.isEmpty() || this.userName == "")
            JOptionPane.showMessageDialog(null, "Username is empty.");
    }
    
    private void getTrackLimit(){
        this.trackLimit = Integer.valueOf(jComboBoxTrackLimit.getSelectedItem().toString());
        
        if(this.trackLimit < 10 || this.trackLimit > 3000)
            JOptionPane.showMessageDialog(null, "Download size should be in range of 10-3000 records.");
    }
    
    private DefaultTableModel getTableModel(JTable jTable){
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0);
        
        return model;
    }

    public void downloadScrobblerData(){
        getUserName();
        getTrackLimit();
        this.scrobblerData = new ArrayList<>();
        this.scrobblerData = scrobblerSession.downloadUserData(this.userName, this.trackLimit);
        
        if(scrobblerData.size() > 0){
            DefaultTableModel model = getTableModel(jTableDownloadedApi);
        
            this.scrobblerData.stream().forEach((item) -> {
                model.insertRow(model.getRowCount(), new Object[] {
                    item.getArtistName(),
                    item.getTrackName(),
                    item.getDuration(),
                    item.getPlayCount()
                });
            });
        }
    }
    
    public void downloadUserInfo(){
        this.userInfoData = new ArrayList<>();
        this.userInfoData = scrobblerSession.loadUserInfo();
        
        if(userInfoData.size() > 0){
            DefaultTableModel model = getTableModel(jTableUsers);
            
            this.userInfoData.stream().forEach((item) -> {
                model.insertRow(model.getRowCount(), new Object[]{
                    item.getUserName(),
                    item.getTracksAvailable(),
                    item.getTotalTracksListened(),
                    item.getAvgPlayCount(),
                    item.getAvgTrackDuration(),
                    item.getTopArtist(),
                    item.getTopArtistListened()
                });
            });
        }
    }
    
    public void downloadArtistInfo(){
        this.artistInfoData = scrobblerSession.loadArtistInfo();
        
        if(artistInfoData.size() > 0){
            DefaultTableModel model = getTableModel(jTableArtists);
            
            this.artistInfoData.stream().forEach((item) -> {
                model.insertRow(model.getRowCount(), new Object[]{
                    item.getArtist(),
                    item.getTrackCount(),
                    item.getTotalDuration(),
                    item.getAvgDuration(),
                    item.getTimesListened(),
                    item.getTopTrack(),
                    item.getTopTrackListened()
                });
            });
        }
    }
    
    public void uploadScrobblerData(){
        if(scrobblerData.size() > 0){
            scrobblerSession.saveRecordToDatabase(this.scrobblerData);
            JOptionPane.showMessageDialog(null, "Uploaded " + scrobblerData.size() + " rows.");
        } else{
            JOptionPane.showMessageDialog(null, "Firstly, you have to download some data.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelDownload = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jTextFieldUsername = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxTrackLimit = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDownloadedApi = new javax.swing.JTable();
        jPanelDatabase = new javax.swing.JPanel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableUsers = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableArtists = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Last.fm audio scrobbler");
        setSize(new java.awt.Dimension(320, 240));

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jTextFieldUsername.setToolTipText("User name");

        jLabel1.setText("Username");

        jComboBoxTrackLimit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "25", "50", "100", "150", "200", "300" }));
        jComboBoxTrackLimit.setToolTipText("Number of records to download");

        jLabel2.setText("Size");

        jButton1.setText("Download");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Upload");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTableDownloadedApi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Artist", "Track", "Duration", "Play count"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableDownloadedApi);

        javax.swing.GroupLayout jPanelDownloadLayout = new javax.swing.GroupLayout(jPanelDownload);
        jPanelDownload.setLayout(jPanelDownloadLayout);
        jPanelDownloadLayout.setHorizontalGroup(
            jPanelDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDownloadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDownloadLayout.createSequentialGroup()
                        .addGroup(jPanelDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxTrackLimit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelDownloadLayout.setVerticalGroup(
            jPanelDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDownloadLayout.createSequentialGroup()
                .addGroup(jPanelDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jComboBoxTrackLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        jLabel1.getAccessibleContext().setAccessibleName("jLabelUsername");

        jTabbedPane1.addTab("Download", jPanelDownload);

        jTableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Tracks available", "Tracks listened", "Average play count", "Average track duration", "Top artist", "Top artist listened"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableUsers);

        jTabbedPane5.addTab("Users", jScrollPane2);

        jTableArtists.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Artist", "Tracks count", "Total duration", "Average duration", "Times listened", "Top track", "Top track listened"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableArtists);

        jTabbedPane5.addTab("Artists", jScrollPane3);

        jButton3.setText("Reload");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDatabaseLayout = new javax.swing.GroupLayout(jPanelDatabase);
        jPanelDatabase.setLayout(jPanelDatabaseLayout);
        jPanelDatabaseLayout.setHorizontalGroup(
            jPanelDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDatabaseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
                    .addComponent(jTabbedPane5)
                    .addGroup(jPanelDatabaseLayout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelDatabaseLayout.setVerticalGroup(
            jPanelDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDatabaseLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        jTabbedPane1.addTab("Database", jPanelDatabase);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        taskInfinityDownload = new ProgressInfinityDownload();
        taskInfinityDownload.execute();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        taskInfinityUpload = new ProgressInfinityUpload();
        taskInfinityUpload.execute();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        taskInfinityDatabase = new ProgressInfinityDatabase();
        taskInfinityDatabase.execute();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        taskInfinityDatabase = new ProgressInfinityDatabase();
        taskInfinityDatabase.execute();        
                
    }//GEN-LAST:event_jButton3ActionPerformed

    
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxTrackLimit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelDatabase;
    private javax.swing.JPanel jPanelDownload;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTableArtists;
    private javax.swing.JTable jTableDownloadedApi;
    private javax.swing.JTable jTableUsers;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables

    
}
