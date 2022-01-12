package chatSystem.model;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.Timer;
public class User {

	/* private String nom;
	 private String hostaddr;
	 //see if a timer parameter would be useful 
	    protected User(String nom, String addr){
	        this.nom=nom;
	        this.hostaddr=addr;
	    }
	    
	    public String getNom(){ return this.nom;}
	    public void setNom(String name){ nom = name;}
	    public String getAddr(){ return this.hostaddr;}
	    
	    @Override
	    public String toString()
	    {
	        return this.getNom()+" : "+this.getAddr();
	    }
	    
	    @Override
	    public boolean equals(Object o)
	    {
	        User u = null;
	        if (o != null && o instanceof User){
	            u = (User) o;
	        }
	        
	        if(u != null)
	            return ((this.hostaddr.equals( u.getAddr() ) ) );
	        else
	            return false;
	    }*/
	
		private String username;
		private InetAddress ip;
		private State userState;
		private Timer timer;


		public User(String username, InetAddress ip, ActionListener action) {
			this.username = username;
			this.ip = ip;
			this.userState=State.DISCONNECTED;
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

}


