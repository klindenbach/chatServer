package chatServer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	
	protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ChatResponse chatResponse = new ChatResponse(response);
		try {
			Object msg = handleGet(new ChatRequest(request), chatResponse);
			chatResponse.printSuccess(msg);
		} 
		catch (Exception e) {
			chatResponse.printError(e);
		} 
	}

	protected final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ChatResponse chatResponse = new ChatResponse(response);
		try {
			handlePost(new ChatRequest(request), chatResponse);
			chatResponse.printSuccess("OK");
		} 
		catch (Exception e) {
			chatResponse.printError(e);
		} 
	}
	
	protected void handlePost(ChatRequest request, ChatResponse response) throws InvalidRequestException, SQLException, IOException {
		throw new InvalidRequestException("Unimplimented");
	}	
	
	protected Object handleGet(ChatRequest request, ChatResponse response) throws InvalidRequestException, SQLException {
		throw new InvalidRequestException("Unimplimented");
	}
}
