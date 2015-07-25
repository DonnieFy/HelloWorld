package WebServer;

public class RegistAction extends AbstractAction implements Action{
	private boolean isLogin = false;
	
	public String getUri() {
		return "/regist.html";
	}

	public void onPost(Request request, Response response) {
		//注册的提交
		FileHandler handler = new FileHandler();
		DbConnect dc = null;
		byte[] body = null;
		try{
			dc = new DbConnect();
			if (dc.addUser(request.getUser())){
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
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public boolean isLogin() {
		return isLogin;
	}

}
