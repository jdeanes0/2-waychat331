package udpnew;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.HashSet;
import java.io.IOException;
import java.net.DatagramPacket;

public class UDPServerReceive extends UDPReceive{

    private boolean firstTime;
    private HashSet<SocketAddress> group;
    
    public UDPServerReceive(DatagramSocket socket) {
        super(socket);
    }

    /**
     * Thread that is like UDPReceive but keeps track of the users that connect.
     * 
     * Additionally, will spawn a new thread if a new user connects.
     */
    public void run() {
        while (true) {
			byte[] buffer = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            } // receive the message over the packet from the client
			System.out.println(new String(packet.getData(), 0, packet.getLength()));
			group.add(packet.getSocketAddress()); // get the IP & port # of the client
            int currentPort = packet.getPort();
            InetAddress currentIP = packet.getAddress();
			
			for (SocketAddress s:group) {
				if (firstTime == true) {
                    UDPSend udps = new UDPSend(currentPort, currentIP, socket);
                    Thread send = new Thread(udps);
                    send.start(); // begin a thread to allow it to communicate to a client.
					String dat = "First Client Connected!";
					DatagramPacket greet = new DatagramPacket(dat.getBytes(), dat.length(), s); // Create a datagram with the appropriate headers
					try {
                        socket.send(greet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } // send out the packet with the headers through the socket
					firstTime = false;
				} // end of if
			} // end of for
		} // end of while
    }
}
