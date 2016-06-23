import build_in_class.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyPair;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nguye
 */
public class configuration extends javax.swing.JFrame {

    /**
     * Creates new form configuration
     */
    public configuration() {
        initComponents();
        own_thrd = Thread.currentThread();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_nickname = new javax.swing.JLabel();
        lbl_ip = new javax.swing.JLabel();
        lbl_port = new javax.swing.JLabel();
        txt_nickname = new javax.swing.JTextField();
        txt_ip = new javax.swing.JTextField();
        txt_port = new javax.swing.JTextField();
        lbl_hint = new javax.swing.JLabel();
        btn_ok = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        lbl_avatar = new javax.swing.JLabel();
        box_avatar = new javax.swing.JLabel();
        btn_browse = new javax.swing.JButton();
        lbl_yIP = new javax.swing.JLabel();
        lbl_yPort = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configuration");
        setName("frm_configuration"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lbl_nickname.setText("Your nickname:");

        lbl_ip.setText("Your friend's IP:");

        lbl_port.setText("Your friend's Port:");

        txt_nickname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nicknameKeyPressed(evt);
            }
        });

        lbl_hint.setText("You have to enter your friend's IP and Port to begin:");

        btn_ok.setText("OK");
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        lbl_avatar.setText("Avatar:");

        box_avatar.setText("avatar.jpg");

        btn_browse.setText("Browse");
        btn_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_browseActionPerformed(evt);
            }
        });

        lbl_yIP.setText("IP:");

        lbl_yPort.setText("Port:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_cancel)
                .addGap(9, 9, 9))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_hint)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_port)
                                    .addComponent(lbl_ip))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_port, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                    .addComponent(txt_ip)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_nickname)
                                    .addComponent(lbl_avatar))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(65, 65, 65)
                                                .addComponent(btn_browse))
                                            .addComponent(box_avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                        .addComponent(txt_nickname, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_yIP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_yPort)
                        .addGap(111, 111, 111))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_yIP)
                    .addComponent(lbl_yPort))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nickname)
                    .addComponent(txt_nickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_avatar)
                    .addComponent(box_avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_browse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_hint)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_ip))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_port))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ok)
                    .addComponent(btn_cancel))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okActionPerformed
        // TODO add your handling code here:
        
        JOptionPane p1 = new JOptionPane();
        this.txt_nickname.setText(this.txt_nickname.getText().replaceAll(" ", ""));
        //p1.showOptionDialog(null, "Waiting for your friend to respond!!!", "Information", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
        boolean flag = false;
        // check that connection was opened? if connection were opened, flag = true, else flag = false
        Send s = new Send("send HELLO", "hello");
        s.yIP = this.yIP;
        s.yPort = this.yPort;
        s.content = this.txt_nickname.getText();
        this.yNickname = s.content;
        try
        {
            s.IpDest = InetAddress.getByName(this.txt_ip.getText());
            s.PortDes = Integer.parseInt(this.txt_port.getText()) + 1;
        } catch (UnknownHostException ex)
        {
            Logger.getLogger(configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.run();
        
        System.out.print("Waiting for acknowledge");
        /*
        while(!recv.acked)
        {
            System.out.print("");
        }
        */
        System.out.println("\nAcked from friend");
        this.fNickname = recv.fNickname;
        recv.yNickname = this.yNickname;
        try
        {
            this.fIP = InetAddress.getByName(this.txt_ip.getText());
            this.fPort = Integer.parseInt(this.txt_port.getText());
        } catch (UnknownHostException ex) {
            Logger.getLogger(configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        init_HTML();
        
        // send public key
        // Create KeyPair
        KeyPair kp = MyCrypto.createRSAKeyPair();
        try {
            File currentDirectory = new File(new File(".").getAbsolutePath());
            String p = currentDirectory.getCanonicalPath();
            MyCrypto.exportRSAKeyPair(kp, p + "/keys/RSAkey");
            
            // Send key to friend
            s = new Send("send PUBK", "pubk");
            s.yIP = this.yIP;
            s.yPort = this.yPort;
            s.content = MyCrypto.keyToString(kp.getPublic());
            s.IpDest = InetAddress.getByName(this.txt_ip.getText());
            s.PortDes = Integer.parseInt(this.txt_port.getText()) + 1;
            s.run();
        } catch (IOException ex) {
            System.out.println("File not found!");
        }
        
        // send avatar
        try {
            if(avt != null)
            {
                Thread.sleep(100);
                s = new Send("send FILE", "file");
                s.yIP = this.yIP;
                s.yPort = this.yPort;
                s.IpDest = InetAddress.getByName(this.txt_ip.getText());
                s.PortDes = Integer.parseInt(this.txt_port.getText()) + 1;
                s.content = "avt_" + this.yNickname + avt.getCanonicalPath().substring(avt.getCanonicalPath().lastIndexOf("."), avt.getCanonicalPath().length());
                recv.filePath = avt.getCanonicalPath();
                s.run();
            }
        } catch(IOException ix) {
            
        } catch (InterruptedException ex) {
            Logger.getLogger(configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dispose();
    }//GEN-LAST:event_btn_okActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_browseActionPerformed
        // TODO add your handling code here:
        File img = configuration.FileChooser();
        if(img != null)
        {
            avt = img;
            System.out.println(img.getAbsolutePath().toString());
            int w = box_avatar.getWidth();
            int h = box_avatar.getHeight();
            int x = box_avatar.getX();
            int y = box_avatar.getY();
            System.out.println("(" + x + ", " + y + ")" + "\nW: " + w + " H: " + h);
            Image srcImg = new ImageIcon(img.getAbsolutePath()).getImage();
            if(panel != null)
            {
                panel.setVisible(false);
                panel = null;
            }
            //setVisible(false);
            panel = new ImageImplement(srcImg);
            add(panel);
            setVisible(true);
            setSize(400,400);
            box_avatar.setText("");
            //setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }//GEN-LAST:event_btn_browseActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        if(this.yIP != null)
        {
            this.lbl_yIP.setText("IP: " + this.yIP.toString());
            this.lbl_yPort.setText("Port: " + this.yPort);
        }
        
    }//GEN-LAST:event_formWindowOpened

    private void txt_nicknameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nicknameKeyPressed
        // TODO add your handling code here:
        String s = this.txt_nickname.getText();
        s = s.replaceAll(" ", "");
        this.txt_nickname.setText(s);
    }//GEN-LAST:event_txt_nicknameKeyPressed

    public static File FileChooser()
    {
        File chosenFile = null;
        boolean flag = false;
        JFileChooser chooser = new JFileChooser();
        
        while(flag == false)
        {

            int choice = chooser.showOpenDialog(null);

            if (choice != JFileChooser.APPROVE_OPTION)
                return null;

            chosenFile = chooser.getSelectedFile();

            String filePath = chosenFile.getAbsolutePath();
            if(!(filePath.endsWith(".jpg") || filePath.endsWith(".png") || filePath.endsWith("bmp")))
            {
                JOptionPane.showMessageDialog(null, "File chosen must be image file.");
            }
            else
                flag = true;
        }
        return chosenFile;
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
    
    /**
     * @param args the command line arguments
     */
    public static void configuration() {
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
            java.util.logging.Logger.getLogger(configuration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(configuration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(configuration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(configuration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new configuration().setVisible(true);
            }
        });
    }
    
    private ImageImplement panel;
    public String yNickname;
    public String fNickname;
    public InetAddress yIP;
    public int yPort;
    public InetAddress fIP;
    public int fPort;
    public File avt = null;
    public boolean isClosed = false;
    public Receive recv;
    public Thread own_thrd;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel box_avatar;
    private javax.swing.JButton btn_browse;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_ok;
    private javax.swing.JLabel lbl_avatar;
    private javax.swing.JLabel lbl_hint;
    private javax.swing.JLabel lbl_ip;
    private javax.swing.JLabel lbl_nickname;
    private javax.swing.JLabel lbl_port;
    private javax.swing.JLabel lbl_yIP;
    private javax.swing.JLabel lbl_yPort;
    public javax.swing.JTextField txt_ip;
    public javax.swing.JTextField txt_nickname;
    public javax.swing.JTextField txt_port;
    // End of variables declaration//GEN-END:variables
}

class ImageImplement extends JPanel
{
    private Image img; 
    public ImageImplement(Image img)
    { 
        this.img = img; Dimension size = new Dimension(img.getWidth(null), img.getHeight(null)); 
        setPreferredSize(size); 
        setMinimumSize(size); 
        setMaximumSize(size); 
        setSize(size); 
        setLayout(null); 
    } 
    public void paintComponent(Graphics g) 
    { 
        g.drawImage(img, 122, 69, 132, 78, null); 
    } 
}


