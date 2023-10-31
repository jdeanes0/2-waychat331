package udpnew;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Class to receive UDP messages in a loop
 */
public class UDPReceive implements Runnable {

    private DatagramSocket socket;

    public UDPReceive(DatagramSocket socket) {
        this.socket = socket;
    }

    public void run() {
        while (true) {
            byte[] buffer = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
			    socket.receive(packet); // receive the message over the packet from the client
    			System.out.println(new String(packet.getData(), 0, packet.getLength()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } // end of while
    }
}
