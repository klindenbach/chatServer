package chatServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/resetDB")
public class ResetDBServlet extends ChatServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Statement st = _conn.createStatement();
			st.executeUpdate("DROP TABLE IF EXISTS messages");
			st.executeUpdate("CREATE TABLE messages (message TEXT)");

			printSuccess(response, "OK");
		} 
		catch (SQLException e) {
			printError(response, e);
		} 
	}
}