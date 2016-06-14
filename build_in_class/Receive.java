/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package build_in_class;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class Receive implements Runnable
{
    public String threadName;
    public String flag;
    public int yPort; // port nhan mac dinh
    public InetAddress yIP; // ip
    public InetAddress sIP;
    public int sPort; // port gui
    public String content; // chua noi dung goi tin
    public DatagramPacket packet;
    public boolean acked;
    public boolean isRunnable = true;
    
    public Receive()
    {
            System.out.println("Init RECEIVE");
            System.out.println("Your IP: " + yIP + "\tYour port: " + yPort);
    }

    public Receive(String name)
    {
            System.out.println("Init RECEIVE");
            this.threadName = name;
            System.out.println("Thread name: " + this.threadName +"\tYour IP: " + yIP + "\tYour port: " + yPort);
    }
    
    public void run()
    {
        while(true)
        {
            try 
            {
                System.out.println("Init DatagramSocket");
                //System.out.println("Thread name: " + this.threadName +"\tYour IP: " + IPAddress.toString() + "\tYour port: " + yPort);
                DatagramSocket ds = new DatagramSocket(yPort, yIP);
                System.out.println("Init RECEIVE datagramSocket successful");
                
                byte[] recvData = new byte[16384];
                
                // nhan goi tin
                packet = new DatagramPacket(recvData, 16384);
                ds.receive(packet);
                this.sIP = packet.getAddress();
                this.sPort = packet.getPort();
                System.out.println("Received packet from " + sIP + "\tPort: " + sPort);
                System.out.println("Content [" + packet.getLength() + "]: " + new String(packet.getData()).substring(0, packet.getLength()));
                parsePacket(packet);
                
                if(this.flag.equals("hello"))
                {
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
                
                if(this.flag.equals("star"))
                {
                    Send s = new Send("send SEND", "send");
                    s.IpDest = packet.getAddress();
                    s.PortDes = packet.getPort() + 1;
                    s.content = this.content; // path to file
                    s.run();
                }
                
                if(this.flag.equals(""))
                {
                    ds.receive(packet);
                    File currentDirectory = new File(new File(".").getAbsolutePath());
                    String p = currentDirectory.getCanonicalPath();
                    p = p + "\\ReceivedFiles";
                    File f = new File(p);
                    if(!f.exists())
                        f.mkdir();
                    File file = new File(p + this.content);
                    OutputStream output = null;
                    long totalBytesRecv = 0;
                    output = new BufferedOutputStream(new FileOutputStream(file));
                    
                    while(!parsePacket(packet).equals("eof"))
                    {
                        output.write(packet.getData());
                        totalBytesRecv = totalBytesRecv + packet.getLength();
                        ds.receive(packet);
                    }
                    
                    System.out.println("Received file " + this.content + "(" + totalBytesRecv + ")");
                }
                
                if(this.flag.equals("mess"))
                {
                    parsePacket(packet);
                }
                
                if(this.flag.equals("file"))
                {
                    parsePacket(packet);
                }
                
                if(this.flag.equals("pubk"))
                {
                    // not yet implement
                }
                
                ds.close();
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
        String s = new String(packet.getData()).substring(0, packet.getLength());
        this.flag = s.substring(0, s.indexOf(" "));
        if(this.flag.equals("hello") || this.flag.equals("ack"))
        {
            this.sIP = packet.getAddress();
            this.sPort = packet.getPort();
            System.out.println("Flag [" + this.flag.length() + "]: <" + this.flag.toUpperCase() + ">");
        }
        
        if(this.flag.equals("mess") || this.flag.equals("file")  || this.flag.equals("eof"))
        {
            this.content = s.substring(this.flag.length() + 2, s.length());
            System.out.println("Flag [" + this.flag.length() + "]: <" + this.flag.toUpperCase() + ">");
        }
        
        if(this.flag.length() > 4 && !this.flag.equals("ack") && !this.flag.equals("eof") && !this.flag.equals("hello"))
        {
            this.flag = "";
            System.out.println("Flag [" + this.flag.length() + "]: <" + this.flag.toUpperCase() + ">");
        }
        
        return this.flag;
    }
    
}
