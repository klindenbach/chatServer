package chatServer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/getConversationID")
public class GetConversationIDServlet extends ChatServlet {
       
	private static final long serialVersionUID = 1L;

	@Override
	protected Object handleGet(ChatRequest request, ChatResponse response) throws InvalidRequestException, SQLException {

		String query = "SELECT * "
				     + "FROM conversations c "
				     + "WHERE EXISTS ("
				     + "              SELECT user"
				     + "              FROM conversationUsers"
				     + "              WHERE user = ? AND"
				     + "                    conversation = c.id"
				     + "             )"
				     + "      AND"
				     + "      EXISTS ("
				     + "              SELECT user"
				     + "              FROM conversationUsers"
				     + "              WHERE user = ? AND"
				     + "                    conversation = c.id"
				     + "             )";
		
		PreparedStatement stmnt = _conn.prepareStatement(query);
		stmnt.setString(1, request.getRequiredParameter("user1"));
		stmnt.setString(2, request.getRequiredParameter("user2"));

		ResultSet rs = stmnt.executeQuery();
		
        if (rs.next()) {
        	return rs.getString("id");
        }

		return "0";
	}
} 
