package hu.ak_akademia.cash_desk_main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLUtils {

	private static final String JDBC = "jdbc";
	private static final String DRIVER_MY_SQL = "mysql";
	private static final String HOST = "localhost";
	private static final String PORT_MY_SQL = "3306";
	private static final String DATABASE_MY_SQL = "cash_desks";
	private static final String USER_MY_SQL = "root";
	private static final String PASSWORD_MY_SQL = "12345";
	private static final String SWITCH = "rewriteBatchedStatements=true";

	public static final String URL_MYSQL = String.format("%s:%s://%s:%s/%s?%s", //
			JDBC, DRIVER_MY_SQL, HOST, PORT_MY_SQL, DATABASE_MY_SQL, SWITCH);

	private static final Properties PROPERTIES;

	static {
		PROPERTIES = new Properties();
		PROPERTIES.setProperty("user", USER_MY_SQL);
		PROPERTIES.setProperty("password", PASSWORD_MY_SQL);
	}

	public static String getDriverMySql() {
		return DRIVER_MY_SQL;
	}

	public static String getJdbc() {
		return JDBC;
	}

	public static String getSwitch() {
		return SWITCH;
	}

	public static Connection getMySQLConnection() throws SQLException {
		return DriverManager.getConnection(URL_MYSQL, PROPERTIES);
	}
}
