package WebServer;


public interface Response {
	
	public void setStatus(int status);
	
	public void addHeader(String key, String value);
	
	public void addBody(byte[] body);
	
	public void send();
	
}
