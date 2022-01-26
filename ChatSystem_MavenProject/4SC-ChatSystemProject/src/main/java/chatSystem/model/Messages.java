package chatSystem.model;

import java.io.Serializable;
import java.util.Date;
import chatSystem.model.User;
import java.sql.Timestamp;
public class Messages implements Serializable{

	    private final String message;
	    //private final Date horoDate;
	    //private final User sender;
	    private final String userPhone;
	    private Timestamp timer;
	    

	    public Messages(String message, String userPhone)
	    {
	    	this.timer = new Timestamp(System.currentTimeMillis());
	        this.message= message;
	        //this.horoDate = new Date();
	        this.userPhone = userPhone;
	    }
	    
	   
	    public Messages(String msg, String usrPhone, Timestamp timeStamp)
	    {
	    	timer = timeStamp;
			message = msg;
			userPhone = usrPhone;
	    }
	    
	    

	    public String getMessage()
	    {
	        return this.message;

	    }
	    
	    public Timestamp getTimerOfTheMessage() {
			return this.timer;
		}

	    public Date getHorodatation()
	    {
	        return this.horoDate;

	    }

	    public String getUserPhone() {
	        return userPhone;
	    }
	}
