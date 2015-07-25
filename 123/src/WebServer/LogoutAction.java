package WebServer;

public class LogoutAction extends AbstractAction implements Action{

	private boolean isLogout = false;
	public String getUri() {
		return "/logout.html";
	}
	public void onGet(Request request, Response response){
		super.onGet(request, response);
		isLogout = false;
	}
	
	public void onPost(Request request, Response response){
		
		try {
			Handler handler = new FileHandler();
			handler.setFlag(false);	
			byte[] body = "success".getBytes();
			handler.setBody(body);
			handler.handle(request, response);
			isLogout = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isLogout() {
		return isLogout;
	}

}
