package Socketsender;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class main {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Sender sender = new Sender(InetAddress.getLocalHost().getHostAddress(),9999);
		sender.start();

	}

}
