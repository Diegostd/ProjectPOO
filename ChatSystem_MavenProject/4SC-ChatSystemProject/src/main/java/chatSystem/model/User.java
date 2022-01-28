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
	private Timer timer;


	public User(String username, InetAddress ip, String phone) {
		this.username = username;
		this.ip = ip;
		this.phone = phone;
		//this.userState=State.CREATED;
		//The states and the timer are not not fully implemented yet
		//this.userState=State.DISCONNECTED;
		//this.tiser=new Timer(3000,action);
	}

	public void startTimer(){
		this.timer.start();
	}

	public void restartTimer(){
		this.timer.restart();
	}

	public void stopTimer(){
		this.timer.stop();
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