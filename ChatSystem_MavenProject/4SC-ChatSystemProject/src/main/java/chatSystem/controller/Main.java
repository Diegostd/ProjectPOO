package chatSystem.controller;

import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import chatSystem.interfaces.ConnexionWindow;
import chatSystem.model.Model;
import chatSystem.model.User;

public class Main {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub

			@SuppressWarnings("unused")
			
			//controlGUI controlgui = new controlGUI();
			NetworkController nc = new NetworkController();
			nc.NewUserBroadcast("titi");
			nc.NewUserBroadcast("toto");
			
			//ConnexionWindow frame = new ConnexionWindow();
		//	frame.setVisible(true);
			

	}

}
