package dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Abstract class for DAO instances to connect to database.
 * @author William Kofod
 *
 */
public abstract class BaseDAO {
	private DataSource ds;
	public final int ALREADY_EXISTS = -1;
	public final int SUCCESS = 1;
	public final int FAILURE = 0;
	
	/**
	 * Get a connection to the database.
	 * Tries to connect three times before
	 * catching Exception.
	 * @return Connection
	 */
	protected Connection getConnection() {
		try {
			Context cxt = new InitialContext();
			ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/DispatchDB"); 
			Connection con = ds.getConnection();
			return con;
		} catch (Exception e) {
			try {
				Context cxt = new InitialContext();
				ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/DispatchDB"); 
				Connection con = ds.getConnection();
				return con;
			} catch (Exception ex) {
				try {
					Context cxt = new InitialContext();
					ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/DispatchDB"); 
					Connection con = ds.getConnection();
					return con;
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		}

        return null;
	}
}
