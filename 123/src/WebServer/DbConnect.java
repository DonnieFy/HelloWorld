package WebServer;
import java.sql.*;

import org.slf4j.*;

public class DbConnect {
		
	private	String drivername = "com.mysql.jdbc.Driver";
	private	String username = "root";
	private	String userPassword = "123456";
	private	String url = "jdbc:mysql://localhost:3306/mywebserver";
	final static Logger logger = LoggerFactory.getLogger(DbConnect.class);
	private Connection conn = null;
	private Statement statement = null; 
	
	public DbConnect() throws Exception {
		
		Class.forName(drivername);
		conn = DriverManager.getConnection(url,username,userPassword);
		if (!conn.isClosed()){
			logger.info("connected to DataBase");
		}
		statement = conn.createStatement();
	}
	
	public boolean isUser(User user) throws SQLException{
		
		String sql = "select password from userlist where username = '"+user.getUsername()+"'";
		ResultSet rs = statement.executeQuery(sql);
		if (!rs.next()) return false;
		if (user.getPassword().equals(rs.getString("password"))){
			return true;
		}else return false;
	}
	
	public boolean addUser(User user) throws SQLException{
		
		String sql = "select password from userlist where username = '"+user+"'";
		ResultSet rs = statement.executeQuery(sql);
		if (rs.next()) { return false;}
		sql = "insert into userlist values('"+user.getUsername()+"','"+user.getPassword()+
				"','"+user.getSex()+"','"+user.getEmail()+"','"+user.getBirthday()+"')";
		if (statement.executeUpdate(sql)==1){
			return true;
		}else return false;		
	}
	
	public ResultSet getUserSet() throws SQLException{
		
		String sql = "select * from userlist";
		ResultSet rs = statement.executeQuery(sql);
		return rs;
	}
	
	public void deleteUser(String username) throws SQLException{
		
		String sql = "delete from userlist where username = '"+username+"'";
		statement.executeUpdate(sql);
	}
	
	public User getUserData(String username) throws SQLException{
		
		String sql = "select * from userlist where username = '"+username+"'";
		ResultSet rs = statement.executeQuery(sql);
		rs.next();
		User user = new User(username, rs.getString("password"),
				rs.getString("sex"),rs.getString("email"),rs.getString("birthday"));
		return user;
	}
}

