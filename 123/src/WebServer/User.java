package WebServer;

public class User {

	private String username = "";
	private String password = "";
	private String sex = "";
	private String email = "";
	private String birthday = "2015-01-01";
	
	public User(String username, String password){
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public User(String username, String password, String sex, String email, String birthday){
		this.setUsername(username);
		this.setPassword(password);
		this.setSex(sex);
		this.setEmail(email);
		this.setBirthday(birthday);
	}
	
	public String getUserHtml(){
		StringBuffer array = new StringBuffer();
		array.append("<html><body><h1>User Data</h1><table border=\"8\">");
		array.append("<tr><td><b>Username</b></td><td>"+username+"</td></tr>");
		array.append("<tr><td><b>Password</b></td><td>"+password+"</td></tr>");
		array.append("<tr><td><b>Sex</b></td><td>"+sex+"</td></tr>");
		array.append("<tr><td><b>Email</b></td><td>"+email+"</td></tr>");
		array.append("<tr><td><b>Birthday</b></td><td>"+birthday+"</td></tr>");
		array.append("</table></body></html>");
		return array.toString();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
