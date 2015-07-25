package WebServer;

public class LoginAction extends AbstractAction implements Action {
	private boolean isLogin = false;
	
	public String getUri() {
		return "/login.html";
	}

	public void onPost(Request request, Response response) {
		
		Handler handler = new FileHandler();
		DbConnect dc = null;
		byte[] body = null;
		try {
			dc = new DbConnect();
			if (dc.isUser(request.getUser())) {
				String str = "success";
				body = str.getBytes();
				isLogin = true;
			}else {
				String str = "failed";
				body = str.getBytes();
				isLogin = false;
			} 
			handler.setFlag(false);
			handler.setBody(body);
			handler.handle(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onGet(Request request, Response response) {
		super.onGet(request, response);
	}

	public boolean isLogin() {
		return isLogin;
	}
	
}
