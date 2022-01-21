package ChatSystem.network;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;



public class main {

	public static void main(String[] args) throws IOException {
		
		// TODO Auto-generated method stub
				//UDPListener serverUDP = new UDPListener("UDP_Server");
				UDPserver server_udp = new UDPserver();
				UDPclient client = new UDPclient();
				client.send_Message();
				
	}

}
