package chatSystem.model;

import java.io.Serializable;
import java.util.Date;
import chatSystem.model.User;;
public class Message implements Serializable{

	    private final String message;
	    private final Date horoDate;
	    private final User sender;

	    public Message(String message, User transmitter)
	    {
	        this.message= message;
	        this.horoDate = new Date();
	        this.sender = transmitter;

	    }

	    public String getMessage()
	    {
	        return this.message;

	    }

	    public Date getHorodatation()
	    {
	        return this.horoDate;

	    }

	    public User getTransmitter() {
	        return transmitter;
	    }
	}
}
