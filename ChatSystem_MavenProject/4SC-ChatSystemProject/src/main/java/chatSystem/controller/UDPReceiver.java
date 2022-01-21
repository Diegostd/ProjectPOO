package chatSystem.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.event.EventListenerList;



public class UDPReceiver extends Thread {
	private DatagramSocket socketForReceive;
	private EventListenerList listeners;
	private Boolean stopThread;
	private InetAddress addressSrc;
	//private NetworkController nc = new NetworkController();
	//private int port = 5556;


	public UDPReceiver(int port) throws SocketException {
		this.socketForReceive = new DatagramSocket(port);
		//start();
		
		this.stopThread = false;
		/*listeners = new EventListenerList();
		try {
			this.socketForReceive = new DatagramSocket(5005);
		} catch (SocketException e) {
			e.printStackTrace();
		}*/

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
	
	
	
	public void receive_Message() throws IOException, ClassNotFoundException {
		NetworkController nc = new NetworkController();
		byte[] buffer = new byte[256]; 
		DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
		String text=""; 
		int i = 1; 
		String data; 
		int test = 1;
		while (!text.equals("notOk")){
		//while (test==1) {
			//receive() method blocks until a datagram is received. 
			socketForReceive.receive(inPacket);
			//Accept the sender's address and port from the packet
			InetAddress clientAddress = inPacket.getAddress(); 
			int clientPort = inPacket.getPort(); 
			System.out.println("client address : "+ clientAddress.toString() + " client port number : " + clientPort);
			
			//retrieve the data from the buffer
			text = new String(inPacket.getData(), 0, inPacket.getLength()); 
			System.out.println("Message received : " + text);
			//Check unicity
			nc.receivedFirstMsgHello(clientAddress, text);
			
			/*//Create the response datagram
			if (text.equals("bye")) data = "Last message from server! ";
			else data = "Message: " +i+ " from server";
			buffer = data.getBytes();
			 
			DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
			socketForReceive.send(response);*/
			i++; 
			//handle one client who can send one msg only
			test=0;
			
		} 
		
		//socketForReceive.close();
	}
	
	//THREAD
	public void run() {
		try {
			receive_Message();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	
	
	/*public MessageListener[] getListeners() {
		return listeners.getListeners(MessageListener.class);
	}*/

	/*protected void NewReceivedMessage(InetAddress ipsrc, String message){
		for (MessageListener listener : getListeners()){
			listener.aMessageHasBeenReceived(ipsrc, message);
		}
	}*/
	
	//Fonction to obtain the AdressIp
	//Before this, I also tried to get the address by declaring 
	//it as public static, but it didn't work either
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
	
	/*public void CheckUnicity(String newPseudo) {
		if (UsersList.contains(newPseudo)) {
			
		}
	}*/
	
	
	
	//Fonctions under construction
	public EventListenerList getListeners() {
		return listeners;
	}

	public void setListeners(EventListenerList listeners) {
		this.listeners = listeners;
	}
	
	

}
