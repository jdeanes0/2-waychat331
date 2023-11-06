package udpblast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Blast {

    public static void main(String[] args) {
        /*
         * Just fucking yeet UDP packets across the network
         */

         int fport = 4000;
         int hport = 4000;
         String IP = "10.103.50.231";

         try {
            InetAddress I = InetAddress.getByName(IP);

            DatagramSocket socket = new DatagramSocket(hport);
            String msg = "HELLO????";
            DatagramPacket p = new DatagramPacket(msg.getBytes(), msg.length(), I, fport);
            socket.send(p);
            socket.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    
}
