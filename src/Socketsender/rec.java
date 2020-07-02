package Socketsender;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class rec {
	static int port;
	DataOutputStream out;
	DataInputStream in;


	
	public rec(int port) {
		this.port = port;
	}
	
	public void run() {
		ServerSocket server = null;
		Socket client = null;

		
		// creates a server on a given port and posts address to login
		try {
			server = new ServerSocket(port);
			client = server.accept();
			out = new DataOutputStream(client.getOutputStream());
			in = new DataInputStream(client.getInputStream());
			String firstInput = in.readUTF();
			System.out.println(firstInput);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public static void main(String [] args) {
		rec server = new rec(8888);
		server.run();
	}


}
