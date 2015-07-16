package WebServer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class WebRequest implements Request{
	private String url = null;
	private String method = null;
	private String str = null;
	private InputStream input;
	final static Logger logger = LoggerFactory.getLogger(WebRequest.class);
	Map map = new HashMap();
	
	public WebRequest(InputStream input){
		this.input = input;
	}
	
	public String getStr(){
		return str;
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
	
	public void parse() throws IOException {
		StringBuffer sb = new StringBuffer();
		map.clear();
		url = null;
		method =null;
		str = null;
		while (true){
			int c = input.read();
			if (c=='\t'||c=='\n'||c==-1){
				break;
			}
			sb.append((char)c);
		}
		str = sb.toString();
		if("\r\n ".equals(str)){
			return ;
		}
		logger.info("get String {} from InputStream",str);
		
		if (str.startsWith("GET")){
			String[] st = str.split(" ");
			url = st[1];
			method = st[0];		
		}else {
			String[] st = str.split(" ", 2);
			if (st[0].contains(":")){
				st[0].replace(":", "");
				map.put(st[0], st[1]);
			}
		}
	}
	
	
}
