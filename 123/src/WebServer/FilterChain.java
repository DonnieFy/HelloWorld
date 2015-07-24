package WebServer;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilterChain {
	final Logger logger = LoggerFactory.getLogger(FileHandler.class);
	private Map<String, String> sessionMap = new HashMap<String, String>(); 
	String session = null;
	
	public FilterChain(){
	}
	
	public void doFilter(Request request){
		session = request.getHead("Cookie");
		logger.info("{}",session);
		String uri = request.getUrl();
		if ("/userlist.html".equals(uri)&&sessionMap.containsKey(session)){
			if (!"admin".equals(sessionMap.get(session))){
				request.setUrl("/");
				return;
			}
		}
		if ("/jquery-1.3.2.min.js".equals(uri)){
			return;
		}
		if (sessionMap.containsKey(session)&&!isUserUri(uri)){
			request.setUrl("/");
			return;
		}
		if (!sessionMap.containsKey(session)&&isUserUri(uri)){
			request.setUrl("/login.html");
			return;
		}
	}
	
	public void doLogin(User user){
		if (user!=null){
			sessionMap.put(session, user.getUsername());
			logger.info("do login {} {}",session,user.getUsername());
		}
	}
	
	public void doLogout(){
		if (sessionMap.containsKey(session)){
			sessionMap.remove(session);
			logger.info("do logout");
		}
	}
	
	private boolean isUserUri(String uri){
		if ("/login.html".equals(uri)||"/regist.html".equals(uri)){
			return false;
		}else return true;
	}
}
