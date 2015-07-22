package WebServer;

public class User {

	public String username;
	public String password;
	public String sex = "";
	public String email = "";
	public String birthday = "2015-01-01";
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
}
