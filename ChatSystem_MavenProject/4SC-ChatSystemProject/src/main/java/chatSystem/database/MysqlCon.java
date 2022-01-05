package chatSystem.database;
import java.sql.*;  

public class MysqlCon {

	/*public MysqlCon() {
		// TODO Auto-generated constructor stub
	}*/

	
	// les identifiants : login : tp_servlet_012 password : Thi0zaes
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
		    "jdbc:mysql://srv-bdens:3306/tp_servlet_012","tp_servlet_012","Thi0zaes");  
			//here tp_servlet_012 is database name, tp_servlet_012 is username and  Thi0zaes password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from users");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+ rs.getString(4));  
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
			}  

	} 

	
