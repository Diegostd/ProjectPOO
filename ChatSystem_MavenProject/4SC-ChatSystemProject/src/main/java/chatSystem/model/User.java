package chatSystem.model;

import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.Timer;



public class User{
	private String username;
	private InetAddress ip;
	private State userState;
	private Timer timer;


	public User(String username, InetAddress ip, ActionListener action) {
		this.username = username;
		this.ip = ip;
		//The states and the timer are not not fully implemented yet
		//this.userState=State.DISCONNECTED;
		//this.timer=new Timer(3000,action);
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