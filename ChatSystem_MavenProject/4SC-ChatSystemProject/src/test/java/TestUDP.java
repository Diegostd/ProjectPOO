import java.net.InetAddress;
import java.net.UnknownHostException;

import chatSystem.model.UDPMessage;
import chatSystem.model.User;

public class TestUDP {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		User usr = new User("toto", InetAddress.getLocalHost(), "651654613");
		UDPMessage UDPMessage = new UDPMessage(usr);
		
		System.out.println(UDPMessage.serializeMessage());
	}

}
