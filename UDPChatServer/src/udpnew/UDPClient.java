package udpnew;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class for the client; calls upon UDPSend and UDPReceive after initializing.
 * 
 * @author jdeanes0
 * @version 10/29/23
 */
public class UDPClient {
    public static void main(String[] args) {
        // Get basic info about target
        Scanner s = new Scanner(System.in);

        InetAddress local; // Local IP necessary only for debug/printing to console.
        InetAddress IP;

        do {
            try {
                System.out.print("Target IP> ");
                String anip = s.nextLine();
                local = InetAddress.getLocalHost();
                IP = InetAddress.getByName(anip);
                break; // break once the IP number is valid.
            } catch (UnknownHostException e) {
                System.err.println("IP address could not be found.");
            }
        } while (true);
        String hostname = local.getHostName();
        String hostAddress = local.getHostAddress();
        
        int hport = getPort(s, "Host Port> ");
        int tport = getPort(s, "Target Port> ");

        // Shows that the client is active
        System.out.println("IP Address: " + hostAddress);
        System.out.println("Port #: " + hport);
        System.out.println("Hostname: " + hostname);

        /*
         * Necessary fields have now been retrieved from the user.
         * Create two sockets and give them to the threads to begin their send/receive loops.
         */
        DatagramSocket socket;
        do {
            try {
                socket = new DatagramSocket(hport);
                break;
            } catch (SocketException e) {
                e.printStackTrace();
            }
        } while (true);
         
        UDPReceive udpr = new UDPReceive(socket);
        UDPSend udps = new UDPSend(tport, IP, socket);

        Thread send = new Thread(udps);
        Thread receive = new Thread(udpr);

        receive.start();
        send.start();
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
