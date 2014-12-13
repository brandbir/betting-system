package betting.models;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import betting.helper.LogFile;

public class ConnectionPool
{
	private static DataSource dataSource = null;
	
	public static Connection getConnection()
	{
		Connection con = null;
		
		try
		{
			if(dataSource == null)
			{
				Context context = (Context) new InitialContext().lookup("java:comp/env");
				dataSource = ((DataSource) context.lookup("jdbc/TEST"));
			}
			
			con = dataSource.getConnection();
		}
		catch(NamingException e)
		{
			LogFile.logError("ConnectionPool.getConnection() - " + e.getMessage());
		}
		catch (SQLException e)
		{
			LogFile.logError("ConnectionPool.getConnection() - " + e.getMessage());
		}
		
		return con;
	}
}
