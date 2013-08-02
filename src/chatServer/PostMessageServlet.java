package chatServer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/postMessage")
public class PostMessageServlet extends ChatServlet {
       
	private static final long serialVersionUID = 1L;

	@Override
	protected void handlePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String query = "INSERT INTO messages VALUES (?)";
		
		PreparedStatement stmnt = _conn.prepareStatement(query);
		stmnt.setString(1, _postString);
		stmnt.executeUpdate();
	}
} 
