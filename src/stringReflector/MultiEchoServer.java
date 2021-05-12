package stringReflector;

import java.net.ServerSocket;
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
		} catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
		}
		
	}
	
	public void AwaitClientConnection() {
		try {
			this.serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Failed to get client connection" + e);
		}
	}
}
