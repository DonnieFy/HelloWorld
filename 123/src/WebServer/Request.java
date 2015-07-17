package WebServer;

import java.io.IOException;



public interface Request {
	
	public String getUrl();
	
	public String getHead(String mm);
	
	public String getMethod();
	
}
