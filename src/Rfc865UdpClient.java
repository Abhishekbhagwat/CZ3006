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
import java.io.UnsupportedEncodingException;
import java.net.*;


public class Rfc865UdpClient {
    static DatagramSocket socket;
    
    public static void main(String[] args) {
        //1. Open UDP socket
        
        try{
            socket = new DatagramSocket();
            InetAddress IpAddress = InetAddress.getByName("Swl2-c2-v0102");
            System.out.println(IpAddress);
            socket.connect(IpAddress, 17);
        }
        catch(SocketException e){
            e.printStackTrace();
            System.exit(-1);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        
            try {
                //2. Send UDP request to server

                byte[] buffer = "Bhagwat Abhishek, SSP2, 172.21.151.183".getBytes("UTF-8");
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.send(request);

                byte[] reply_buffer = new byte[1024];
                DatagramPacket reply = new DatagramPacket(reply_buffer, reply_buffer.length);
                socket.receive(reply);

                String quote = new String(reply_buffer);
                System.out.println(quote);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally{
            socket.close();
            }
    }
}
