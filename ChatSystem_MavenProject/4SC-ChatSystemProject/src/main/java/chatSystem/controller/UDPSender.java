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

public class UDPSender implements Serializable {
	private DatagramSocket socketSender;
	private String hostname = "255.255.255.255"; 
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
	
	//Old function to send the message
	//Create the part of the message that we will send
	public void sendMessage(String message, InetAddress iptosend){
		int port = 5557;
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
	
	
	//NEW SEND MESSAGE
	public void send_MessageNew(String messageNew, InetAddress ipsrcNew) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
            String text = messageNew;
            System.out.println("Message que envia : " + text);
            System.out.println("IP a donde envia : " + ipsrcNew.toString());
            DatagramPacket outPacket = new DatagramPacket(text.getBytes(), text.length(), ipsrcNew, port);
            socketSender.send(outPacket);
		  } catch (IOException e) {
				System.err.println("message failed to send");
				e.printStackTrace();
			}	
	}	
	
	//NEW
	public void send_Message() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text="";
        //while (!text.equals("bye")){
        do {
        	System.out.print("Enter something:");
            text = br.readLine();
            System.out.print("text: "+text);
            DatagramPacket outPacket = new DatagramPacket(text.getBytes(), text.length(), address, port);
            socketSender.send(outPacket);
            //Create a buffer for incoming datagrams
            byte[] buffer = new byte[256];
            //Create a DatagramPacket object for the incoming datagram
            DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
            //Accept an incoming datagram
            socketSender.receive(inPacket);
            //Retrieve the data from the buffer
            String response = new String(inPacket.getData(), 0, inPacket.getLength());
            System.out.println("received DATA from UDP Server = " + response);
        }
 
        while (!text.equals("bye"));
            	//Close the DatagramSocket:
        socketSender.close();
            
        } /*catch (IOException e) {
			System.err.println("message failed to send");
			e.printStackTrace();
		}	*/
        
        
        /* while (!text.equals("bye"));
        //Close the DatagramSocket:*/
       
	
	
	
	

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

