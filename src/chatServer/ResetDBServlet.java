package chatServer;

import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/resetDB")
public class ResetDBServlet extends ChatServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected String handleGet(HttpServletRequest request, HttpServletResponse response) throws InvalidRequestException, SQLException {
		
		Statement st = _conn.createStatement();
		st.executeUpdate("DROP TABLE IF EXISTS messages");
		st.executeUpdate("CREATE TABLE messages (message TEXT)");
		
		return "OK";
	}
}