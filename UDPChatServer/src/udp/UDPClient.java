package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        int port = 3999;
        try {
            DatagramSocket socket = new DatagramSocket(port);

            InetAddress addr = InetAddress.getLocalHost();
            InetAddress foreign = InetAddress.getByName("192.168.56.1");
			String hostname = addr.getHostName();
			String hostAddress = addr.getHostAddress();
			System.out.println("IP Address: " + hostAddress);
			System.out.println("Port #: " + port);
			System.out.println("Hostname: " + hostname);

            byte[] buffer = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            String dat = "Did this work?";
            DatagramPacket senditboi = new DatagramPacket(dat.getBytes(), dat.length(), foreign, 4000);
            socket.send(senditboi);

            while (true) {
                Scanner sc = new Scanner(System.in);
                String st = sc.nextLine();
                DatagramPacket thismsg = new DatagramPacket(st.getBytes(), st.length(), foreign, 4000);
                socket.send(thismsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
