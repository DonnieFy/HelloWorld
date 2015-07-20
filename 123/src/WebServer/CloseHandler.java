package WebServer;

public class CloseHandler implements Handler{

	public static boolean match(WebRequest request) {
		return "close".equals(request.getHead("Connection"));
	}

	public void handle(Request request, Response response) {
		System.exit(0);		
	}

	public boolean match(Request request) {
		// TODO Auto-generated method stub
		return false;
	}

}