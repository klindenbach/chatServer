package chatServer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/getMessages")
public class GetMessagesServlet extends ChatServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected JSONObject handleGet(HttpServletRequest request, HttpServletResponse response) throws InvalidRequestException, SQLException {
		
		Statement st = _conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM messages");
		
		JSONArray messageArray = new JSONArray();
        while (rs.next()) {
        	JSONObject message = new JSONObject();
        	message.put("message", rs.getString("message"));
        	messageArray.put(message);
        }
        JSONObject results = new JSONObject();
        results.put("messages", messageArray);
		
		return results;
	}
}