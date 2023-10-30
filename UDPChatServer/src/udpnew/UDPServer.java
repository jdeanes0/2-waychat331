package udpnew;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class for the server; calls upon UDPSend and UDPReceive after initializing.
 * 
 * @author jdeanes0
 * @version 10/29/23
 */
public class UDPServer {
    
    static DatagramSocket socket;
	static HashSet<SocketAddress> group;
	static InetAddress addr;
	static String ip;
	static boolean firstTime;
    public static void main(String[] args) {
        //UDPSend sender = new UDPSend(); // loop to send messages
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

	    /**
     * Loop to handle input exceptions when getting integers
     * 
     * @param s Scanner object, default settings
     * @return port number
     */
    public static int getPort(Scanner s, String prompt) {
        int aport;
        do {
            try {
                System.out.print(prompt);
                aport = s.nextInt();
                break; // break once the port number is valid.
            } catch (InputMismatchException e) {
                System.err.println("That is not a number, much less a port number.");
            }
        } while (true);

        return aport;
    }
}
