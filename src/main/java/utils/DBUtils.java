package utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtils {
	public static Connection makeConnection() throws NamingException, SQLException {
		Context context = new InitialContext();
		Context tomcatCtx = (Context) context.lookup("java:comp/env");
		DataSource ds = (DataSource) tomcatCtx.lookup("Haircut");
		if (ds != null) {
			Connection con = ds.getConnection();
			return con;
		}
		return null;
		
	}
}
