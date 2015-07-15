package WebServer;

import java.io.IOException;
import java.io.InputStream;

public class WebRequest implements Request{
	private String url = null;
	private String head = null;
	private InputStream input;
	
	public WebRequest(InputStream input){
		this.input = input;
	}
	public String getUrl(){
		return url;
	}
	
	public String getHead(){
		return head;
	}
	
	public void parse() throws IOException {
		StringBuffer sb = new StringBuffer();
		while (true){
			int c = input.read();
			if (c=='\t'||c=='\n'||c==-1){
				break;
			}
			sb.append((char)c);
		}
		String[] st = sb.toString().split(" ");
		if (st.length==3){
			url = st[1];
			head = st[0];
		}
		input.close();
	}
}
