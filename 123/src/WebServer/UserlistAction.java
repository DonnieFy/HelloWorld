package WebServer;

import java.sql.ResultSet;

public class UserlistAction extends AbstractAction implements Action{

	public String getUri() {
		return "/userlist.html";
	}

	public void onGet(Request request, Response response){
		try {
			Handler handler = new FileHandler();
			DbConnect dc = new DbConnect();
			ResultSet rs = dc.getUserSet();
			StringBuffer array = new StringBuffer("");
			array.append("<html><body><h1>Use List</h1><table border=\"8\">");
			array.append("<tr><td><b>Username</b></td><td><b>Password</b></td></tr>");
			while (rs.next()){
				array.append("<tr><td>"+rs.getString("username")+"</td><td>");
				array.append(rs.getString("password")+"</td><td>");
				array.append("<a href=\"/delete.html?" +rs.getString("username")+
						"\"><font color=\"red\">Delete</font></a></td></tr>");
			}
			array.append("</table></body></html>");
			handler.setFlag(false);
			handler.setBody(array.toString().getBytes());
			handler.handle(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		
	}
}
