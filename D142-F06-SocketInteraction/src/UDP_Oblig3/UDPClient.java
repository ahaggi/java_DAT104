package UDP_Oblig3;

 import java.net.*;
import java.nio.ByteBuffer;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

class UDPClient  {
 
 	public static void main(String[] args) throws Exception {

 				
 				
				DatagramSocket clientSocket = new DatagramSocket(7743);
				InetAddress IPAddress = InetAddress.getByName("localhost");


				byte[] sendData = new byte[1024];
				byte[] receiveData = new byte[1024];
 
//				String sentence = "filnavn";
//				sendData = sentence.getBytes();

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 2358);
				DatagramPacket receivePacket;
//				clientSocket.send(sendPacket);
				String rcvd=" ";
				do {
					receiveData = new byte[1024];
					receivePacket = new DatagramPacket(receiveData, receiveData.length);
					clientSocket.receive(receivePacket);

					rcvd = new String(receivePacket.getData());

					System.out.println(rcvd);

					byte [] ack =  new byte[4];
					Integer ack_nr = Integer.valueOf(rcvd.substring(0,1));		
					ByteBuffer bb = ByteBuffer.allocate(4); 
					bb.putInt(ack_nr); 
					ack = bb.array();

					sendPacket = new DatagramPacket(ack, ack.length, IPAddress, 2358);
//					Thread.sleep(1000);

					clientSocket.send(sendPacket);
				} while (!rcvd.equals("."));

				
				
				clientSocket.close();
				 
 
 	}

}