package chatSystem.controller;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import chatSystem.interfaces.ConnexionWindow;
import chatSystem.model.ModelMessages;
import chatSystem.model.User;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

			
			//controlGUI controlgui = new controlGUI();
			//nc.NewUserBroadcast("titi");
			//nc.NewUserBroadcast("toto");
			
			//UDPListener serverUDP = new UDPListener("UDP_Server");
			//UDPReceiver serverUDP = new UDPReceiver();
			UDPSender client = new UDPSender();
			UDPReceiver th1 = new UDPReceiver(5557);
			//UDPReceiver th2 = new UDPReceiver(5557);
			th1.start();
			//th2.start();
			client.send_Message();
			
		
			
			
			
			//ConnexionWindow frame = new ConnexionWindow();
		//	frame.setVisible(true);
			

	}

}
