package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.HashSet;

public class UDPServer {
	DatagramSocket socket;
	HashSet<SocketAddress> group;
	InetAddress addr;
	String ip;
	boolean firstTime;

	public static void main(String[] args) {
		UDPServer u = new UDPServer();
		try {
			u.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() throws Exception {
		firstTime = true;
		System.out.println("*****Server Started*****");
		group = new HashSet<SocketAddress>();
		int port = 4000;
		socket = new DatagramSocket(port);
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
		while (true) {
			byte[] buffer = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet); // receive the message over the packet from the client
			System.out.println(packet.toString());
			group.add(packet.getSocketAddress()); // get the IP & port # of the client
			
			for (SocketAddress s:group) {
				if (firstTime == true) {
					String dat = "First Client Connected!";
					DatagramPacket greet = new DatagramPacket(dat.getBytes(), dat.length(), s); // Create a datagram with the appropriate headers
					socket.send(greet); // send out the packet with the headers through the socket
					firstTime = false;
				} // end of if
				System.out.println(s);
				DatagramPacket p = new DatagramPacket(buffer, 1024, s);
			} // end of for
		} // end of while
	} // end of method
} // end of class
