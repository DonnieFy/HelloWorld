package WebServer;

import java.io.IOException;



public interface Request {
	
	public String getUrl();
	
	public String getHead(String mm);
	
	public String getMethod();
	
	public void proccess() throws IOException;
	
	public User getUser();

	public void setUrl(String string);
}
