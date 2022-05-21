package com.newsfeed.user.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconn {
	
	private static String url = "jdbc:oracle:thin:@db202204291622_high?TNS_ADMIN=/Users/rd/Desktop/Poonam/Wallet_DB202204291622/";
//	private static String url = "jdbc:oracle:thin:@(description= "
//			+ "(retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.us-sanjose-1.oraclecloud.com))"
//			+ "(connect_data=(service_name=gce3f73bc7bdf4e_db202204291622_high.adb.oraclecloud.com))"
//			+ "(security=(ssl_server_cert_dn=\"CN=adb.us-sanjose-1.oraclecloud.com, "
//			+ "OU=Oracle ADB SANJOSE, O=Oracle Corporation, L=Redwood City, ST=California, C=US\")))";
	private static String username = "admin";
    private static String password = "Newsfeed@2022";
    
	public static Connection getConn() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		return DriverManager.getConnection(url,username, password);
		
	}
	
}
