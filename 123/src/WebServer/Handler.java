package WebServer;

public interface Handler {

	public boolean match(Request request);

	public void handle(Request request, Response response) throws Exception;

	public void setFlag(boolean b);

	public void setBody(byte[] body);
}
