package chatSystem.controller;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import chatSystem.interfaces.*;
import chatSystem.model.*;

public class UDPSender implements Serializable {
	private DatagramSocket socketSender;
	private String hostname = "255.255.255.255"; 
	private State state;
	private int port = 5557; 
	private InetAddress address;



	public UDPSender() throws UnknownHostException{
		try {
			//Create a DatagramSocket object
			this.socketSender = new DatagramSocket();
			//conversion of hostname to InetAddress needed to send data packet
			this.address = InetAddress.getByName(hostname);
		} catch (SocketException e) {
			System.err.println("Socket couldn't be created.");
			e.printStackTrace();
		}
	}
	   
	
	
	public void send_MessageUDP(String message, InetAddress IP)  {
		try {
			byte[] buffer = message.getBytes();
			DatagramPacket pck = new DatagramPacket(buffer, buffer.length, address, port);
			socketSender.send(pck);
			socketSender.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in sending the UDP message");
		}
		
	}
	

	public void sendMessageBroadcastUDP(String msgToSend){

		// Here we send the packet on broadcast
		try {
			byte[] buffer = msgToSend.getBytes();
			//InetAddress iptosend = InetAddress.getByName("255.255.255.255");
			DatagramPacket MSGToSend = new DatagramPacket(buffer, buffer.length, address, port);
			this.socketSender.send(MSGToSend);
			this.socketSender.close();
		}catch (SocketException e){
			System.err.println("java.net.SocketException: [UDPS]Socket closed");
		}catch (UnknownHostException e1) {
			System.err.println("UnknownHostException for broadcast 255.255.255.255");
			e1.printStackTrace();
		} catch (IOException e) {
			System.err.println("Message failed to send");
			e.printStackTrace();
		}
	}

	
	public State getStateOfTheSender(){
		return this.state;
	}
	
	public void closeSocket(){
		if (!this.socketSender.isClosed()){
			this.socketSender.close();
		}
	}
	
	protected void finalize(){
		if (!this.socketSender.isClosed()){
			this.socketSender.close();
		}
	}
	

}

