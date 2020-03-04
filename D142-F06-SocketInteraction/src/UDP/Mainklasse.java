package UDP;

public class Mainklasse {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		

		
		UDPServer udpServer = new UDPServer();
		Thread t_server = new Thread(udpServer);
		t_server.start();
		
		Thread.sleep(1000);

 		UDPClient udpClient1 = new UDPClient();
		Thread t1 = new Thread(udpClient1);
		t1.start();

//
// 		UDPClient udpClient2 = new UDPClient();
//		Thread t2 = new Thread(udpClient2);
//		t2.start();
//		
// 		UDPClient udpClient3 = new UDPClient();
//		Thread t3 = new Thread(udpClient3);
//		t3.start();

	}

}

