package WebServer;

import java.io.IOException;



public interface Request {
	
	public String getUrl();
	
	public String getHead();
	
	public void parse() throws IOException ;
}
