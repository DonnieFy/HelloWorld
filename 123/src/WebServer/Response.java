package WebServer;

import java.io.IOException;

public interface Response {

	public void send(String pathname) throws IOException;
	
	public void sendHead(String str);
	
}
