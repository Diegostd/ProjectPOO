package chatSystem.model;

import java.nio.file.Path;
import java.nio.file.Paths;


public class ModelUserList {
	private User user;
	private Path chatHistoryFile;
	
	
	public ModelUserList(User user){
		this.user = user;
		this.chatHistoryFile = Paths.get(this.user.getUsername()+"Hist.txt");
		//this.cleanChatHistory();
	}
	
	public User getUser(){
		return this.user;
	}
	
	public String toString(){
		return this.getUser().toString();
	}

}
