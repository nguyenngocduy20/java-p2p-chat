/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package build_in_class;
import java.util.*;
import java.io.*;
import java.net.*;
import java.security.KeyPair;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;

/**
 *
 * @author nguye
 */
public class Receive implements Runnable
{
    public String threadName;
    public String flag;
    public String fNickname;
    public String yNickname;
    public int yPort; // port nhan mac dinh
    public InetAddress yIP; // ip
    public InetAddress fIP;
    public int fPort;
    public InetAddress sIP;
    public int sPort; // port gui
    public String content; // chua noi dung goi tin
    public DatagramPacket packet;
    public boolean acked;
    public boolean isRunnable = true;
    public String filePath;
    public JEditorPane t;
    public StringBuilder doc;
    
    public Receive()
    {
            System.out.println("Init RECEIVE");
            System.out.println("Your IP: " + yIP + "\tYour port: " + yPort);
            this.yNickname = "anonymous";
            this.fNickname = "anonymous_friend";
    }

    public Receive(String name)
    {
            System.out.println("Init RECEIVE");
            this.threadName = name;
            System.out.println("Thread name: " + this.threadName +"\tYour IP: " + yIP + "\tYour port: " + yPort);
            this.yNickname = "anonymous";
            this.fNickname = "anonymous_friend";
    }
    
