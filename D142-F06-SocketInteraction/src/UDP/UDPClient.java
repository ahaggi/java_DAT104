package UDP;

import java.io.IOException;
import java.net.*;

class UDPClient implements Runnable {
	// BufferedReader inFromUser = new BufferedReader(new
	// InputStreamReader(System.in));

	public UDPClient() throws Exception {

	}

	@Override
	public void run() {
 		while (true) {
			try {

				DatagramSocket clientSocket = new DatagramSocket(9876);
				InetAddress IPAddress = InetAddress.getByName("localhost");

 
				byte[] sendData = new byte[1024];
				byte[] receiveData = new byte[1024];
				String sentence = "";
				// String sentence = inFromUser.readLine();
				sentence = "What is the time? asks " + Thread.currentThread().getName();
				sendData = sentence.getBytes();

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8123);
				try {

					clientSocket.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("ikke sendt" + Thread.currentThread().getName());

				}
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

				try {
					clientSocket.receive(receivePacket);
					Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ikke motatt" + Thread.currentThread().getName());
				}
				String modifiedSentence = new String(receivePacket.getData());

				System.out.println("FROM SERVER:" + modifiedSentence + "\n\n");
				 clientSocket.close();
			} catch (Exception e1) {
				e1.printStackTrace();
 			}

		}
	}

}