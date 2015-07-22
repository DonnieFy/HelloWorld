package WebServer;

public class LoginAction extends AbstractAction implements Action {

	public String getUri() {
		return "/login.html";
	}

	public void onPost(WebRequest request, WebResponse response) {
		
		FileHandler handler = new FileHandler();
		DbConnect dc = null;
		byte[] body = null;
		try {
			dc = new DbConnect();
			if (dc.isUser(request.getUser())) {
				String str = "success";
				body = str.getBytes();
			}else {
				String str = "failed";
				body = str.getBytes();
			} 
			handler.setFlag(false);
			handler.setBody(body);
			handler.handle(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
