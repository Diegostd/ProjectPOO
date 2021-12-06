package chatSystem.protocols;

import java.net.*;
import java.net.http.WebSocket.Listener;
import java.io.IOException;
import java.lang.System;
import java.util.*; 

//Principles : 
//** implement a permanent listener to detect new user on the network
//** Periodically sends a packet to signal its presence on the network

public class UdpHanlder extends Thread{
	
	//listening port 
	private final int serverPort = 1338;
	// Listener socket
    private DatagramSocket listenerSocket;
    
    
    // Listen for incoming probes
    private Listener listener;
    
    
	
	
	
	
	

	

}
