package chatSystem.database;
import java.sql.*;  



public class MysqlCon {

	//Constructeur : utile pour utiliser les méthodes de la classe en instanciant un objet de type MysqlCon 
	public MysqlCon() {
		// TODO Auto-generated constructor stub
	}
    
	//connectBDD() :  pour se connecter à la base de données 
	public static java.sql.Connection connectBDD() {
		
		java.sql.Connection con = null; 
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		con =DriverManager.getConnection(  
	    "jdbc:mysql://srv-bdens:3306/tp_servlet_012","tp_servlet_012","Thi0zaes"); 
		//here tp_servlet_012 is database name, tp_servlet_012 is username and  Thi0zaes password  
		}		
		catch(Exception e){ System.out.println(e);}
		
		return con;			
	}
		
    //cette méthode reseigne dans la BDD centrale un échange de msg entre 2 utilisateurs une fois la connexion à la bdd établie 
	public void addHistoryLine(java.sql.Connection con,String senderID, String receiverID, String msgContent, String timeStamp) {
		String query1 = "insert into history (senderID, receiverID, msgContent, timeStamp) "+"values (?,?,?,?)"; 		
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(query1);
			preparedStmt.setString (1,senderID); 
			preparedStmt.setString (2,receiverID); 
			preparedStmt.setString (3,msgContent); 
			preparedStmt.setString (4,timeStamp); 
			preparedStmt.execute(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}
	
	//cette méthode affiche l'historique des messages échangés 
	public void displayHistory(java.sql.Connection con) {
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs= stmt.executeQuery("select * from history");
			while(rs.next()) { 
				System.out.println("senderID: " + rs.getString(1)+" receiverID: "+rs.getString(2)+" msgContent: "+rs.getString(3)+" timeStamp: "+rs.getString(4));  		
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 			
	}
	
	//méthode qui permet de vider la BDD 
	/*public void clearHistory(java.sql.Connection con) {
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("truncate table history;");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 			
	}*/
	
	//cette méthode permet se déconnecter de la BDD 		
	public void disconnectBDD(java.sql.Connection con) {
		try {
			con.close(); 
		}
		catch(Exception e){ System.out.println(e);}
	}
	
	
	
	public static void main(String[] args) {
		
		MysqlCon BDD = new MysqlCon(); 
		java.sql.Connection conTest = BDD.connectBDD(); 
		//BDD.addHistoryLine(conTest,"Lola", "Lili","Coucou","18h"); 
		BDD.displayHistory(conTest);
		BDD.disconnectBDD(conTest);
				
			
	    //**************************** affichage des éléments de la bdd  *********************************//
        /*Statement stmt=conTest.createStatement();  
	    ResultSet rs = stmt.executeQuery("select * from users");  //avec users la table de notre base de données 
	    while(rs.next()) System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+ rs.getString(4));*/ 
			
			
		
		
	   
		
	} 	
}


//recv msg (partie udp) : soit une méthode void, soit un state quot ou nbrs octets reçus 
//rcv bloquant --> 2 méthodes différentes pour l'envoi et la réception ou avec des **threads**

//appel agent --> init (exécution) --> appel des méthodes de la bdd 
//créer une table à 3 id : id emetteur, id recepteur, contenu du msg , optionnellement time stamp (utile pour la gestion de l'historique) --> voir comment ça se fait depuis java 


//je dois définir les clés primaires de mes tables 
//mettre à jour mes TCP sender et receiver 
// et ajouter les msgs dans la base de données 




