package udpnew;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.HashSet;

public class UDPServer {
    
    static DatagramSocket socket;
	static HashSet<SocketAddress> group;
	static InetAddress addr;
	static String ip;
	static boolean firstTime;
    public static void main(String[] args) {
        UDPSend sender = new UDPSend(); // loop to send messages
        UDPReceive receiver = new UDPReceive(); // loop to receive messages

        firstTime = true;
		System.out.println("*****Server Started*****");
		group = new HashSet<SocketAddress>();
		int port = 4000;
		try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		try {
			// Get all the host information
			InetAddress addr = InetAddress.getLocalHost();
			String hostname = addr.getHostName();
			String hostAddress = addr.getHostAddress();
			System.out.println("IP Address: " + hostAddress);
			System.out.println("Port #: " + port);
			System.out.println("Hostname: " + hostname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Client Activity:");
    }

}