    public void run()
    {
        while(true)
        {
            try 
            {
                System.out.println("\nInit DatagramSocket");
                //System.out.println("Thread name: " + this.threadName +"\tYour IP: " + IPAddress.toString() + "\tYour port: " + yPort);
                DatagramSocket ds = new DatagramSocket(yPort, yIP);
                System.out.println("Init RECEIVE datagramSocket successful");
                
                byte[] recvData = new byte[16384];
                
                // nhan goi tin
                packet = new DatagramPacket(recvData, 16384);
                ds.receive(packet);
                this.sIP = this.fIP = packet.getAddress();
                this.sPort = this.fPort = packet.getPort();
                System.out.println("\n===\nReceived packet from " + sIP + "\tPort: " + sPort);
                System.out.println("Content [" + packet.getLength() + "]: " + new String(packet.getData()).substring(0, packet.getLength()));
                parsePacket(packet);
                
                System.out.println("\n--- Action for this type of packet ---\n");
                if(this.flag.equals("hello"))
                {
                    this.fNickname = this.content.substring(0, this.content.indexOf(" "));
                    Send s = new Send("send ACK", "ack");
                    s.yIP = this.yIP;
                    s.yPort = this.yPort - 1;
                    s.IpDest = this.sIP;
                    s.PortDes = this.sPort + 1; // recv_port = send_port + 1;
                    s.run();
                }
                
                if(this.flag.equals("ack"))
                {
                    acked = true;
                }
                                
                if(this.flag.equals("mess"))
                {
                    parsePacket(packet);
                    try
                    {
                        File currentDirectory = new File(new File(".").getAbsolutePath());
                        String p = currentDirectory.getCanonicalPath();
                        
                        KeyPair kp = MyCrypto.importRSAKeyPair(p + "/keys/RSAkey"); // get KeyPair
                        
                        this.content = MyCrypto.decryptRSAMessage(kp.getPrivate(), this.content); //decrypt using private key
                        
                        UpdateHTML(this.content);
                        
                        File f = new File(p + "/temp/temp" + this.yNickname + ".html");
                        File dummy = new File(p + "/temp/dummy.html");
                        Receive.copyFile(f, dummy);
                        t.setPage(dummy.toURI().toURL());
                        t.setPage(f.toURI().toURL());
                    } catch(IOException ex)
                    {
                        
                    }
               }
                
                if(this.flag.equals("file"))
                {
                    parsePacket(packet);
                    // this.content = file_name
                    if(this.content.startsWith("avt_"))
                    {
                        Send s = new Send("send STAR", "star");
                        s.yIP = this.yIP;
                        s.yPort = this.yPort - 1;
                        s.IpDest = packet.getAddress();
                        s.PortDes = packet.getPort() + 1;
                        s.content = this.content;
                        s.run();
                        this.filePath = "/temp/" + this.content;
                    }
                    else
                        this.filePath = "/ReceivedFiles/" + this.content;
                    
                    // receive file
                    File currentDirectory = new File(new File(".").getAbsolutePath());
                    String p = currentDirectory.getCanonicalPath();
                    //p = p + "/ReceivedFiles";
                    File f = new File(p + "/ReceivedFiles");
                    if(!f.exists())
                        f.mkdir();
                    File file = new File(p + this.filePath);
                    System.out.println("Receiving file: " + this.filePath);
                    OutputStream output = null;
                    long totalBytesRecv = 0;
                    output = new BufferedOutputStream(new FileOutputStream(file));
                    
                    ds.receive(packet);
                    while(!parsePacket(packet).equals("eof"))
                    {
                        output.write(packet.getData(), 0, packet.getLength());
                        System.out.print("Received file, part from " + totalBytesRecv);
                        totalBytesRecv = totalBytesRecv + packet.getLength();
                        System.out.println(" to " + totalBytesRecv);
                        ds.receive(packet);
                    }
                    
                    output.close();
                    System.out.println("Received file " + this.content + "(" + totalBytesRecv + ")");
                }
                
                
                if(this.flag.equals("star"))
                {
                    Send s = new Send("send SEND", "send");
                    s.yIP = this.yIP;
                    s.yPort = this.yPort - 1;
                    s.IpDest = packet.getAddress();
                    s.PortDes = packet.getPort() + 1;
                    s.content = this.filePath; // path to file
                    s.run();
                }
                
                /*
                if(this.flag.equals(""))
                {
                    ds.receive(packet);
                    File currentDirectory = new File(new File(".").getAbsolutePath());
                    String p = currentDirectory.getCanonicalPath();
                    p = p + "/ReceivedFiles";
                    File f = new File(p);
                    if(!f.exists())
                        f.mkdir();
                    File file = new File(p + this.filePath);
                    System.out.println("Receiving file: " + this.filePath);
                    OutputStream output = null;
                    long totalBytesRecv = 0;
                    output = new BufferedOutputStream(new FileOutputStream(file));
                    
                    while(!parsePacket(packet).equals("eof"))
                    {
                        output.write(packet.getData());
                        System.out.print("Received file, part from " + totalBytesRecv);
                        totalBytesRecv = totalBytesRecv + packet.getLength();
                        System.out.print(" to " + totalBytesRecv);
                        ds.receive(packet);
                    }
                    
                    output.close();
                    System.out.println("Received file " + this.content + "(" + totalBytesRecv + ")");
                }
                */
                
                if(this.flag.equals("pubk"))
                {
                    PublicKey pubk = MyCrypto.stringToPubKey(this.content);
                    try{
                        File currentDirectory = new File(new File(".").getAbsolutePath());
                        String p = currentDirectory.getCanonicalPath();
                        MyCrypto.exportPublicKey(pubk, p + "/keys/publicKey"); // write friend's public key to file
                    } catch(IOException ex){
                    }
                }
                
                ds.close();
                System.out.println("\n--- End Action ---\n");
            } catch (SocketTimeoutException ex)
            {
                if(!isRunnable)
                    return;
                Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public String parsePacket(DatagramPacket packet)
    {
        if(packet.getLength() == 16384)
        {
            this.flag = "";
            return this.flag;
        }
        
        String s = new String(packet.getData()).substring(0, packet.getLength());
        this.flag = s.substring(0, s.indexOf(" "));
        if(this.flag.equals("hello") || this.flag.equals("ack"))
        {
            this.sIP = packet.getAddress();
            this.sPort = packet.getPort();
            this.content = s.substring(this.flag.length() + 1, s.length());
            //this.content = this.content.substring(0, this.content.indexOf(" "));
            System.out.println("Flag [" + this.flag.length() + "]: <" + this.flag.toUpperCase() + ">");
        }
        
        if(this.flag.equals("mess") || this.flag.equals("file")  || this.flag.equals("eof") || this.flag.equals("pubk"));
        {
            this.content = s.substring(this.flag.length() + 1, s.length());
            System.out.println("Flag [" + this.flag.length() + "]: <" + this.flag.toUpperCase() + ">");
        }
        
        if(this.flag.length() > 4 && !this.flag.equals("ack") && !this.flag.equals("eof") && !this.flag.equals("hello"))
        {
            this.flag = "";
            System.out.println("Flag [" + this.flag.length() + "]: <" + this.flag.toUpperCase() + ">");
        }
        
        return this.flag;
    }
        
    public String get_date()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println("Event at: " + dateFormat.format(date)); //06/08/2014 15:59:48
        return dateFormat.format(date);
    }
        
    private void UpdateHTML(String info)
    {
        try
        {
            File currentDirectory = new File(new File(".").getAbsolutePath());
            String p = currentDirectory.getCanonicalPath();
            File html = new File(p + "/temp/temp" + this.yNickname + ".html");
            System.out.println("Update File: " + html.getCanonicalPath());
            RandomAccessFile b = new RandomAccessFile(html, "rw");
            b.seek(html.length() - 16);
            String pic = "<img src=\"C:\\Users\\nguye\\Pictures\\11212762_776134922485724_4283480414057853955_n.jpg\" alt=\"avt\" style=\"width:24px;height:24px;\">\n";
            String s = "\t\t\t<span style=\"color:red;font-weight:bold\">&emsp " + this.fNickname.toUpperCase() + " &emsp[" + this.get_date() + "]</span>: ";
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
    
    public static void copyFile(File source, File dest) throws FileNotFoundException, IOException
    {
            InputStream is = null;
            OutputStream os = null;
            try
            {
                is = new FileInputStream(source);
                os = new FileOutputStream(dest);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0)
                {
                    os.write(buffer, 0, length);
                }
            }
            finally
            {
                is.close();
                os.close();
            }
    }
}
