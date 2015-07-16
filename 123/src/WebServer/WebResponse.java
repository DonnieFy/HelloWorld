package WebServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebResponse implements Response{
	private InputStream is;
	private PrintStream os;
	final static Logger logger = LoggerFactory.getLogger(WebResponse.class);
	Map map = new HashMap();

	public WebResponse(OutputStream os){
		this.os = new PrintStream(os);
		init();
	}

	public void send(String pathname) throws IOException {
		File file = new File (pathname);
		if (!file.exists()){
			sendHead("404");
			return;
		}
		sendHead("200");
		if (pathname.endsWith("/")){
			sendDir(pathname);
			logger.info("send dir from {}",pathname);
		}else {
			sendFile(pathname);
			logger.info("send file from {}",pathname);
		}
	}

	public void sendHead(String str) {
		os.println(map.get(str).toString());
		os.println();		
	}
	
	private void sendDir(String pathname) throws IOException{
		File f = new File(pathname);
		String[] childFile = f.list();
		os.println("<html><body><h1>"+pathname+"'s directory</h1><table border=\"8\">");
		for (int i=0; i<childFile.length; i++){
			File tempFile = new File(pathname+childFile[i]);
			if (tempFile.isDirectory()) childFile[i] += "/";
			os.print("<tr><td><b><a href='"+childFile[i]+"'>");
			os.print(childFile[i]);
			os.print("</a></b></td></tr>");
		}
		os.print("</table></body></html>");
		os.flush();
	}
	
	private void sendFile(String pathname) throws IOException{
		File f = new File (pathname);
		is = new FileInputStream(f);
		int len = (int)f.length();
		byte b[] = new byte[len];
		int offset = 0;
		while (offset<len){
			offset += is.read(b,offset,len-offset);
		}
		os.write(b);
		os.flush();
		is.close();
	}
	
	private void init(){
		map.clear();
		
		String str = "HTTP/1.1 404 Not Found\r\n"+
				"Date: July 2015 GMT\r\n"+
				"Server: Apache/2.0.55 (Windows) PHP/5.0.5\r\n"+
				"Content-Length: 291\r\n"+
				"Keep-Alive: timeout=15, max=100\r\n"+
				"Connection: Keep-Alive\r\n"+
				"Content-Type: text/html; charset=iso-8859-1\r\n\r\n"+
				"<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\r\n"+
				"<html><head>\r\n"+
				"<title>404 Not Found</title>\r\n"+
				"</head><body>\r\n"+
				"<h1>Not Found</h1>\r\n"+
				"<p>The requested URL /notexist was not found on this server.</p>\r\n"+
				"<hr>\r\n"+
				"<address>Apache/2.0.55 (Unix) PHP/5.0.5 Server at localhost Port 8080</address>\r\n"+
				"</body></html>\r\n";
		map.put("404", str);
		str = "HTTP/1.0 200 OK\r\n"+
				"Keep-Alive: timeout=15, max=100\r\n"+
				"Connection: Keep-Alive\r\n"+
				"Content-Type: text/html\r\n";
		map.put("200", str);
		str = "HTTP/1.0 200 OK\r\n"+
				"Keep-Alive: timeout=15, max=100\r\n"+
				"Connection: Keep-Alive\r\n"+
				"Content-Type: image/jpeg\r\n";
		map.put("201", str);
	}

}
