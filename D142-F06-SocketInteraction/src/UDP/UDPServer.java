package UDP;

import java.io.*;
import java.net.*;
import java.time.Instant;

class UDPServer implements Runnable {
	DatagramSocket serverSocket;

	public UDPServer() throws SocketException {
		serverSocket = new DatagramSocket(8123);
	}

	public void run() {
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		{
			while (true) {
 
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					try {
						serverSocket.receive(receivePacket);

						String sentence = new String(receivePacket.getData());

						InetAddress IPAddress = receivePacket.getAddress();
						int port = receivePacket.getPort();

						// String capitalizedSentence = sentence.toUpperCase();
						System.out.println("Packet received from: " + IPAddress.getHostName() + " " + receivePacket.getPort());
						System.out.println("Data received: " + sentence);


						// sendData = capitalizedSentence.getBytes();
						String svar = Instant.now().toString() + " Reply to "+ sentence.substring(sentence.lastIndexOf("asks") + 4);
						sendData = svar.getBytes();

						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

						serverSocket.send(sendPacket);
						Thread.sleep(2000);

					} catch (Exception e) {
						e.printStackTrace();
					}
 			}

		}
	}
}
