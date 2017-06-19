import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

class jdbctest {

	public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException {
		String user = JOptionPane.showInputDialog("User");
		String passwort = JOptionPane.showInputDialog("Passwort");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@ora14.informatik.haw-hamburg.de:1521:inf14",user, passwort);

		Statement stmt = con.createStatement();
		Connection rset = (Connection) stmt.executeQuery("Select Wohnort from Kunden");
		ResultSetMetaData rsetmd = (ResultSetMetaData) rset.getMetaData();
		System.out.println(rsetmd.getColumnName(1));
		while (((ResultSet) rset).next()) {
			System.out.println(((ResultSet) rset).getString(1));
		}
		con.close();
	}
}