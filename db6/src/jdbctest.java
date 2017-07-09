import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class Jdbctest {

	public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException {
		String user = JOptionPane.showInputDialog("User");
		String passwort = JOptionPane.showInputDialog("Passwort");
		String select = JOptionPane.showInputDialog("Select: ");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@ora14.informatik.haw-hamburg.de:1521:inf14", user, passwort);

		Statement stmt = con.createStatement();
		ResultSet rset;
		ResultSet rset2;
		if (!select.equals("")) {
			rset = stmt.executeQuery("Select * from " + select);
			rset2 = stmt.executeQuery("SELECT * FROM " + select);
			
		}else{
			rset = stmt.executeQuery("Select TABLE_NAME from USER_TABLES");
			rset2 = stmt.executeQuery("Select TABLE_NAME from USER_TABLES");
			
		}
		
		ResultSetMetaData rsetmd = (ResultSetMetaData) rset.getMetaData();
		System.out.println(rsetmd.getColumnName(1));
		while (((ResultSet) rset).next()) {

			System.out.println(((ResultSet) rset).getString(1));
		}
		
	    // It creates and displays the table
	    JTable table = new JTable(buildTableModel(rset, rset2));

	    // Closes the Connection
	    JOptionPane.showMessageDialog(null, new JScrollPane(table));
		
		
		
		
		con.close();

		rset.close();
		stmt.close();

	}
	
	public static DefaultTableModel buildTableModel(ResultSet rs, ResultSet rs2)
	        throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    
	    
	    
	    while (rs2.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs2.getObject(columnIndex));
	        }
	        data.add(vector);
	    }
	    
	    return new DefaultTableModel(data, columnNames);

	}
	
}