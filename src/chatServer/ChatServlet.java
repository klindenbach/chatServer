package chatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class ChatServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected Connection _conn;
	protected String _postString;

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
	
	public void printSuccess(HttpServletResponse response, Object msg) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("msg", msg);
		obj.put("status", "200");
		
		response.getWriter().print(obj);
		response.setStatus(200);
		response.setContentType("text/json");
	}
	
	public void printError(HttpServletResponse response, Exception e) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("msg", e.getMessage());
		
		int status = 400;

		if (e instanceof SQLException)
			status = 500;
		
		obj.put("status", status);
		
		response.getWriter().print(obj);
		response.setStatus(status);		
		response.setContentType("text/json");
		
		e.printStackTrace();
	}
	
	public String parsePost(HttpServletRequest request) throws IOException {
		StringBuffer jb = new StringBuffer();
		String line = null;
	    BufferedReader reader = request.getReader();
	    while ((line = reader.readLine()) != null)
	        jb.append(line);
	    
	    return jb.toString();
	}
	
	protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Object msg = handleGet(request, response);
			printSuccess(response, msg);
		} 
		catch (Exception e) {
			printError(response, e);
		} 
	}

	protected final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			_postString = parsePost(request);
			handlePost(request, response);
			printSuccess(response, "OK");
		} 
		catch (Exception e) {
			printError(response, e);
		} 
	}
	
	protected void handlePost(HttpServletRequest request, HttpServletResponse response) throws InvalidRequestException, SQLException, IOException {
		throw new InvalidRequestException("Unimplimented");
	}	
	
	protected Object handleGet(HttpServletRequest request, HttpServletResponse response) throws InvalidRequestException, SQLException {
		throw new InvalidRequestException("Unimplimented");
	}
}
