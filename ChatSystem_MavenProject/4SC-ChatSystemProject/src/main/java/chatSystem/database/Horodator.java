package chatSystem.database;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Horodator {
	
	
	public Horodator() {
		// TODO Auto-generated constructor stub
	}
	
	//retourne le timestamp du message - exemple à l'affichage : mar. 18 janv. 14:51:19 
	public static String horodateMsg() {
		
		 Date  date = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("E dd MMM HH:mm:ss");
		 String readyDate = sdf.format(date);
		 
		 return readyDate;			
	}
	
}
