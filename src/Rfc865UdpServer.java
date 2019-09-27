/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abhishek027
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

     
public class Rfc865UdpServer {
    public static void main (String[] argv) {
        // 1. Open UDP spocket in well known port
        
        DatagramSocket socket = null;
        
        try {
            socket = new DatagramSocket(17); // 17 is the port for Qoute of the day protocol
        }
        catch (SocketException e){
            e.printStackTrace();
            System.exit(-1);
        }
        
        while(true) {
            try {
                //2. Listen for UDP request from the client
                
                byte[] buffer = new byte[1024];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                
                String s = new String(buffer);
                System.out.println(s);
                
                InetAddress IPAddress = request.getAddress();
                int port = request.getPort();
                
                //3. Send UDP reply to client
                
               byte[] replyBuf = "Hi there".getBytes("UTF-8");
               
               DatagramPacket reply = new DatagramPacket(replyBuf, replyBuf.length, IPAddress, port);
               socket.send(reply);
            }
            catch (IOException e) {
            e.printStackTrace();
            }
        }
        
    }
}
