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
		StringBuffer sb = new StringBuffer();
		String str = null;
		while (true){
			int c = input.read();
			if (c=='\t'||c=='\n'||c==-1){
				break;
			}
			sb.append((char)c);
		}
		str = sb.toString();
		if (str.startsWith("GET")){
			String[] st = str.split(" ");
			url = st[1];
			method = st[0];		
			logger.info("get method GET {}", url);
		}else {
			String[] st = str.split(" ", 2);
			if (st[0].contains(":")){
				st[0].replace(":", "");
				map.put(st[0], st[1]);
			}
		}
	}
	
	
}
