package udpnew;

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

    public static void main(String[] args) {
        // full re-write BABYYYYY
        // So how to init?
        /*
         * Get local IP
         * Get port to open on
         * Print port and IP to console
         * No greeting, I don't really care about greeting messages.
         * 
         * Create the socket
         * Provide it to the threads aaaaaaand go.
         * Also after getting each thread: if another user wants to connect, 
         */
        Scanner s = new Scanner(System.in);

        int hport = getPort(s, "Host Port> ");

        try {
			// Get all the host information
			InetAddress addr = InetAddress.getLocalHost();
			String hostname = addr.getHostName();
			String hostAddress = addr.getHostAddress();
			System.out.println("IP Address: " + hostAddress);
			System.out.println("Port #: " + hport);
			System.out.println("Hostname: " + hostname);
		} catch (Exception e) {
			e.printStackTrace();
		}

        do {
            try {
                socket = new DatagramSocket(hport, addr);
                break;
            } catch (SocketException e) {
                e.printStackTrace();
                break;
            }
        } while (true);

        System.out.println("*****Server Started*****");
		group = new HashSet<SocketAddress>();

        UDPServerReceive udpr = new UDPServerReceive(socket);

        Thread receive = new Thread(udpr);

        receive.start();
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
