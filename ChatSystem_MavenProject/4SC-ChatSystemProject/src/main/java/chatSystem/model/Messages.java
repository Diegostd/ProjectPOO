package chatSystem.model;

import java.io.Serializable;
import java.sql.Timestamp;
public class Messages implements Serializable{

	    private final String message;
	    private final String userPhone;
	    private Timestamp timeOfMessage;
	    

	    public Messages(String message, String userPhone)
	    {
	    	this.timeOfMessage = new Timestamp(System.currentTimeMillis());
	        this.message= message;
	        //this.horoDate = new Date();
	        this.userPhone = userPhone;
	    }
	    
	   
	    public Messages(String msg, String usrPhone, Timestamp timeStamp)
	    {
	    	timeOfMessage = timeStamp;
			message = msg;
			userPhone = usrPhone;
	    }
	    
	    

	    public String getMessage()
	    {
	        return this.message;

	    }

	    public String getUserPhone() {
	        return userPhone;
	    }
	    
	    public Timestamp getTimestamp() {
			return timeOfMessage;
		}

		public String getSenderId() {
			return userPhone;
		}
	}
