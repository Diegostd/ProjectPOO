package chatSystem.controller;

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

import chatSystem.model.*;



public class UDPReceiver extends Thread implements Serializable{
	private NetworkController messageExchanged;
	private DatagramSocket socketForReceive;
	private UDPSender udps;
	private UDPMessage udpm;
	private State state;
	private boolean running = true;
	private EventListenerList listeners;
	private Boolean stopThread;
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
	
	
	//thread
	/*public void run() {
		byte[] buf = new byte[2048];
		String message = null;

		while(!this.stopThread){
			// to receive a message
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			//String hostIP = addressSrc.getHostAddress() ;
			
		
			try {
				socketForReceive.receive(packet);
				// unpackage to a message
				addressSrc = packet.getAddress();
				ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
				try {
					ObjectInputStream ois = new ObjectInputStream(bais);
					message = (String) ois.readObject();
					
					//We print in the console the pseudo and the IP of the user 
					//who sent the message, where the pseudo is the message
					System.out.println(message+" [UDPReceiver] pseudo part");
					System.out.println(addressSrc.toString()+ "  [UDPReceiver] Ip ");
					
					
					//I did this line of code because I thought that the thread did 
					//not allow to do the other things, apparently it is not that.
					stopThread=true;
					
				}
				
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				// Inform, who wants the message
				//this.NewReceivedMessage(packet.getAddress(), message);
			}
			catch (SocketException e){
				System.err.println("java.net.SocketException: [UDPR]Socket closed");
				this.stopThread = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}*/
	
	
	
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
				//UDPMessage udpMessage = UDPMessage.deserializeMessage(text); Option 1
				UDPMessage udpMessage = new UDPMessage(text); //Option 2
				//UDPMessage udpMessage = UDPMessage.deserializeMessage(text);
				//State state = State.CONNECTING;
				//udpMessage.withTheStatus();
				System.out.println("[UDPReceiver] Message received : " + text);
				if(!udpMessage.getUserPhone().equals("7894561232")) {
					this.messageExchanged.newReceivedBroadcastMessage(udpMessage);
				}
			} catch (SocketTimeoutException e) {	
			}
			
		/*//This is for another part
			//Check unicity
			if (!text.equals("bye")) {
				nc.receivedFirstMsgHello(clientAddress, text);	
			}
			
			//Create the response datagram
			if (text.equals("bye")) data = "Last message from server! ";
			else data = "Message: " +i+ " from server";
			buffer = data.getBytes();
			 
			DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
			socketForReceive.send(response);
			//handle one client who can send one msg only
			
		} */
				
		}
		closeSocket();
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

	public void setStopThread(Boolean stop){
		this.stopThread = stop;
	}
	
	public void closeSocket(){
		if (!this.socketForReceive.isClosed()){
			this.socketForReceive.close();
		}
	}
	
	
	
	

}
