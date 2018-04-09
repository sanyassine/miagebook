package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import persistence.connection.Oracle;

public abstract class DataMapper {
	protected Connection c;
	public DataMapper () {
		c = Oracle.getConnection();
	}
	
}
