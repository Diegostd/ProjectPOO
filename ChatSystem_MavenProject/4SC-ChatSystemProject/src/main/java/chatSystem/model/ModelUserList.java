package chatSystem.model;

import java.nio.file.Path;
import java.nio.file.Paths;


public class ModelUserList {
	private User user;
	//Variable under construction
	private Path chatHistoryFile;
	
	//We create the list constructor
	public ModelUserList(User user){
		this.user = user;
		this.chatHistoryFile = Paths.get(this.user.getUsername()+"Hist.txt");
		//this.cleanChatHistory();
	}
	
	public User getUser(){
		return this.user;
	}
	
	//Fonction only for utility, not used yet
	public String toString(){
		return this.getUser().toString();
	}

}
