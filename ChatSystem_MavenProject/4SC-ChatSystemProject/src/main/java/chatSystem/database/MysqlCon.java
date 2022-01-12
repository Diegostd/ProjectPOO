package chatSystem.database;
import java.sql.*;  



public class MysqlCon {

	/*public MysqlCon() {
		// TODO Auto-generated constructor stub
	}*/
   
	
	public static void main(String[] args) {
			
		//****************************étape 1 : établissement de la connexion entre BDD et java *********************************//
		// TODO Auto-generated method stub
		// les identifiants : login : tp_servlet_012 password : Thi0zaes
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			java.sql.Connection con=DriverManager.getConnection(  
		    "jdbc:mysql://srv-bdens:3306/tp_servlet_012","tp_servlet_012","Thi0zaes");  
			//here tp_servlet_012 is database name, tp_servlet_012 is username and  Thi0zaes password  
			
			
			//**************************** affichage des éléments de la bdd  *********************************//
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from users");  //avec users la table de notre base de données 
			while(rs.next()) System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+ rs.getString(4));  
			
			
			
			//**************************** création de table et insertion de données  ************************//
			/*String query0 ="create table history (senderID varchar(20),receiverID varchar(20),msgContent varchar(500),timeStamp varchar(50))"; 
			PreparedStatement preparedStmt0 = con.prepareStatement(query0);
			preparedStmt0.execute();*/
			String query1 = "insert into history (senderID, receiverID, msgContent, timeStamp) "+"values (?,?,?,?)"; 
			
			
			
			//création jeux de données 			
			PreparedStatement preparedStmt = con.prepareStatement(query1); 
			preparedStmt.setString (1,"Loulou"); 
			preparedStmt.setString (2,"Lola"); 
			preparedStmt.setString (3,"Coucou !"); 
			preparedStmt.setString (4,"18h:24-10/12/2022"); 
			preparedStmt.execute(); //execution du prepared statement 
			
			Statement stmt1 = con.createStatement(); 
			ResultSet rs1 = stmt.executeQuery("select * from history");
			
			
			while(rs1.next()) { 
				System.out.println("senderID: " + rs1.getString(1)+" receiverID: "+rs1.getString(2)+"msgContent : "+rs1.getString(3)+"timeStamp "+rs1.getString(4));  		
			}
			
			con.close(); 
			
			}catch(Exception e){ System.out.println(e);}   
		
	    //appel agent --> init (exécution) --> appel des méthodes de la bdd 
		//créer une table à 3 id : id emetteur, id recepteur, contenu du msg , optionnellement time stamp (utile pour la gestion de l'historique) --> voir comment ça se fait depuis java 
		
	} 	
}


//recv msg (partie udp) : soit une méthode void, soit un state quot ou nbrs octets reçus 
//rcv bloquant --> 2 méthodes différentes pour l'envoi et la réception ou avec des **threads**


//je dois définir les clés primaires de mes tables 
//mettre à jour mes TCP sender et receiver 
// et ajouter les msgs dans la base de données 




