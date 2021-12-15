package ChatSystem.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import chatSystem.model.Message;
import chatSystem.model.MsgCheck;
import chatSystem.model.MsgExt;
import chatSystem.model.MsgFile;
import chatSystem.model.MsgGoodbye;
import chatSystem.model.MsgHello;
import chatSystem.model.MsgTxt;


public class UDPSender {
	
	private DatagramSocket socket;
	private Message lastMessage;
	
	public UDPSender(){
		try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			System.err.println("Socket couldn't be created.");
			e.printStackTrace();
		}
	}
	
	public UDPSender(DatagramSocket sock){
		this.socket=sock;
	}
	
	/*
	 * Method that send a given message in UDP mode
	 */
	private void sendMess(Message mes, InetAddress iptosend) {
		this.lastMessage=mes;
		int port = 1234;
		byte[] buf = new byte[2048];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(mes);
			oos.close();
			buf=baos.toByteArray();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		DatagramPacket mestosend = new DatagramPacket(buf, buf.length, iptosend, port);
		try {
			this.socket.send(mestosend);
		} catch (IOException e) {
			System.err.println("Message failed to send");
			e.printStackTrace();
		}
	}
	
	public void sendCheck(int type, InetAddress iptosend, String usernameSrc, String usernameDest){
		MsgCheck mes=null;
		switch(type){
		case 1: 
			//Msg_Check
			mes = new MsgCheck(usernameSrc, usernameDest,false);
			break;
		case 2: 
			//Check_Ok
			mes = new MsgCheck(usernameSrc, usernameDest,true);
			break;
		default : 
			break;
		}
		this.sendMess(mes, iptosend);
	}
	
	/*
	 * Send Hello in Broadcast
	 */
	public void sendHelloAll(String usernameSrc, String usernameDest){
		InetAddress addr=null;
		try {
			addr = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		MsgHello mes = new MsgHello(usernameSrc, usernameDest,false, true);
		this.sendMess(mes, addr);
	}
	
	/*
	 * Send Check in broadcast
	 */
	public void sendCheckAll(String usernameSrc, String usernameDest){
		InetAddress addr=null;
		try {
			addr = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		MsgCheck mes = new MsgCheck(usernameSrc, usernameDest, false);
		this.sendMess(mes, addr);
	}
	
	public void sendHello(int type,InetAddress iptosend, String usernameSrc, String usernameDest){
		boolean ack=false;
		boolean connect=false;
		switch(type){
		case 1 : 
			//Message Hello
			ack=false;
			connect=true;
			break;
		case 2 : 
			//Message Hello_Ok
			ack=true;
			connect=true;
			break;
		case 3 : 
			//Message Hello_Not_Ok
			ack=true;
			connect=false;
			break;
	}
		MsgHello mes = new MsgHello(usernameSrc, usernameDest,ack, connect);
		this.sendMess(mes, iptosend);
	}
	
	/*
	 * Send goodbyeMessage in broadcast
	 */
	public void sendBye(String usernameSrc, String usernameDest){
		InetAddress addr=null;
		try {
			addr = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		MsgGoodbye mes = new MsgGoodbye(usernameSrc, usernameDest);
		this.sendMess(mes, addr);
	}
	
	/*
	 * Send message Text
	 */
	public void sendMessageNormal(InetAddress iptosend, String message, String usernameSrc, String usernameDest){
		MsgTxt mes = new MsgTxt(usernameSrc, usernameDest, message);
		this.sendMess(mes, iptosend);
	}
	
	/*
	 * Send message Text in broadcast
	 */
	public void sendMessageNormalAll(String message, String usernameSrc, String usernameDest){
		InetAddress addr=null;
		try {
			addr = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		MsgTxt mes = new MsgTxt(usernameSrc, usernameDest, message);
		this.sendMess(mes, addr);
	}
	
	/*
	 * Send message File
	 */
	public void sendMessageFile(InetAddress iptosend, String usernameSrc, String usernameDest, byte[] file){
		MsgFile mes=new MsgFile(usernameSrc, usernameDest, file);
		this.sendMess(mes, iptosend);
	}
	
	/*
	 * Send message FileAll
	 */
	public void sendMessageFileAll(String usernameSrc, String usernameDest, byte[] file){
		InetAddress addr=null;
		try {
			addr = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		MsgFile mes=new MsgFile(usernameSrc, usernameDest, file);
		this.sendMess(mes, addr);
	}
	
	public void sendMessageExterieur(String message,InetAddress iptosend, String usernameSrc, String usernameDest){
		MsgExt mes = new MsgExt(usernameSrc, usernameDest, message.getBytes());
		this.sendMess(mes, iptosend);
	}
	
	public DatagramSocket getSocket(){
		return this.socket;
	}
	
	public void closeSocket(){
		System.out.println("Closing socket at port "+this.socket.getLocalPort());
		socket.close();
	}
	
	public Message getLastMessage(){
		return this.lastMessage;
	}
	
	protected void finalize() throws Throwable
    { 
		try{
			System.out.println("Closing socket at port "+this.socket.getLocalPort());
			socket.close();
		}
		finally{
			super.finalize();
		}
    }

}
