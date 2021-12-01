package com.upload;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB {
	public static void main(String args[]) throws Exception {
		Connection conn = null;
		Statement stat = null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","root");
		stat = conn.createStatement();
		String sql = "select * from xyz";
		ResultSet result = stat.executeQuery(sql);
		while(result.next()) {
			String col1 = result.getString("ajfnhga");
			int col2 = result.getInt("skdjnfoan");
			String col3 = result.getString("jfoaihg");
			System.out.print("col1:"+col1+",col2:"+col2+",col3:"+col3);
		}
		stat.close();
		conn.close();
	}
}
