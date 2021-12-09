package chatSystem.controller;

import java.net.InetAddress;

import javax.swing.event.EventListenerList;

public class MessageListener extends EventListener {
	
	void aMessageHasBeenReceived(InetAddress ipsrc, String message);
}
