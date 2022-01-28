package chatSystem.network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import javax.swing.event.EventListenerList;

import chatSystem.controller.NetworkController;
import chatSystem.model.*;



public class UDPReceiver extends Thread implements Serializable{
	private NetworkController messageExchanged;
	private DatagramSocket socketForReceive;
	private boolean running = true;
	private InetAddress addressSrc;
	private int timeExpired = 1000;
	//private NetworkController nc = new NetworkController();
	//private int port = 5556;


	public UDPReceiver(int port, NetworkController messagesExchanged) throws SocketException {
		//start();
		try {
			this.socketForReceive = new DatagramSocket(port);
			this.messageExchanged = messagesExchanged;
		} catch (SocketException e) {
			e.printStackTrace();
			 System.out.println("Problem in creating the listener for the UDPReceiver");
		}

	}
	
	
	public void listenerToUDPMessages() throws IOException, ClassNotFoundException {
		System.out.println("[UDPReceiver] UPD Listener is listening");
		String text="";
		
		socketForReceive.setSoTimeout(timeExpired);
		while (running){
			byte[] buffer = new byte[48000]; 
			try {
				DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
				//receive() method blocks until a datagram is received.
				socketForReceive.receive(inPacket);
				
				//Accept the sender's address and port from the packet
				InetAddress clientAddress = inPacket.getAddress(); 
				int clientPort = inPacket.getPort(); 
				System.out.println("[UDPReceiver] client address : "+ clientAddress.toString() + " client port number : " + clientPort);
				
				//retrieve the data from the buffer
				text = new String(inPacket.getData(), 0, inPacket.getLength()); 
				//System.out.println("[UDPReceiver] Message received,antes : " + text);
				//UDPMessage udpMessage = UDPMessage.deserializeMessage(text); //Option 1
				//UDPMessage udpMessage = new UDPMessage(text); //Option 2
				UDPMessage udpMessage = UDPMessage.deserializeMessage(text); //Option 3 
				//State state = State.CONNECTING;
				//udpMessage.withTheStatus();
				System.out.println("[UDPReceiver] Message received : " + text);
				if(!udpMessage.getUserPhone().equals(NetworkController.getLocalPhone())) {
					System.out.println("[UDPReceiver] Telephone different");
					this.messageExchanged.newReceivedBroadcastMessage(udpMessage);
				}
				else {
					System.out.println("[UDPReceiver] Same ID, same telephone, so he will not add himself to the list");
				}
			} catch (SocketTimeoutException e) {	
			}
				
		}
		closeSocket();
		System.out.println("[UDPReceiver] Socket closed for receiveng UDP messages");
	}
	
		
		
	//THREAD
	public void run() {
		try {
			listenerToUDPMessages();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	 public void setRunning(boolean running) {
	        this.running = running;
	    }
	
	
	public InetAddress getAddressIp() {
	     return this.addressSrc;
	  }
	
	public void closeSocket(){
		if (!this.socketForReceive.isClosed()){
			this.socketForReceive.close();
		}
	}
	
	
	
	

}
