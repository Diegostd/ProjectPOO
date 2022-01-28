package chatSystem.database;
import java.sql.*;  



public class MysqlCon {

	//Constructeur : utile pour utiliser les methodes de la classe en instanciant un objet de type MysqlCon 
	public MysqlCon() {
		// TODO Auto-generated constructor stub
	}
    
	//connectBDD() :  pour se connecter a  la base de donnees 
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
		
    //cette methode reseigne dans la BDD centrale un exchange de msg entre 2 utilisateurs une fois la connexion a la bdd etablie
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
	
	//cette methode affiche l'historique des messages exchanges
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
	
	//mÃ©thode qui permet de vider la BDD 
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
	
	//cette methode permet se desconnecter de la BDD 		
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
				
			
	    //**************************** affichage des elements de la bdd  *********************************//
        /*Statement stmt=conTest.createStatement();  
	    ResultSet rs = stmt.executeQuery("select * from users");  //avec users la table de notre base de donnÃ©es 
	    while(rs.next()) System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+ rs.getString(4));*/ 
			
			
		
		
	   
		
	} 	
}


//recv msg (partie udp) : soit une methode void, soit un state quot ou nbrs octets recus
//rcv bloquant --> 2 methodes differents pour l'envoi et la reception ou avec des **threads**

//appel agent --> init (execution) --> appel des methodes de la bdd 
//creer une table a 3 id : id emetteur, id recepteur, contenu du msg , optionnellement time stamp (utile pour la gestion de l'historique) --> voir comment ca se fait depuis java 


//je dois definir les cles primaires de mes tables 
//mettre a jour mes TCP sender et receiver 
// et ajouter les msgs dans la base de donnees 




