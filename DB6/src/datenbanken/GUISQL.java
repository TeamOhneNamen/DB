package datenbanken;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GUISQL{

	static JFrame jf = new JFrame("Select");
	
	static Connection con;
	static String art;
	
	
	static ResultSet rset;
	static ResultSet rset2;
	static Statement stmt;
	
	static JTable table;
	static JButton jbtn_ganz = new JButton("Ganzer SQL befehl");
	static JButton jbtn_new = new JButton("Andere Tabelle");
	static JScrollPane jscpane;
	
	static JLabel lbl_nix = new JLabel("");
	
	public GUISQL(Connection con, String art){
		
		this.con = con;
		this.art = art;
		
	}
	
	static String select;


	public static void build() throws SQLException{
		
		tableMeker(con);
		
		eigenschaften();
		
		zuweisungen();
		
		
		rset.close();
		rset2.close();
		stmt.close();
	}
	
	private static void tableMeker(Connection con) throws SQLException{
	
		if (art.equals("befehl")) {
			
			select = JOptionPane.showInputDialog("Befehl:");
			
			stmt = con.createStatement();
		
			rset = stmt.executeQuery(select);
			rset2 = stmt.executeQuery(select);
			
		} else {

			select = JOptionPane.showInputDialog("Tabelle:");
			
			stmt = con.createStatement();
			
			if (!select.equals("")) {
				rset = stmt.executeQuery("Select * from " + select);
				rset2 = stmt.executeQuery("SELECT * FROM " + select);
				
			}else{
				rset = stmt.executeQuery("Select TABLE_NAME from USER_TABLES");
				rset2 = stmt.executeQuery("Select TABLE_NAME from USER_TABLES");
				
			}
			
		}
		
		
	    // It creates and displays the table
	    table = new JTable(buildTableModel(rset, rset2));
	    jscpane = new JScrollPane(table);
	}
	
	
	private static void eigenschaften() {
		jf.setSize(900, 600);
		jf.setVisible(true);
		
		jscpane.setBounds(10, 10, 700, 540);
		jbtn_new.setSize(155, 20);
		jbtn_new.setLocation(720, 530);

		jbtn_ganz.setSize(155, 20);
		jbtn_ganz.setLocation(720, 500);
		
		
		jbtn_new.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				GUISQL new_search = new GUISQL(con, "tabelle");
				try {
					new_search.build();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		jbtn_ganz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				GUISQL new_search = new GUISQL(con, "befehl");
				try {
					new_search.build();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		
	}

	private static void zuweisungen() {
		
		// Closes the Connection
	    jf.add(jscpane);
	    jf.add(jbtn_new);
	    jf.add(jbtn_ganz);
		
	    jf.add(lbl_nix);
	    
	}
	private static DefaultTableModel buildTableModel(ResultSet rs, ResultSet rs2)
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
