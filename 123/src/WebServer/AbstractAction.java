package WebServer;

public abstract class AbstractAction implements Action{

	public void onGet(WebRequest request, WebResponse response) {
		try {
			FileHandler handler =new FileHandler();
			handler.setFlag(true);
			handler.handle(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onPost(Request request, Response response) {
		
	}

}
