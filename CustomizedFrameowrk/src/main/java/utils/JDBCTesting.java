package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTesting {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6589229",
				"sql6589229", "7YHp4P78Fg");

		Statement statement = connection.createStatement();
		
		int resultSet = statement.executeUpdate("drop table user");

		resultSet = statement.executeUpdate(
				"create table user(id int UNSIGNED primary key not null auto_increment, name varchar(100), email varchar(100))");
		
		resultSet = statement.executeUpdate("insert into user(name,email)values('javatpoint','java@javatpoint.com')");
		System.out.println("Result Set : " + resultSet);

		resultSet = statement.executeUpdate("insert into user(name,email)values('jalandhar','jalandhar@gmail.com')");
		
		ResultSet resultset = statement.executeQuery("select * from user");
		
		while (resultset.next()) {
			System.out.println("Name : " + resultset.getString("name"));
			System.out.println("email : " + resultset.getString("email"));
		}
	}

}
