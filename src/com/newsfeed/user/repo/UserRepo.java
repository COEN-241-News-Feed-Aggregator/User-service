package com.newsfeed.user.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.newsfeed.user.conn.DBconn;
import com.newsfeed.user.models.User;
import com.newsfeed.user.models.UserLoginRequest;

@Component
public class UserRepo {
	
//	@Value("${spring.datasource.username}")
//	private String userName;
//
//	@Value("${spring.datasource.password}")
//	private String password;
//
//	@Value("${spring.datasource.url}")
//	private String serverName;

	/** The name of the table which stores book details */
	private final String user_tableName = "USERS";



	public boolean createUser(User user) {

//		java.util.Date dt = new java.util.Date();
//		java.text.SimpleDateFormat sdf =
//		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String currentTime = sdf.format(dt);
		try {
			String insertString = "INSERT INTO " +user_tableName+ "(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)";
			System.out.println(insertString);
			Connection c = DBconn.getConn();
			PreparedStatement ps = c.prepareStatement(insertString);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmailId());
			ps.setString(4, user.getPassword());
			ps.execute();
			ps.close();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	return true;
	}
	
	public Integer loginUser(UserLoginRequest loginRequest) {
		try {
			String selectString = "SELECT * FROM " +user_tableName+ " WHERE EMAIL = ? AND PASSWORD = ? ";
			System.out.println(selectString);
			Connection c = DBconn.getConn();
			PreparedStatement ps = c.prepareStatement(selectString);
			ps.setString(1, loginRequest.getEmailId());
			ps.setString(2, loginRequest.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("ID");
			}
			return -1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return -1;
		} 
	}

}
