package chatSystem.controller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import chatSystem.interfaces.*;

public class UDPSender {
	private DatagramSocket socketSender;



	public UDPSender(){
		try {
			this.socketSender = new DatagramSocket();
		} catch (SocketException e) {
			System.err.println("Socket couldn't be created.");
			e.printStackTrace();
		}
	}

	//Create the part of the message that we will send
	public void sendMessage(String message, InetAddress iptosend){
		int port = 1234;
		byte[] buf = new byte[2048];

		ByteArrayOutputStream Baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(Baos);
			oos.writeObject(message);
			oos.close();
			buf=Baos.toByteArray();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		DatagramPacket Messagetosend = new DatagramPacket(buf, buf.length, iptosend, port);

		try {
			this.socketSender.send(Messagetosend);
		} catch (IOException e) {
			System.err.println("message failed to send");
			e.printStackTrace();
		}
	}

	public void sendMessageBroadcast(String msgToSend){
		int port = 5005;
		byte[] buffer = new byte[2048];

		// Here we create the packet to send
		ByteArrayOutputStream Baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream Oos = new ObjectOutputStream(Baos);
			Oos.writeObject(msgToSend);
			Oos.close();
			buffer=Baos.toByteArray();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Here we send the packet on broadcast
		try {
			InetAddress iptosend = InetAddress.getByName("255.255.255.255");
			DatagramPacket MSGToSend = new DatagramPacket(buffer, buffer.length, iptosend, port);

			this.socketSender.send(MSGToSend);
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

