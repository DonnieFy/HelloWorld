package WebServer;

import java.io.IOException;

public interface Response {

	public void send() throws IOException;
	
	public void sendHead();
	
}
