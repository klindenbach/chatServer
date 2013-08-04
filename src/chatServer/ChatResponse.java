package chatServer;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.json.JSONObject;

public class ChatResponse extends HttpServletResponseWrapper  {

	public ChatResponse(HttpServletResponse response) {
		super(response);
	}
	
	public void printSuccess(Object msg) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("msg", msg);
		obj.put("status", "200");
		
		getWriter().print(obj);
		setStatus(200);
		setContentType("text/json");
	}
	
	public void printError(Exception e) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("msg", e.getMessage());
		
		int status = 400;

		if (e instanceof SQLException)
			status = 500;
		
		obj.put("status", status);
		
		getWriter().print(obj);
		setStatus(status);		
		setContentType("text/json");
		
		e.printStackTrace();
	}
}
