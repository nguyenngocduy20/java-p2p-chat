/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package build_in_class;
import java.util.*;
import java.io.*;
import java.net.*;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class Send implements Runnable
{
    public String threadName;
    public String flag; // hello: gui hello den peer
                        // ack: xac nhan da nhan duoc
                        // mess: tin nhan
                        // file: command gui file
                        // star: signal bat dau nhan file
                        // pubk: public key
                        // eof: het file
                        // send: gui file
    public String content;  // hello: <nickname> <ip> <port_send>
                            // ack: <ip> <port_send>
                            // mess: <content>
                            // file: <file_name>
                            // star: <file_name>
                            // eof: <file_name>
                            // pubk: <pem>
                            // send: <path_to_file>
    public InetAddress IpDest;
    public int PortDes;
    public int yPort; // port for sending
    public InetAddress yIP; // your IP
    
    public Send(String name)
    {
        System.out.println("\n\t---- Init Thread Send ----\n");
        this.threadName = name;
        System.out.println("\tThread name: " + this.threadName);
    }
    
    public Send(String name, String flag)
    {
        System.out.println("\t---- Init Thread Send ----");
        this.threadName = name;
        this.flag = flag;
        System.out.println("\tThread name: " + this.threadName);
    }
    
    public void run()
    {
        try {
            System.out.println("\tYour IP: " + this.yIP.toString() + "\tYour port: " + this.yPort);
            System.out.println("\tInit DatagramSocket");
            DatagramSocket dsoc = new DatagramSocket(yPort, yIP);
            System.out.println("\tDatagram Init Successful");
            
            // hello <ip> <port_send>
            if(flag.equals("hello"))
            {
                System.out.println("\tPacket HELLO");
                try 
                {
                    this.content = " " + this.content + " " + yIP.toString() + " " + yPort;
                    byte[] sendData = (this.flag + this.content).getBytes();
                    System.out.println("\tPacket: " + new String(sendData));
                    System.out.println("\tDest IP: " + IpDest.toString() + "\n\tDest Port: " + PortDes);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, (this.flag + this.content).length(), IpDest, PortDes);
                    dsoc.send(sendPacket);
                    System.out.println("\tPacket sent!");
                    dsoc.close();
                    System.out.println("\tDatagramSocket Closed");
                } catch (IOException ex)
                {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            // ack <ip> <port_send>
            if(flag.equals("ack"))
            {
                System.out.println("\tPacket ACK");
                try 
                {
                    this.content = " " + yIP.toString() + " " + yPort;
                    byte[] sendData = (this.flag + this.content).getBytes();
                    System.out.println("\tPacket: " + new String(sendData));
                    System.out.println("\tDest IP: " + IpDest.toString() + "\n\tDest Port: " + PortDes);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, (this.flag + this.content).length(), IpDest, PortDes);
                    dsoc.send(sendPacket);
                    System.out.println("\tPacket sent!");
                    dsoc.close();
                    System.out.println("\tDatagramSocket Closed");
                } catch (IOException ex)
                {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // pubk <pem>
            if(flag.equals("pubk"))
            {
                System.out.println("\tPacket PUBK");
                try 
                {
                    byte[] sendData = (flag + " " + content).getBytes();
                    System.out.println("\tPacket: " + new String(sendData));
                    System.out.println("\tDest IP: " + IpDest.toString() + "\n\tDest Port: " + PortDes);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, (this.flag + " " + this.content).length(), IpDest, PortDes);
                    dsoc.send(sendPacket);
                    System.out.println("\tPacket sent!");
                    dsoc.close();
                    System.out.println("\tDatagramSocket Closed");
                } catch (IOException ex)
                {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // mess <content>
            if(flag.equals("mess"))
            {
                System.out.println("\tPacket MESS");
                try 
                {
                    // encrypt this.content
                    
                    File currentDirectory = new File(new File(".").getAbsolutePath());
                    String p = currentDirectory.getCanonicalPath();
                    PublicKey pubk = MyCrypto.importPublicKey(p + "/keys/publicKey");
                    this.content = MyCrypto.encrypRSAMessage(pubk, this.content);
                    
                    // send packet
                    byte[] sendData = (flag + " " + content).getBytes();
                    System.out.println("\tPacket: " + new String(sendData));
                    System.out.println("\tDest IP: " + IpDest.toString() + "\n\tDest Port: " + PortDes);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, (this.flag + " " + this.content).length(), IpDest, PortDes);
                    dsoc.send(sendPacket);
                    System.out.println("\tPacket sent!");
                    dsoc.close();
                    System.out.println("\tDatagramSocket Closed");
                } catch (IOException ex)
                {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // file <file_name>
            if(flag.equals("file"))
            {
                System.out.println("\tPacket FILE");
                try 
                {
                    byte[] sendData = (flag + " " + content).getBytes();
                    System.out.println("\tPacket: " + new String(sendData));
                    System.out.println("\tDest IP: " + IpDest.toString() + "\n\tDest Port: " + PortDes);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, (this.flag + " " + this.content).length(), IpDest, PortDes);
                    dsoc.send(sendPacket);
                    System.out.println("\tPacket sent!");
                    dsoc.close();
                    System.out.println("\tDatagramSocket Closed");
                } catch (IOException ex)
                {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // star: <file_name>
            if(flag.equals("star"))
            {
                System.out.println("\tPacket STAR");
                try 
                {
                    byte[] sendData = (flag + " " + content).getBytes();
                    System.out.println("\tPacket: " + new String(sendData));
                    System.out.println("\tDest IP: " + IpDest.toString() + "\n\tDest Port: " + PortDes);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, (flag + " " + content).length(), IpDest, PortDes);
                    dsoc.send(sendPacket);
                    System.out.println("\tPacket sent!");
                    dsoc.close();
                    System.out.println("\tDatagramSocket Closed");
                    
                    /*
                    Receive r = new Receive("Receive FILE");
                    r.content = content; // content = file_name
                    r.flag = "";
                    r.run();
                    */
                } catch (IOException ex)
                {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // send <path_to_file>
            if(flag.equals("send"))
            {
                System.out.println("\tPacket SEND");
                File file = new File(content); // content = file_path
                byte[] result = new byte[16384];
                InputStream input = null;
                System.out.println("\tSend file: " + this.content);
                
                try 
                {
                    long totalBytesRead = 0;
                    input = new BufferedInputStream(new FileInputStream(file));
                    long file_length = file.length();
                    while (totalBytesRead < file_length)
                    {
                        int bytesRead = input.read(result, 0, 16384);
                        if(bytesRead > 0)
                        {
                            totalBytesRead = totalBytesRead + bytesRead;
                            DatagramPacket sendPacket = new DatagramPacket(result, result.length, IpDest, PortDes);
                            dsoc.send(sendPacket);
                            System.out.println("\tSend file, part from " + (totalBytesRead - bytesRead) + " to " + totalBytesRead);
                        }
                    }
                    
                    System.out.println("\tAll file sent!");
                    dsoc.close();
                    System.out.println("\tDatagramSocket Closed");
                    
                    input.close();
                    Send s = new Send("send EOF", "oef");
                    s.yIP = this.yIP;
                    s.yPort = this.yPort;
                    s.IpDest = this.IpDest;
                    s.PortDes = this.PortDes;
                    s.content = content.substring(content.lastIndexOf('/'), content.length());
                    s.run();
                } catch (FileNotFoundException ex)
                {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            // eof <file_name>
            if(flag.equals("eof"))
            {
                System.out.println("\tPacket EOF");
                try 
                {
                    byte[] sendData = (flag + " " + content).getBytes();
                    System.out.println("\tPacket: " + new String(sendData));
                    System.out.println("\tDest IP: " + IpDest.toString() + "\nDest Port: " + PortDes);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IpDest, PortDes);
                    dsoc.send(sendPacket);
                    System.out.println("\tPacket sent!");
                    dsoc.close();
                    System.out.println("\tDatagramSocket Closed");
                } catch (IOException ex)
                {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("\n\t---- End Thread Send ----\n");
            
        } catch (SocketException ex) {
            Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
