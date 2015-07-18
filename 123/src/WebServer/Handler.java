package WebServer;

public interface Handler {

	public boolean match(Request request);

	public void handle(Request request, Response response);
}
