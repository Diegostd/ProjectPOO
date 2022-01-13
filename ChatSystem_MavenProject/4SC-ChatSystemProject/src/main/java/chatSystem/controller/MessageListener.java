package chatSystem.controller;

import java.net.InetAddress;

import javax.swing.event.EventListenerList;

//Not fully implemented
public class MessageListener extends EventListener {
	
	void aMessageHasBeenReceived(InetAddress ipsrc, String message);
}
