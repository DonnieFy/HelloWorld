package WebServer;

public interface Action {

	public String getUri();
	
	public void onGet(Request request, Response response);
	
	public void onPost(Request request, Response response);

	public boolean isLogin();
	
	public boolean isLogout();
}
