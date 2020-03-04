package TCP_Oblig;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.RandomAccess;

import javax.sound.midi.MidiDevice.Info;

public class TCPClient{
	
	public static void main(String[] args) throws Exception {
		TCPClient tcpClient = new TCPClient();
		tcpClient.start();
	}

	


	public void start() {
		try {
			String sentence;
			String replyFromServer;
			Socket clientSocket = new Socket("localhost", 23457);
			System.out.println("denne er en ny client");
			boolean b = true;
			while (b) {
//				 BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));				
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));// create input stream  attached  to socket
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());// create output stream attached to socket
				int r = (new Random()).nextInt(4);

				sentence = "";

				switch (r) {
				case 0:
					sentence ="FULL";
					System.out.println(sentence);
					break;
				case 1:
					sentence ="DATE";
					System.out.println(sentence);
					break;
				case 2:
					sentence ="TIME";
					System.out.println(sentence);
					break;
				case 3:
					sentence ="CLOSE";
					System.out.println(sentence);
					b=false;
					break;
				default:
					break;
				}
				outToServer.writeBytes(sentence + '\n');

				replyFromServer = inFromServer.readLine();

				System.out.println("From server: " + replyFromServer + "\n\n");
				Thread.sleep(1000);

			}
 			clientSocket.close();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	} 

	
	
}
