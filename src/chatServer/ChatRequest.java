package chatServer;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ChatRequest extends HttpServletRequestWrapper {

	private String _postString;
	
	public ChatRequest(HttpServletRequest request) throws IOException {
		super(request);
		_postString = parsePost();
	}
	
	public String getPostString() {
		return _postString;
	}
	
	public String getRequiredParameter(String param) throws InvalidRequestException {

		String ret = getParameter(param);
		
		if (ret == null)
			throw new InvalidRequestException("Missing required parameter: " + param);
		
		return ret;
	}
	
	private String parsePost() throws IOException {
		StringBuffer jb = new StringBuffer();
		String line = null;
	    BufferedReader reader = getReader();
	    while ((line = reader.readLine()) != null)
	        jb.append(line);
	    
	    return jb.toString();
	}
}
