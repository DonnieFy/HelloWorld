package WebServer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;



public class WebRequest implements Request  {
	private String url = null;
	private String method = null;
	private User user = null;
	private String session = null;
	private InputStream input;
	final static Logger logger = LoggerFactory.getLogger(WebRequest.class);
	Map<String, String> map = new HashMap<String, String>();
	
	public WebRequest(InputStream input) throws IOException{
		this.input = input;
	}
	
	public String getSession(){
		if (session!=null) return session;
		session = map.get("Cookie") + map.get("User-Agent");
		session = session.replace(" ","");
		return session;
	}
	
	public String getHead(String mm){		
		if (map.containsKey(mm)){
			return map.get(mm).toString();
		}else return "";
	}
	
	public String getUrl(){
		return url;
	}
	
	public String getMethod(){
		return method;
	}
	
	public User getUser(){
		return user;
	}
	
	public void proccess() throws IOException  {
		
		url = null;
		method = null;
		user = null;
		int i = 0, c = 0, offset = 0;
		int len = input.available();
		
		byte b[] = new byte[len];
		if (len>0) b[0] = (byte)c;
		while (offset<len){
			offset += input.read(b,offset,len - offset);
		}
		while (i<len){
			StringBuffer sb = new StringBuffer();
			String str = null;
			while (true){
				c = b[i];
				i++;
				sb.append((char)c);
				if (c=='\t'||c=='\n'||i==len)	break;
			}
			str = sb.toString();
			logger.info("get string {}",str);
			
			if (str.startsWith("GET")||str.startsWith("POST")){
				parseMethod(str);
			}else if (str.contains("password")&&"POST".equals(method)){
				parseUser(str);
			}else if (str.contains(":")){
				parseHead(str);
			}
		}
	}	
	private void parseMethod(String str) {
		String[] st = str.split(" ");
		url = st[1];
		method = st[0];		
		map.clear();
	}
	
	private void parseHead(String str){
		String[] st = str.split(" ", 2);
		if (st[0].contains(":")){
			st[0].replace(":", "");
			map.put(st[0], st[1]);
		}
	}
	
	private void parseUser(String str) {
		String st[] = str.split("&");
		String username = st[0].substring(st[0].indexOf("=")+1,st[0].length());
		String password = st[1].substring(st[1].indexOf("=")+1,st[1].length());
		user = new User(username, password);
		logger.info("user {} password {}",username,password);
	}
}	
	
