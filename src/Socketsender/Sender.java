package Socketsender;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Sender {
	Socket client;
	DataOutputStream out;
	DataInputStream in;
	String address;
	int port;

	public Sender(String address, int port) throws UnknownHostException, IOException {
		this.address = address;
		this.port = port;
		this.client = new Socket(address, port);
	}

	public String takeIP() {

		String ip;
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface iface = interfaces.nextElement();
				// filters out 127.0.0.1 and inactive interfaces
				if (iface.isLoopback() || !iface.isUp())
					continue;

				Enumeration<InetAddress> addresses = iface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress addr = addresses.nextElement();

					// *EDIT*
					if (addr instanceof Inet6Address)
						continue;

					ip = addr.getHostAddress();
					return ip;
				}
			}
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}

		return null;
	}
	/**
	 * to send Ip to destination 
	 */
	public void start() {
		try {
			out = new DataOutputStream(client.getOutputStream());
			in = new DataInputStream(client.getInputStream());
			out.writeUTF(takeIP());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
