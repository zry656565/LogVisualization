package com.LogVisualization.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.LogVisualization.Bean.Source;
import com.LogVisualization.IDAO.ISourceDAO;
import com.LogVisualization.Utils.SV;

public class SourceDAO implements ISourceDAO {

	private static Connection conn;
	private Connection getConnection(){
		Connection con = null;
		try {
			//load jdbc driver
			Class.forName(SV.DB_DRIVER);
			String url = SV.DB_URL + SV.DB_SCHEME;
			con = DriverManager.getConnection(url, SV.DB_USERNAME, SV.DB_PASSWORD);
		} catch (Exception e) {
			System.out.println("DB Connect Failed:" + e.getMessage());
			return null;
		}
		return con;
	}
	
	@Override
	public int createSource(String name, String url, String state, String username) {
		conn = getConnection();
		if (conn==null) {
			return -1;
		}
		
		try {  
			//sql for insert source
			String sql = "INSERT INTO tb_source(name, url, states, username)" +
				"VALUES ('" + name + "', '"
	            			+ url + "', '"
	            			+ state + "', '"
	            			+ username +"');";
	              
			Statement st = (Statement) conn.createStatement();
			st.executeUpdate(sql);
			System.out.println("SQL EXECUTE: " + sql);
			conn.close();
		} catch (SQLException e) {  
			System.out.println("INSERT FAILED: " + e.getMessage());
			return -2;
		}
		return 0;
	}

	@Override
	public int removeSource(int id) {
		conn = getConnection();
		if (conn==null) {
			return -1;
		}
		
		try {  
			//sql for remove source
			String sql = "DELETE FROM tb_source where id = " + id + ";";
	              
			Statement st = (Statement) conn.createStatement();
			st.executeUpdate(sql);
			System.out.println("SQL EXECUTE: " + sql);
			conn.close();
		} catch (SQLException e) {  
			System.out.println("DELETE FAILED: " + e.getMessage());
			return -2;
		}
		return 0;
	}

	@Override
	public int modifySource(int id, String name, String url, String state) {
		conn = getConnection();
		if (conn==null) {
			return -1;
		}
		
		try {  
			//sql for modify source
			String sql = "UPDATE tb_source SET ";
			//null_flag used for judging whether the three arguments are all null
			boolean null_flag = false;
			if (name!=null) {
				sql += "name ='" + name + "' ";
				null_flag = true;
			}
			if (url!=null) {
				sql += "url ='" + url + "' ";
				null_flag = true;
			}
			if (state!=null) {
				sql += "states ='" + state +"' ";
				null_flag = true;
			}
			sql += "where id = " + id + ";";
			
			if (null_flag==false) {
				conn.close();
				return 0;
			}
	              
			Statement st = (Statement) conn.createStatement();
			st.executeUpdate(sql);
			System.out.println("SQL EXECUTE: " + sql);
			conn.close();
		} catch (SQLException e) {  
			System.out.println("UPDATE FAILED: " + e.getMessage());
			return -2;
		}
		return 0;
	}

	@Override
	public List<Source> querySource(String username) {
		conn = getConnection();
		List<Source> sourceList = new ArrayList<Source>();
		if (conn==null) {
			return null;
		}
		
		try {  
			//sql for query source
			String sql = "SELECT * from tb_source where username = '" + username + "';";
	              
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				sourceList.add(new Source(
						rs.getInt("id"),
						rs.getString("name"), 
						rs.getString("url"),
						rs.getString("states"),
						rs.getString("username")));
			}
			System.out.println("SQL EXECUTE: " + sql);
			conn.close();
		} catch (SQLException e) {  
			System.out.println("DELETE FAILED: " + e.getMessage());
			return null;
		}
		return sourceList;
	}

}
