package WebServer;

public class RegistAction extends AbstractAction implements Action{

	public String getUri() {
		return "/regist.html";
	}

	public void onPost(WebRequest request, WebResponse response) {
		//注册的提交
		FileHandler handler = new FileHandler();
		DbConnect dc = null;
		byte[] body = null;
		try{
			dc = new DbConnect();
			if (dc.newUser(request.getUser())){
				String str = "success";
				body = str.getBytes();
			}else {
				String str = "failed";
				body = str.getBytes();
			}
			handler.setFlag(false);
			handler.setBody(body);
			handler.handle(request, response);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
