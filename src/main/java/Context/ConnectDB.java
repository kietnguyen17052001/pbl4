package Context;

import java.sql.*;

public class ConnectDB {
	private String url = "jdbc:sqlserver://THANHDUONG\\SQLEXPRESS; databaseName=PBL4";
	private String username = "sa";
	private String password = "29032001";

	public ConnectDB() {

	}

	// connect to database: PBL4 - MSSQL SERVER
	public Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, username, password);
	}
}
