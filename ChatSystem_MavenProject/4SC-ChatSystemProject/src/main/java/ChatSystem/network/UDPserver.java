package ChatSystem.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

	
	public class UDPserver extends Thread{
		private int port = 5555;
		private DatagramSocket servSocket; 
		
		public UDPserver() throws SocketException {
			this.servSocket = new DatagramSocket(this.port);
			start(); 
		}
		
		public void receive_msg() throws IOException {
			byte[] buffer = new byte[256]; 
			
			DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
			String text=""; 
			int i =1; 
			String data; 
			while (!text.equals("bye")){
				//receive() method blocks until a datagram is received. 
				servSocket.receive(inPacket);
				
				//Accept the sender's address and port from the packet
				InetAddress clientAddress = inPacket.getAddress(); 
				int clientPort = inPacket.getPort(); 
				System.out.println("client address : "+ clientAddress.toString() + "client port number : " + clientPort);
				
				//retrieve the data from the buffer
				text = new String(inPacket.getData(), 0, inPacket.getLength()); 
				System.out.println("Message received : " + text);
				
				//Create the response datagram
				if (text.equals("bye")) data = "Last message from server! ";
				else data = "Message " +i+ " from server";
				buffer = data.getBytes();
				 
				DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
				servSocket.send(response);
				i++; 
				//handle one client who can send one msg only
			} 
			
			servSocket.close();
		}
		
		public void run() {
			try {
				receive_msg();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}		
	}
