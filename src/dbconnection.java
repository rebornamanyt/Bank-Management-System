import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class dbconnection
{
	public Statement conmethod()
	{
		Statement stmt=null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");		//register ur driver
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sss?characterEncoding=UTF-8","root","12345678");
			stmt= con.createStatement();
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
			// TODO: handle exception
		}
		return stmt;
		
	}
	

}
