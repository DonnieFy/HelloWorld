package WebServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebResponse implements Response{
	
	private PrintStream os;
	final static Logger logger = LoggerFactory.getLogger(WebResponse.class);

	public WebResponse(OutputStream os){		
		this.os = new PrintStream(os);
	}

	public void setStatus(int status) {
		if (status==200)  os.println("HTTP/1.0 200 OK");
		if (status==404)  os.println("HTTP/1.1 404 Not Found");		 
	}	

	public void addHeader(String key, String value) {	
		os.println(key+value);		
	}

	public void addBody(byte[] body) {	
		os.println();
		try {
			os.write(body);
			os.println();
		}
		catch (IOException e) {
			logger.error("IOException when addBody");
			e.printStackTrace();
		}
	}

	public void send() {		
		os.flush();		
	}

}
