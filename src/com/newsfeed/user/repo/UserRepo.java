package com.newsfeed.user.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.newsfeed.user.models.Topic;
import com.newsfeed.user.models.User;
import com.newsfeed.user.models.UserLoginRequest;
import com.newsfeed.user.models.UserTopics;

@Component
@PropertySource("classpath:/application.properties")
public class UserRepo {
	
	@Value("${spring.datasource.url:jdbc:oracle:thin:@db202204291622_high?TNS_ADMIN=/Users/rd/Desktop/Poonam/Wallet_DB202204291622/}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;
	
	public Connection getConn() throws ClassNotFoundException, SQLException {
		return DriverManager.getConnection(url,username, password);
		
	}
	

	public int createUser(User user) {
		
		Connection c = null;
		try {
			String selectString = "SELECT ID FROM USERS WHERE EMAIL = ? ";
			String insertString = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)";
			System.out.println(insertString);
			c = getConn();
			c.setAutoCommit(false);
			System.out.println(user.getFirstName());
			PreparedStatement ps = c.prepareStatement(selectString);
			ps.setString(1, user.getEmailId());
			PreparedStatement ps1 = c.prepareStatement(insertString);
			ps1.setString(1, user.getFirstName());
			ps1.setString(2, user.getLastName());
			ps1.setString(3, user.getEmailId());
			ps1.setString(4, user.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return -1;
			}
			System.out.println("Checked id");
			ps1.executeUpdate();
			System.out.println("Created");
			
			ps.setString(1, user.getEmailId());
			ResultSet rs1 = ps.executeQuery();
			if(rs1.next()) {
				System.out.println("Checking again");
				int userID = rs1.getInt("ID");
				c.commit();
				return userID;
			}
			
			return -1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return -1;
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Integer loginUser(UserLoginRequest loginRequest) {
		Connection c = null;
		try {
			String selectString = "SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?" ;
			System.out.println(selectString);
			c = getConn();
			PreparedStatement ps = c.prepareStatement(selectString);
			ps.setString(1, loginRequest.getEmailId());
			ps.setString(2, loginRequest.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getInt("ID"));
				return rs.getInt("ID");
			}
			return -1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return -1;
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Topic> getAllTopics() {
		Connection c = null;
		List<Topic> result = new ArrayList<>();
		try {
			String selectString = "SELECT * FROM TOPICS";
			System.out.println(selectString);
			c = getConn();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(selectString);
			while(rs.next()) {
				result.add(new Topic(rs.getInt("ID"), rs.getString("NAME")));
			}
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return result;
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
	
	public List<Topic> getUserTopics(int userId) {
		Connection c = null;
		List<Topic> result = new ArrayList<>();
		try {
			String selectString = "SELECT * FROM TOPICS WHERE ID IN (SELECT TOPIC_ID FROM USER_TOPIC_MAPPING WHERE USER_ID = ?)";
			System.out.println(selectString);
			c = getConn();
			PreparedStatement ps = c.prepareStatement(selectString);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(new Topic(rs.getInt("ID"), rs.getString("NAME")));
			}
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return result;
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
	
	public boolean followTopic(UserTopics userTopics) {
		Connection c = null;
		try {
			String deleteString = "DELETE FROM USER_TOPIC_MAPPING WHERE USER_ID = ? AND EXISTS (SELECT * FROM USER_TOPIC_MAPPING WHERE USER_ID = ? )";
			System.out.println(deleteString);
			c = getConn();
			c.setAutoCommit(false);
			PreparedStatement ps = c.prepareStatement(deleteString);
			ps.setInt(1, userTopics.getUserId());
			ps.setInt(2, userTopics.getUserId());
			ps.executeUpdate();
			String insertString = "INSERT INTO USER_TOPIC_MAPPING(USER_ID, TOPIC_ID) VALUES (?, ?) ";
			System.out.println(insertString);
			PreparedStatement ps2 = c.prepareStatement(insertString);
			ps2.setInt(1, userTopics.getUserId());
			System.out.println("Topics");
			for(Topic t: userTopics.getUserTopics()) {
				System.out.println(t.getTopicId());
				ps2.setInt(2, t.getTopicId());
				ps2.executeUpdate();
			}
			c.commit();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}

}
