package WebServer;

import java.util.ArrayList;

public class UserdataAction extends AbstractAction implements Action{

	public String getUri() {
		return "/userdata.html";
	}
	
	public void onGet(Request request, Response response) {
		
		try {
			Handler handler = new FileHandler();
			DbConnect dc = new DbConnect();
			String username = request.getUsername();
			User user = dc.getUserData(username);
			String str = user.getUserHtml();
			handler.setFlag(false);
			handler.setBody(str.getBytes());
			handler.handle(request, response);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
