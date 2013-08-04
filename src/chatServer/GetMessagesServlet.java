package chatServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/getMessages")
public class GetMessagesServlet extends ChatServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected JSONObject handleGet(ChatRequest request, ChatResponse response) throws InvalidRequestException, SQLException {
		
		String query = "SELECT * FROM messages WHERE timeSent >= ?";
		PreparedStatement stmnt = _conn.prepareStatement(query);

		stmnt.setString(1, request.getRequiredParameter("after"));
		
		ResultSet rs = stmnt.executeQuery();
		
		JSONArray messageArray = new JSONArray();
        while (rs.next()) {
        	JSONObject message = new JSONObject();
        	message.put("message", rs.getString("body"));
        	message.put("sender", rs.getString("sender"));
        	messageArray.put(message);
        }
        JSONObject results = new JSONObject();
        results.put("messages", messageArray);
		
		return results;
	}
}