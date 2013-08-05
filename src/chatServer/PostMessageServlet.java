package chatServer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/postMessage")
public class PostMessageServlet extends ChatServlet {
       
	private static final long serialVersionUID = 1L;

	@Override
	protected void handlePost(ChatRequest request, ChatResponse response) throws SQLException, IOException, InvalidRequestException {
		String query = "INSERT INTO messages (body, sender, timeSent, timeReceived, conversation)"
				+ " VALUES (?, ?, ?, UNIX_TIMESTAMP(NOW()), ?)";
		
		PreparedStatement stmnt = _conn.prepareStatement(query);
		stmnt.setString(1, request.getPostString());
		stmnt.setString(2, request.getRequiredParameter("sender"));
		stmnt.setString(3, request.getRequiredParameter("timeSent")); 
		stmnt.setString(4, request.getRequiredParameter("conversation"));
		stmnt.executeUpdate();
	}
} 
