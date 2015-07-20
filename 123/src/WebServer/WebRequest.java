package WebServer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.sun.corba.se.spi.orb.StringPair;


public class WebRequest implements Request{
	private String url = null;
	private String method = null;
	private String user = null;
	private String password = null;
	private InputStream input;
	final static Logger logger = LoggerFactory.getLogger(WebRequest.class);
	Map<String, String> map = new HashMap<String, String>();
	
	public WebRequest(InputStream input) throws IOException{
		this.input = input;
	}
	
	public String getHead(String mm){		
		try {
			parse();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
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
	
	private void parse() throws IOException {
		
		url = null;
		method = null;
		user = null;
		password = null;
		int i = 0, c = input.read();
		int offset = 1, len = input.available();
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
				if (c=='\t'||c=='\n'||i==len)	break;
				sb.append((char)c);
			}
			str = sb.toString();
			if ("".equals(str)||str==null)  break;
			else logger.info("get string {}",str);
			if (str.startsWith("GET")||str.startsWith("POST")){
				String[] st = str.split(" ");
				url = st[1];
				method = st[0];		
				map.clear();
			}else if ("GET".equals(method)){
				String[] st = str.split(" ", 2);
				if (st[0].contains(":")){
					st[0].replace(":", "");
					map.put(st[0], st[1]);
				}else  	break;
			}
			else if (str.contains("user")){
				String st[] = str.split("&");
				user = st[0].substring(st[0].indexOf("=")+1,st[0].length());
				password = st[1].substring(st[1].indexOf("=")+1,st[1].length());
				logger.info("user {} password {}",user,password);
			}
			
		}
	}
}	
	
