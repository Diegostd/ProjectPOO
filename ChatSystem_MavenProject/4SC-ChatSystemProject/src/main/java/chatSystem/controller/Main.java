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
import chatSystem.network.UDPSender;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			final NetworkController messagesExchanged = new NetworkController();
			
			//Main program
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
                new ConnexionWindow(messagesExchanged);
            }
			});
			

	}

}
