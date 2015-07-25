package WebServer;

import java.sql.SQLException;

public class DeleteAction extends AbstractAction implements Action{

	public String getUri() {
		return "/delete.html";
	}
	
	public void onGet(Request request, Response response){
		
		try {
			Handler handler = new FileHandler();
			String uri = request.getUrl();
			String username = uri.substring(uri.indexOf("?")+1, uri.length());
			DbConnect dc = new DbConnect();
			dc.deleteUser(username);
			request.setUrl("/");
			handler.setFlag(true);
			handler.handle(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}

