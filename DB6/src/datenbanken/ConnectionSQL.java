package datenbanken;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {

	static String user;
	static String passwort;
	static public Connection con;
	
	
	
	public static void connect(String user, String passwort) throws ClassNotFoundException, SQLException{
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@ora14.informatik.haw-hamburg.de:1521:inf14", user, passwort);
		
		System.out.println("verbunden");
		
		

		GUISQL guimain = new GUISQL(con, "tabelle");
		
		guimain.build();
		
		
	}
	
	public static void disconnect(Connection con) throws SQLException{
		
		con.close();
		
	}
	
}
