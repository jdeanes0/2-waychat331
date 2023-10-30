package udpnew;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Class to Send individual UDP messages in a loop
 * 
 * @author jdeanes0
 * @version 10/29/23
 */
public class UDPSend implements Runnable {

    private int tport;
    private InetAddress IP;
    private DatagramSocket socket;

    /**
     * Constructs a sending thread.
     * 
     * @param tport port number of the receiving system
     * @param IP IP of the receiving system
     * @param socket socket on the host system
     */
    public UDPSend(int tport, InetAddress IP, DatagramSocket socket) {
        this.tport = tport;
        this.IP = IP;
        this.socket = socket;
        System.out.println("Thread initialized. Once running, type \"Close Chat Program\" to quit.");
    }
    
    /**
     * Continuous loop to get uio and send the packets.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String st = sc.nextLine();
            if (st.equals("Close Chat Program")) {
                break;
            }
            DatagramPacket thismsg = new DatagramPacket(st.getBytes(), st.length(), IP, tport);
            try {
                socket.send(thismsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sc.close();
        socket.close();
    }

}

