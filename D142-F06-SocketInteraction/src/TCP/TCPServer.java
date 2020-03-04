package TCP;

import java.io.*;
import java.net.*;
import java.time.Instant;

public class TCPServer implements Runnable {
 	
public TCPServer () throws IOException{

}
	@Override
	public void run() {
 
		String clientSentence;
		String serverReply;
		try {

		ServerSocket welcomeSocket = new ServerSocket(6359);
		Socket connectionSocket = welcomeSocket.accept();

		while (true) {
			System.out.println("Connection received from: " + connectionSocket.getInetAddress().getHostName()+ " port Nr " + connectionSocket.getPort());

			
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

				clientSentence = inFromClient.readLine();
				System.out.println("Data received: " + clientSentence);
				serverReply = Instant.now().toString()  + " Reply to "+ clientSentence.substring(clientSentence.lastIndexOf("asks") + 4)+ '\n';  // responsen må avslutte med endofline, siden klient-klasse bruker "readline()"

				outToClient.writeBytes(serverReply);
				
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
