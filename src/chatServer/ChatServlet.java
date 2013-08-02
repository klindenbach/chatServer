package chatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class ChatServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected Connection _conn;

	public ChatServlet() {
		String url = "jdbc:mysql://localhost:3306/chatServer";
		String user = "root";
		String password = "root";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			_conn = DriverManager.getConnection(url, user, password);
		}
		catch (Exception e) { e.printStackTrace(); }
	}
	
	public void printSuccess(HttpServletResponse response, String msg) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("msg", msg);
		obj.put("code", "200");
		
		response.getWriter().print(obj);
		response.setStatus(200);
		response.setContentType("text/json");
	}
	
	public void printError(HttpServletResponse response, Exception e) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("msg", e.getMessage());
		obj.put("code", "500");
		
		response.getWriter().print(obj);
		response.setStatus(500);		
		response.setContentType("text/json");
	}
	
	public JSONObject parsePost(HttpServletRequest request) throws IOException {
		StringBuffer jb = new StringBuffer();
		String line = null;
	    BufferedReader reader = request.getReader();
	    while ((line = reader.readLine()) != null)
	        jb.append(line);
	    
	    return new JSONObject(jb.toString());
	}
}
