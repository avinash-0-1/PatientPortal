package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Date:1/31/2018 This is class which handles the database connections.
 * 
 * @author KK055944 version:0.0.1
 *
 */
public class DataBaseConnection {
	/**
	 * This method will connect to database
	 * 
	 * @return returning the database connection properties.
	 */
	final static Logger logger = Logger.getLogger(DataBaseConnection.class);

	public Connection getConnection() {
		// Initializing connection variable to connect db.
		Connection con = null;
		/**
		 * Using Properties class to fetch the database information
		 */
		Properties props = new Properties();
		InputStream inputStream = null;
		try {
			logger.info("Connecting to DataBase");
			// Using InputStream to fetch the data from properties file.
			inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
			props.load(inputStream);
			/**
			 * @getsProperty method will return properties mentioned in properties file.
			 */
			String driver = props.getProperty("jdbc.driver");
			if (driver != null) {
				Class.forName(driver);
			}
			String url = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			/**
			 * getConnection method use the database properties from file and stored in
			 * local variables and will be used while database connection.
			 */
			con = DriverManager.getConnection(url, username, password);
			logger.info("Connected to DataBase");
		} catch (Exception e) {
			logger.debug("Caught exception in DataBase class:" + e.getMessage());
		}
		return con;
	}

}
