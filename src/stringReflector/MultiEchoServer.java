package stringReflector;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class MultiEchoServer {

	private ServerSocket serverSocket;
	public MultiEchoServer(){
		
	}
	
	public void EstablishSocket() throws IOException {
		try {
			this.serverSocket = new ServerSocket(8765);
			AwaitClientConnection();
		} catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
		}
		
	}
	
	public void AwaitClientConnection() {
		while(true) {
			try {
				// Accept incoming client connections
				Socket clientSocket = this.serverSocket.accept();

				Thread t = new Thread(() -> EchoString(clientSocket));
				t.start();
			} catch (IOException e) {
				System.err.println("Failed to get client connection" + e);
			}
		}
	}
	
	public void EchoString(Socket clientSocket) {
		try {
			BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
			
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}
		} catch (Exception e) {
			System.err.println("Failed to read buffer stream " + e);
		}
		
	}
}
