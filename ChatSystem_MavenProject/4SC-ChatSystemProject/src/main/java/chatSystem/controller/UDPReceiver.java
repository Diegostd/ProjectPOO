package chatSystem.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.event.EventListenerList;



public class UDPReceiver{
	private DatagramSocket socketForReceive;
	private EventListenerList listeners;
	private Boolean stopThread;


	public UDPReceiver() {
		
		listeners = new EventListenerList();
		try {
			this.socketForReceive = new DatagramSocket(5000);
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

	public void ReceiveMessage() {
		byte[] buf = new byte[2048];
		String message = null;

		while(!this.stopThread){
			// receive a message
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			try {
				socketForReceive.receive(packet);
				// unpackage to a message
				ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
				try {
					ObjectInputStream ois = new ObjectInputStream(bais);
					message = (String) ois.readObject();
					System.out.println(message);
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
	}
	
	/*public MessageListener[] getListeners() {
		return listeners.getListeners(MessageListener.class);
	}*/

	/*protected void NewReceivedMessage(InetAddress ipsrc, String message){
		for (MessageListener listener : getListeners()){
			listener.aMessageHasBeenReceived(ipsrc, message);
		}
	}*/

	public void setStopThread(Boolean stop){
		this.stopThread = stop;
	}

	public void closeSocket(){
		if (!this.socketForReceive.isClosed()){
			this.socketForReceive.close();
		}
	}
	
	//here we check the unicity of the pseudo of the users 
	/*public void CheckUnicity(String newPseudo) {
		if (UsersList.contains(newPseudo)) {
			
		}
	}*/

	public EventListenerList getListeners() {
		return listeners;
	}

	public void setListeners(EventListenerList listeners) {
		this.listeners = listeners;
	}
	
	

}
