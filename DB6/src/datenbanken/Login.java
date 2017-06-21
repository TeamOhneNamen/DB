package datenbanken;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {

	static JFrame jf = new JFrame("Login");
	
	static JLabel jl_user = new JLabel("ABC-Kennung");
	static JLabel jl_passwort = new JLabel("Passwort");
	
	static JTextField jt_ABC = new JTextField();
	static JPasswordField jt_passwort = new JPasswordField();
	
	static JLabel jl_nix = new JLabel("");

	static String abcKennung;
	static String passwort;
	
	static JButton jbtn_accept = new JButton("Accept");
	
	public static void build(){
		
		String[] res = new String[2];
		
		eigenschaften();
		zuweisungen();
		
		
	}
	
	private static void eigenschaften(){
		
		jf.setVisible(true);
		jf.setBounds(100, 100, 300, 200);
		
		jl_user.setBounds(10, 20, 100, 20);
		jl_passwort.setBounds(10, 50, 100, 20);
		
		jt_ABC.setBounds(180, 20, 90, 20);
		jt_passwort.setBounds(180, 50, 90, 20);
		
		jbtn_accept.setBounds(180, 90, 90, 20);
		
		
		jbtn_accept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				abcKennung = jt_ABC.getText();
				
				char[] neuespasswort = jt_passwort.getPassword();
				
				passwort = new String(neuespasswort);
				
				jf.setVisible(false);
				
				ConnectionSQL csql = new ConnectionSQL();
				try {
					csql.connect(abcKennung, passwort);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
	}
	
	private static void zuweisungen(){
		
		jf.add(jl_passwort);
		jf.add(jl_user);
		jf.add(jt_ABC);
		jf.add(jt_passwort);
		jf.add(jbtn_accept);
		
		jf.add(jl_nix);
		
	}
	
}
