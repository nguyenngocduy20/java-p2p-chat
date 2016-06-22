
import build_in_class.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nguye
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        try {
            initComponents();
            own_thrd = Thread.currentThread();
            int t = 1 + (int)(Math.random()*255);
            yIP = InetAddress.getByName("127.16.0." + t);
            yPort = 12567 + (int)(Math.random()*45535);
            this.lbl_yIP.setText(this.lbl_yIP.getText() + " " + yIP.toString());
            this.lbl_yPort.setText(this.lbl_yPort.getText() + " " + yPort);
            doc = new StringBuilder();
            yNickname = "anonymous";
            fNickname = "anonymous_friend";
            recv.yIP = this.yIP;
            recv.yPort = this.yPort + 1;
            recv.t = this.txt_chatline;
            recv.doc = this.doc;
            recv_thrd.start();
        } catch (UnknownHostException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txt_send = new javax.swing.JTextArea();
        btn_attach = new javax.swing.JButton();
        btn_send = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_chatline = new javax.swing.JEditorPane();
        lbl_yNickname = new javax.swing.JLabel();
        lbl_yIP = new javax.swing.JLabel();
        lbl_yPort = new javax.swing.JLabel();
        lbl_fNickname = new javax.swing.JLabel();
        lbl_fIP = new javax.swing.JLabel();
        lbl_fPort = new javax.swing.JLabel();
        lbl_fAvt = new javax.swing.JLabel();
        lbl_yAvt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Chat 1312084_1312086_1312110");
        setBackground(new java.awt.Color(255, 255, 255));
        setName("gui"); // NOI18N
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txt_send.setColumns(20);
        txt_send.setRows(5);
        txt_send.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_sendKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txt_send);

        btn_attach.setText("Attachment");

        btn_send.setText("Send");
        btn_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendActionPerformed(evt);
            }
        });

        txt_chatline.setEditable(false);
        jScrollPane2.setViewportView(txt_chatline);
        txt_chatline.setContentType("text/html");
        txt_chatline.getAccessibleContext().setAccessibleDescription("");

        lbl_yNickname.setText("Your Nickname:");

        lbl_yIP.setText("IP:");

        lbl_yPort.setText("Port:");

        lbl_fNickname.setText("Friend's Nickname:");

        lbl_fIP.setText("IP:");

        lbl_fPort.setText("Port:");

        lbl_fAvt.setText("Friend's Avatar");

        lbl_yAvt.setText("Your Avatar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_attach, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(btn_send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_fAvt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_yAvt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_yNickname)
                            .addComponent(lbl_fNickname))
                        .addGap(140, 140, 140)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_yIP)
                            .addComponent(lbl_fIP))
                        .addGap(159, 159, 159)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_yPort)
                            .addComponent(lbl_fPort))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_yNickname)
                    .addComponent(lbl_yIP)
                    .addComponent(lbl_yPort))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_fNickname)
                    .addComponent(lbl_fIP)
                    .addComponent(lbl_fPort))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_fAvt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_yAvt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_attach, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_sendKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sendKeyPressed
        // TODO add your handling code here:
        this.fIP = recv.fIP;
        this.fPort = recv.fPort;
        if(evt.getKeyCode() == KeyEvent.VK_ENTER && !txt_send.getText().equals("\n") && !txt_send.getText().equals("") && txt_send.getText() != null)
        {
            set_nickname();
            String s = this.txt_send.getText();
            s = this.replaceEmotions(s);
            // send message to friend
            Send mess = new Send("Send MESS", "mess");
            mess.yIP = this.yIP;
            mess.yPort = this.yPort;
            mess.IpDest = this.fIP;
            mess.PortDes = this.fPort + 1;
            if(s.charAt(0) == '\n')
                mess.content = s.substring(1, s.length());
            else
                mess.content = s;
            mess.run();
            
            UpdateHTML(s);
            
            File currentDirectory = new File(new File(".").getAbsolutePath());
            String p;
            try
            {
                p = currentDirectory.getCanonicalPath();
                File html = new File(p + "/temp/temp" + this.yNickname + ".html");
                File dummy = new File(p + "/temp/dummy.html");
                Receive.copyFile(html, dummy);
                this.txt_chatline.setPage(dummy.toURI().toURL());
                this.txt_chatline.setPage(html.toURI().toURL());
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.clear_text_pane();
        }
    }//GEN-LAST:event_txt_sendKeyPressed

    private void btn_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendActionPerformed
        // TODO add your handling code here:
        
        this.fIP = recv.fIP;
        this.fPort = recv.fPort;
        if(!txt_send.getText().equals("\n") && !txt_send.getText().equals("") && txt_send.getText() != null)
        {
            set_nickname();
            String s = this.txt_send.getText();
            s = this.replaceEmotions(s);
            // send mesage to friend
            Send mess = new Send("Send MESS", "mess");
            mess.yIP = this.yIP;
            mess.yPort = this.yPort;
            mess.IpDest = this.fIP;
            mess.PortDes = this.fPort + 1;
            if(s.charAt(0) == '\n')
                mess.content = s.substring(1, s.length());
            else
                mess.content = s;
            mess.run();
            
            UpdateHTML(s);
            
            File currentDirectory = new File(new File(".").getAbsolutePath());
            String p;
            try
            {
                p = currentDirectory.getCanonicalPath();
                File html = new File(p + "/temp/temp" + this.yNickname + ".html");
                File dummy = new File(p + "/temp/dummy.html");
                Receive.copyFile(html, dummy);
                this.txt_chatline.setPage(dummy.toURI().toURL());
                this.txt_chatline.setPage(html.toURI().toURL());
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.clear_text_pane();
        }
    }//GEN-LAST:event_btn_sendActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        configuration config = new configuration();
        config.yIP = this.yIP;
        config.yPort = this.yPort;
        config.recv = this.recv;
        config.show(true);
        this.fIP = config.fIP;
        this.fPort = config.fPort;
        set_connection_text();
        //init_HTML();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        recv_thrd.stop();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        set_connection_text();
    }//GEN-LAST:event_formWindowGainedFocus

    public String get_date()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println("Event at: " + dateFormat.format(date)); //06/08/2014 15:59:48
        return dateFormat.format(date);
    }
    
    public void clear_text_pane()
    {
        this.txt_send.setText("");
    }
    
    public void set_nickname()
    {
        this.yNickname = recv.yNickname;
        this.fNickname = recv.fNickname;
    }
    
    public void set_connection_text()
    {
        if(this.fIP != null)
            this.lbl_fIP.setText("IP: " + fIP.toString());
        if(this.fPort == 0)
            this.lbl_fPort.setText("Port: " + fPort);
        if(this.fNickname != null)
            this.lbl_fNickname.setText("Friend's nickname: " + this.fNickname);
        if(this.yIP != null)
            this.lbl_yIP.setText("IP: " + yIP.toString());
        if(this.yPort == 0)
            this.lbl_yPort.setText("Port: " + yPort);
        if(this.yNickname != null)
            this.lbl_yNickname.setText("Your nickname: " + this.yNickname);
    }
    
    private void UpdateHTML(String info)
    {
        try
        {
            File currentDirectory = new File(new File(".").getAbsolutePath());
            String p = currentDirectory.getCanonicalPath();
            File html = new File(p + "/temp/temp" + this.yNickname + ".html");
            System.out.println("\tUpdate File: " + html.getCanonicalPath());
            RandomAccessFile b = new RandomAccessFile(html, "rw");
            b.seek(html.length() - 16);
            String pic = "<img src=\"C:\\Users\\nguye\\Pictures\\11212762_776134922485724_4283480414057853955_n.jpg\" alt=\"avt\" style=\"width:24px;height:24px;\">\n";
            String s = "\t\t\t<span style=\"color:red;font-weight:bold\">&emsp " + this.yNickname.toUpperCase() + " &emsp[" + this.get_date() + "]</span>: ";
            s = pic + s;
            if(info.charAt(0) == '\n')
                s = s + info.substring(1, info.length());
            else
                s = s + info;
            b.write(("\t<br> \n\t\t\t" + s + "\n\t\t</br>\n\t</body>\n</html>").getBytes());
            b.close();
        } catch (IOException ex) {
        }
    }
    
    public void init_HTML()
    {
        try 
        {
            File currentDirectory = new File(new File(".").getAbsolutePath());
            String p = currentDirectory.getCanonicalPath();
            File f = new File(p + "/temp/");
            if(!f.exists())
                f.mkdir();
            FileWriter temp = new FileWriter(p + "/temp/temp" + this.yNickname + ".html");
            BufferedWriter b = new BufferedWriter(temp);
            String s = "<html>\n"
                    + "\t<head>\n"
                    + "\t\t<title> Chat Line </title>\n"
                    + "\t</head>\n"
                    + "\t<body>\n"
                    + "\t</body>\n"
                    + "</html>";
            b.write(s);
            b.close();
            
            // create dummy
            temp = new FileWriter(p + "/temp/dummy.html");
            b = new BufferedWriter(temp);
            b.write("");
            b.close();
        } catch (IOException ex)
        {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    private String replaceEmotions(String s)
    {
        String result = s.replaceAll(":\\)", "&#9786;");
        result = result.replaceAll(":\\(", "&#9785;");
        result = result.replaceAll(":D", "&#128515;");
        result = result.replaceAll("><", "&#128518;");
                
        return result;
    }
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    
    private StringBuilder doc;
    private int yPort;
    private InetAddress yIP;
    private InetAddress fIP;
    private int fPort;
    private String yNickname;
    private String fNickname;
    private String path_yAvt;
    private String path_fAvt;
    static Receive recv = new Receive("RECEIVE");
    static Thread recv_thrd = new Thread(recv);
    private Thread own_thrd;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_attach;
    private javax.swing.JButton btn_send;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_fAvt;
    private javax.swing.JLabel lbl_fIP;
    private javax.swing.JLabel lbl_fNickname;
    private javax.swing.JLabel lbl_fPort;
    private javax.swing.JLabel lbl_yAvt;
    private javax.swing.JLabel lbl_yIP;
    private javax.swing.JLabel lbl_yNickname;
    private javax.swing.JLabel lbl_yPort;
    public javax.swing.JEditorPane txt_chatline;
    private javax.swing.JTextArea txt_send;
    // End of variables declaration//GEN-END:variables
}
