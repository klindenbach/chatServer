package chatServer;

import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.annotation.WebServlet;

@WebServlet("/resetDB")
public class ResetDBServlet extends ChatServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected String handleGet(ChatRequest request, ChatResponse response) throws InvalidRequestException, SQLException {
		
		Statement st = _conn.createStatement();

		st.executeUpdate("DROP TABLE IF EXISTS messages");
		st.executeUpdate("DROP TABLE IF EXISTS conversationUsers");
		st.executeUpdate("DROP TABLE IF EXISTS conversations");
		st.executeUpdate("DROP TABLE IF EXISTS users");

		st.executeUpdate("CREATE TABLE conversations (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL)");
		st.executeUpdate("CREATE TABLE users (email VARCHAR(50) PRIMARY KEY NOT NULL)");
		st.executeUpdate("CREATE TABLE messages (body TEXT, timeSent INT(11), timeReceived INT(11), "
				+ "conversation INT, sender VARCHAR(50), "
				+ "FOREIGN KEY (conversation) REFERENCES conversations(id), "
				+ "FOREIGN KEY (sender) REFERENCES users(email))");
		st.executeUpdate("CREATE TABLE conversationUsers (user VARCHAR(50), conversation INT, "
				+ "FOREIGN KEY (user) REFERENCES users(email), "
				+ "FOREIGN KEY (conversation) REFERENCES conversations(id))");
		
		st.executeUpdate("INSERT INTO users VALUES (\"konrad@EKCHAT.com\")");
		st.executeUpdate("INSERT INTO users VALUES (\"emmanuel@EKCHAT.com\")");
		st.executeUpdate("INSERT INTO users VALUES (\"test@EKCHAT.com\")");
		
		st.executeUpdate("INSERT INTO conversations VALUES ()");
		
		st.executeUpdate("INSERT INTO conversationUsers VALUES (\"konrad@EKCHAT.com\", 1)");
		st.executeUpdate("INSERT INTO conversationUsers VALUES (\"emmanuel@EKCHAT.com\", 1)");
		
		st.executeUpdate("INSERT INTO messages VALUES (\"Hello World!\", UNIX_TIMESTAMP(NOW()), UNIX_TIMESTAMP(NOW()), "
				+ "1, \"konrad@EKCHAT.com\")");
		
		return "OK";
	}
}