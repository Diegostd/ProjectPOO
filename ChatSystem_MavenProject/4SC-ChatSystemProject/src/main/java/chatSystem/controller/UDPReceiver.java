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
	private DatagramSocket socket;
	private EventListenerList listeners;
	private Boolean stopThread;


	public UDPReceiver() {
		this.stopThread = false;
		listeners = new EventListenerList();
		try {
			this.socket = new DatagramSocket(1234);
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
				socket.receive(packet);
				// unpackage to a message
				ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
				try {
					ObjectInputStream ois = new ObjectInputStream(bais);
					message = (String) ois.readObject() ;
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
		if (!this.socket.isClosed()){
			this.socket.close();
		}
	}

}
