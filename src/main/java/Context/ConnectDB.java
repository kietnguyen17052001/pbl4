package Context;

import java.sql.*;

public class ConnectDB {
	private String url = "jdbc:sqlserver://DANGKIET\\SQLEXPRESS; databaseName=PBL4";
	private String username = "kiet";
	private String password = "5071";

	public ConnectDB() {

	}

	// connect to database: PBL4 - MSSQL SERVER
	public Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, username, password);
	}
}
