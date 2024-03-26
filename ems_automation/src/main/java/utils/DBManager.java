package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBManager {
	
	private static DBManager dbManager;
	private Connection connection;
	public Logger logger = LogManager.getLogger("AppLogger");
	
	private DBManager() {
		// TODO Auto-generated constructor stub
		ConfigFileReader configfile = new ConfigFileReader();
		try {
			if(connection == null) {
				Class.forName(configfile.getMysqlDriverName());
				connection = DriverManager.getConnection(configfile.getMysqlConnectionURL(), 
														configfile.getMysqlUserName(), 
														configfile.getMysqlPassword());
			}
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	public static DBManager getInstance() {
		// TODO Auto-generated method stub
		if(dbManager != null)
			return dbManager;
		else
			return new DBManager();
	}
	
	public Connection getDBConnection() {
		return connection;
	}
	
	public void closeDBConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("Exception in getting DB Connection closed {}", e.getMessage());
		}
	}

}
