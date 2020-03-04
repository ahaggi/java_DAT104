package UDP_Oblig3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.*;
import java.nio.ByteBuffer;


 public class UDPServer { 
     	public static void main(String[] args) throws Exception {

 
        DatagramSocket serverSocket = new DatagramSocket(2358); 
		InetAddress IPAddress = InetAddress.getByName("localhost");

        String eofString = ".";
        byte[] receiveData = new byte[1024]; 
        byte[] sendData  = new byte[1024]; 
             
            // Her burde det være en (3-veis) oppkobling, men vi hopper over
            // det for enkelhets skyld.
            int sekvensnummer = 0;

            // Les filnavn:
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
//            serverSocket.receive(receivePacket); 
//            String filnavn = new String(receivePacket.getData()); 
             
			BufferedReader reader = new BufferedReader(new FileReader("UntitledDocument.txt"));
			String linje = reader.readLine();
			
//            int port = receivePacket.getPort(); 
            
            while (linje!=null){
 
                // Lag og send pakke til klient
                sendData = (sekvensnummer+linje).getBytes();
 
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 7743);
                
                // Vent på ACK
                 Integer ack = new Integer(-1);
                do{
                    serverSocket.send(sendPacket);
 
                    receiveData = new byte[1024];
                    receivePacket = new DatagramPacket(receiveData, receiveData.length); 
                    
                    serverSocket.receive(receivePacket); 

                    ack = ByteBuffer.wrap(receivePacket.getData()).getInt();
                } while (ack != sekvensnummer);
 				Thread.sleep(1000);

                // Forbered neste sekvensnummer
                sekvensnummer = 1 - sekvensnummer;
    			linje = reader.readLine();
            }        
            sendData = eofString.getBytes();
            // Her burde det kanskje være en nedkobling,
            //  men vi hopper over det for enkelhets skyld. 

       
    }    // main()
}
 