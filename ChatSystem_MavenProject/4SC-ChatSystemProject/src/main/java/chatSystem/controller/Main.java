package chatSystem.controller;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import chatSystem.interfaces.ConnexionWindow;
import chatSystem.interfaces.MainWindow;
import chatSystem.model.ModelMessages;
import chatSystem.model.State;
import chatSystem.model.UDPMessage;
import chatSystem.model.User;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			InetAddress localHost = InetAddress.getLocalHost();
			User user = new User("toto", localHost, "7894561231");
			//UDPMessage msg = new UDPMessage("toto");
			UDPMessage udpm = new UDPMessage(user);
			final NetworkController messagesExchanged = new NetworkController();
			//controlGUI controlgui = new controlGUI();
			//nc.NewUserBroadcast("titi");
			//nc.NewUserBroadcast("toto");
			
			//UDPListener serverUDP = new UDPListener("UDP_Server");
			//UDPReceiver serverUDP = new UDPReceiver();
			UDPSender client = new UDPSender();
			//UDPMessage udpm = new UDPMessage(null); Test 1
			String message = "hello";
			//String send = udpm.serializeMessage();
			
			
			//UDPReceiver th1 = new UDPReceiver(5557);
			//messagesExchanged.sendPseudo(udpm);
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
                new ConnexionWindow(messagesExchanged);
            }
			});
			
			//String broadcast = new UDPMessage(msg).withTheStatus(State.CONNECTING); Test 1
			//messagesExchanged.notifyToAllUserStateUpdate(State.CONNECTING); Test 1
			//client.sendMessageBroadcastUDP(message);			Test 2
			//UDPReceiver th2 = new UDPReceiver(5557);
			//th1.start();
			//th2.start();
			//client.send_Message();
			
		
			
			
			
			//ConnexionWindow frame = new ConnexionWindow();
		//	frame.setVisible(true);
			

	}

}
