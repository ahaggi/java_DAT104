package TCP_Oblig;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.util.Date;

public class TCPServer implements Runnable {
	ServerSocket welcomeSocket ;
	Socket connectionSocket;
	
	public TCPServer(ServerSocket ws, Socket cs) throws IOException {
		welcomeSocket = ws;
		connectionSocket = cs;
	}
	@Override
	public void run() {
 
		String clientSentence;
		String serverReply;
		
		String reply;
		try {

		boolean b=true;
			while (b) {
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());


				clientSentence = inFromClient.readLine();
				System.out.println("From client: " + clientSentence);
				
				switch (clientSentence) {
				case "FULL":
					reply= DateFormat.getDateInstance(DateFormat.FULL).format(new Date());
					serverReply = reply + " Reply to "	+ " port Nr " + connectionSocket.getPort() + "\n";
					break;
				case "DATE":
					reply= DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
					serverReply = reply + " Reply to "	+ " port Nr " + connectionSocket.getPort() + "\n";					
					break;
				case "TIME":
					reply= DateFormat.getTimeInstance(DateFormat.LONG).format(new Date());
					serverReply = reply + " Reply to "	+ " port Nr " + connectionSocket.getPort() + "\n";					
					break;
				case "CLOSE":
					reply= "Connenction closed";
					serverReply = reply + " Reply to "	+ " port Nr " + connectionSocket.getPort() + "\n";					
					b = false;
					break;
				default:
					serverReply="error \n";
					break;
				}
				outToClient.writeBytes(serverReply);
			}
//			 welcomeSocket.close();
		} catch (IOException e) {
 			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		ServerSocket welcomeSocket = new ServerSocket(23457);

		while (true) {
			System.out.println("venter på en ny client");
			Socket connectionSocket = welcomeSocket.accept();
			System.out.println("Connection received from: " + connectionSocket.getInetAddress().getHostName()
					+ " port Nr " + connectionSocket.getPort());

			TCPServer tcpServer= new TCPServer(welcomeSocket, connectionSocket);
			Thread t1 = new Thread(tcpServer);
			t1.start();
		}

		
	}



}
