package chatSystem.model;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.InetAddress;

import javax.swing.Timer;



public class User implements Serializable{
	private String username;
	private InetAddress ip;
	private String phone; //Phone of the user, ID UNIQUE
	private State userState;
	private int userPort;


	public User(String username, InetAddress ip, String phone) {
		this.username = username;
		this.ip = ip;
		this.phone = phone;
	}


	@Override
	public String toString()
	{
		return this.getUsername()+" : "+ this.getIp();
	}

	public String getUsername() {
		return username;
	}
	
	public String  getUserPhone() {
		return phone;
	}
	
	public int getUserPort() {
		return userPort;
	}
	
	public void setUserPort(int userport) {
		this.userPort = userport;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
	
	public State getState() {
		return this.userState;
	}

	public void setState(State state) {
		this.userState = state;
	}


}