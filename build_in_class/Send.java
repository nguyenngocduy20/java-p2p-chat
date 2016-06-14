/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package build_in_class;
import java.util.*;
import java.io.*;
import java.net.*;
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
    public String content;  // hello: <ip> <port_send>
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
        System.out.println("Init Thread Send");
        this.threadName = name;
        System.out.println("Thread name: " + this.threadName);
    }
    
    public Send(String name, String flag)
    {
        System.out.println("Init Thread Send");
        this.threadName = name;
        this.flag = flag;
        System.out.println("Thread name: " + this.threadName);
    }
    
    public void run()
    {
        try {
            System.out.println("Your IP: " + this.yIP.toString() + "\tYour port: " + this.yPort);
            System.out.println("Init DatagramSocket");
            DatagramSocket dsoc = new DatagramSocket(yPort, yIP);
            System.out.println("Datagram Init Successful");
            
            // hello <ip> <port_send>
            if(flag.equals("hello"))
            {
                try 
                {
                    this.content = " " + yIP.toString() + " " + yPort;
                    byte[] sendData = (this.flag + this.content).getBytes();
                    System.out.println("Packet: " + new String(sendData));
                    System.out.println("Dest IP: " + IpDest.toString() + "\nDest Port: " + PortDes);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, (this.flag + this.content).length(), IpDest, PortDes);
                    dsoc.send(sendPacket);
                    System.out.println("Packet sent!");
                    dsoc.close();
                    System.out.println("DatagramSocket Closed");
                } catch (IOException ex)
                {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            // ack <ip> <port_send>
            if(flag.equals("ack"))
            {
                System.out.println("Packet ACK");
                try 
                {
                    this.content = " " + yIP.toString() + " " + yPort;
                    byte[] sendData = (this.flag + this.content).getBytes();
                    System.out.println("Packet: " + new String(sendData));
                    System.out.println("Dest IP: " + IpDest.toString() + "\nDest Port: " + PortDes);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, (this.flag + this.content).length(), IpDest, PortDes);
                    dsoc.send(sendPacket);
                    System.out.println("Packet sent!");
                    dsoc.close();
                    System.out.println("DatagramSocket Closed");
                } catch (IOException ex)
                {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // pubk <pem>
            if(flag.equals("pubk"))
            {
                System.out.println("Packet PUBK");
                
            }
            
            // mess <content>
            if(flag.equals("mess"))
            {
                System.out.println("Packet MESS");
                try 
                {
                    byte[] sendData = (flag + " " + content).getBytes();
                    System.out.println("Packet: " + new String(sendData));
                    System.out.println("Dest IP: " + IpDest.toString() + "\nDest Port: " + PortDes);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, (this.flag + " " + this.content).length(), IpDest, PortDes);
                    dsoc.send(sendPacket);
                    System.out.println("Packet sent!");
                    dsoc.close();
                    System.out.println("DatagramSocket Closed");
                } catch (IOException ex)
                {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // file <file_name>
            if(flag.equals("file"))
            {
                System.out.println("Packet FILE");
                try 
                {
                    byte[] sendData = (flag + " " + content).getBytes();
                    System.out.println("Packet: " + new String(sendData));
                    System.out.println("Dest IP: " + IpDest.toString() + "\nDest Port: " + PortDes);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, (this.flag + " " + this.content).length(), IpDest, PortDes);
                    dsoc.send(sendPacket);
                    System.out.println("Packet sent!");
                    dsoc.close();
                    System.out.println("DatagramSocket Closed");
                } catch (IOException ex)
                {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // star: <file_name>
            if(flag.equals("star"))
            {
                System.out.println("Packet STAR");
                try 
                {
                    byte[] sendData = (flag + " " + content).getBytes();
                    System.out.println("Packet: " + new String(sendData));
                    System.out.println("Dest IP: " + IpDest.toString() + "\nDest Port: " + PortDes);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, (flag + " " + content).length(), IpDest, PortDes);
                    dsoc.send(sendPacket);
                    System.out.println("Packet sent!");
                    dsoc.close();
                    System.out.println("DatagramSocket Closed");
                    
                    Receive r = new Receive("Receive FILE");
                    r.content = content; // content = file_name
                    r.flag = "";
                    r.run();
                    
                } catch (IOException ex)
                {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // send <path_to_file>
            if(flag.equals("send"))
            {
                System.out.println("Packet SEND");
                File file = new File(content); // content = file_path
                byte[] result = new byte[16374];
                InputStream input = null;
                
                try 
                {
                    long totalBytesRead = 0;
                    input = new BufferedInputStream(new FileInputStream(file));
                    long file_length = file.length();
                    while (totalBytesRead < file_length)
                    {
                        int bytesRead = input.read(result, (int) totalBytesRead, 16384);
                        if(bytesRead > 0)
                        {
                            totalBytesRead = totalBytesRead + bytesRead;
                            DatagramPacket sendPacket = new DatagramPacket(result, result.length, IpDest, PortDes);
                            dsoc.send(sendPacket);
                            System.out.println("Send file, part from " + (totalBytesRead - bytesRead) + " to " + totalBytesRead);
                        }
                    }
                    
                    System.out.println("All file sent!");
                    dsoc.close();
                    System.out.println("DatagramSocket Closed");
                    
                    input.close();
                    Send s = new Send("send EOF", "oef");
                    s.content = content.substring(content.lastIndexOf('\\'), content.length());
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
                System.out.println("Packet EOF");
                try 
                {
                    byte[] sendData = (flag + " " + content).getBytes();
                    System.out.println("Packet: " + new String(sendData));
                    System.out.println("Dest IP: " + IpDest.toString() + "\nDest Port: " + PortDes);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IpDest, PortDes);
                    dsoc.send(sendPacket);
                    System.out.println("Packet sent!");
                    dsoc.close();
                    System.out.println("DatagramSocket Closed");
                } catch (IOException ex)
                {
                    Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
