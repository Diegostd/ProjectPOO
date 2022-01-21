package chatSystem.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;
import chatSystem.model.*;
import javax.swing.DefaultListModel;

public class Model implements Serializable{
	
	private User userLocal;
	public static DefaultListModel<ModelUserList> userList;
	private Path localLog;
	
	
	//We create the user model, using the user class
	//We also declare a new list where users are saved
	public Model(User userLocal) throws IOException {
		this.userLocal = userLocal;
		Model.userList = new DefaultListModel<ModelUserList>();
		//this.userLocal.setState(State.DISCONNECTED);
		//this.localLog = Paths.get(this.userLocal.getUsername()+"Log.txt");
		//this.cleanLog();
	}
	
	public User getUserLocal() {
		return userLocal;
	}
	
	public DefaultListModel<ModelUserList> getRemoteUsers()
	{
		return this.userList;
	}
	
	public void addUserRemote(User userRemote){
		User userTmp = null;
		for(int i=0;i<this.userList.size();i++){
			userTmp = this.userList.getElementAt(i).getUser();
			if (userTmp.getIp().equals(userRemote.getIp())){
				// userRemote is already in the list
				return;
			}
		}
		// userRemote not in the list
		this.userList.addElement(new ModelUserList(userRemote));
		System.out.println("[Model]List of remoteUsers:"+this.userList.toString());
		
	}

	
	
	public void removeUserRemote(User userRemote){
		User userTmp = null;
		System.out.println("[Model]remove userRemote Ip:"+userRemote.getIp());
		for(int i=0;i<this.userList.size();i++){
			userTmp = this.userList.getElementAt(i).getUser();
			if (userTmp.getIp().equals(userRemote.getIp())){
				// userRemote is already in the list
				this.userList.removeElementAt(i);

			}
		}
		System.out.println("[Model]List of remoteUsers:"+this.userList.toString());
	}

	
	
	public User getUserRemoteByName(String username){
		User userRemote = null;
		User userTmp = null;
		for(int i=0;i<this.userList.size();i++){
			userTmp = this.userList.getElementAt(i).getUser();
			if (userTmp.getUsername().equals(username)){
				userRemote = userTmp;
				break;
			}
		}
		return userRemote;
	}

	
	
	public User getUserRemoteByIP(InetAddress hostaddress){
		User userRemote = null;
		User userTmp = null;
		for(int i=0;i<this.userList.size();i++){
			userTmp = this.userList.getElementAt(i).getUser();
			if (userTmp.getIp().equals(hostaddress)){
				userRemote = userTmp;
				break;
			}
		}
		return userRemote;
	}
	
	
	
	public ModelUserList getModelUserRemoteByName(String username){
		ModelUserList userRemote = null;
		ModelUserList userTmp = null;
		for(int i=0;i<this.userList.size();i++){
			userTmp = this.userList.getElementAt(i);
			if (userTmp.getUser().getUsername().equals(username)){
				userRemote = userTmp;
				break;
			}
		}
		return userRemote;
	}

}
