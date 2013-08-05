package chatServer;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ChatListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
        // This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } 
            catch (SQLException e) {
                System.out.println("Error deregistering driver " + driver);
            }
        }
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	}
}