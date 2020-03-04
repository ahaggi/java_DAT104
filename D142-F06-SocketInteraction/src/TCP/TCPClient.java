package TCP;

import java.io.*;
import java.net.*;

public class TCPClient implements Runnable{

	

	@Override
	public void run() {
		try {
			String sentence;
			String replyFromServer;

			Socket clientSocket = new Socket("localhost", 6359);

			while (true) {

				// BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
				
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));// create input stream  attached  to socket
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());// create output stream attached to socket
				
				sentence = "What is the time? asks " + Thread.currentThread().getName();

				outToServer.writeBytes(sentence + '\n');

				replyFromServer = inFromServer.readLine();

				System.out.println("FROM SERVER: " + replyFromServer);
				
				Thread.sleep(1000);
			}

			// clientSocket.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	} 

	
	
}
