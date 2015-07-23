package WebServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.text.DateFormat;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class FileHandler implements Handler{
	
	private String pathname = null;
	private byte[] body = null;
	private String session = null;
	private Map<Integer, String> error = new HashMap<Integer, String>();
	final Logger logger = LoggerFactory.getLogger(FileHandler.class);
	private String root = "D:/MailMaster";
	private boolean flag = true;
	
	public FileHandler(){
		setError();
	}

	public void handle(Request request, Response response) throws Exception {
		
		pathname = root + request.getUrl();
		session = request.getHead("Cookie");
		String header[] = {"Data: ","Server: ","Content-Length: ",
				"Keep-Alive: ","Connection: ","Content-Type: "};	
		response.setStatus(getStatus());
		for (int i=0; i<header.length; i++){
			response.addHeader(header[i], parse(i));
		}
		if ("".equals(session)){
			response.addHeader("Set-Cookie: ", "ID="+getRandomString());
		}
		response.addBody(body);
		response.send();
		logger.info("send file from {}", pathname);
	}
	
	private String parse(int i){
		
		switch (i){
			case 0: {	
				Date date = new Date( );
				DateFormat df = DateFormat.getDateTimeInstance();
				return df.format(date);
			}
			case 1: {
				return System.getProperty("os.name")+System.getProperty("os.version");
			}
			case 2: {
				return String.valueOf(body.length);
			}		
			case 3: {
				return "timeout=15, max=100";
			}
			case 4: {
				return "keep-alive";
			}
			case 5: {
				return getType();
			}
		}
		return "";
	}
	public void setFlag(boolean flag){
		this.flag = flag;
	}
	
	public void setBody(byte[] body){
		this.body = body;
	}
	
	private void proccess() throws Exception{
		
		if (pathname.endsWith("/")){
			proccessDir();
		}else proccessFile();
	}
	
	private void proccessDir(){
		
		File f = new File(pathname);
		String[] childFile = f.list();
		StringBuffer array = new StringBuffer("");
		array.append("<html><body><h1>"+pathname+"'s directory</h1><table border=\"8\">");
		for (int i=0; i<childFile.length; i++){
			File tempFile = new File(pathname+childFile[i]);
			if (tempFile.isDirectory()) childFile[i] += "/";
			array.append("<tr><td><b><a href='"+childFile[i]+"'>");
			array.append(childFile[i]);
			array.append("</a></b></td></tr>");
		}
		array.append("</table></body></html>");
		body = array.toString().getBytes();
	}
	
	private void proccessFile() throws Exception{
		
		File f = new File (pathname);
		InputStream is = new FileInputStream(f);
		int len = (int)f.length();
		body = new byte[len];
		int offset = 0;
		while (offset<len){
			offset += is.read(body,offset,len-offset);
		}
		is.close();
	}
	
	private void proccessError(int status) throws UnknownHostException{
		
		String str = "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\r\n"+
				"<html><head>\r\n"+
				"<title>"+status+error.get(status)+"</title>\r\n"+
				"</head><body>"+"<h1>"+error.get(status)+"</h1>"+
				"<p>The requested "+pathname+" was "+error.get(status)+"on this server.</p>\r\n"+
				"<hr>"+"<address>"+InetAddress.getLocalHost().getHostName()+" at "+
				InetAddress.getLocalHost()+" Port 8080</address>"+"</body></html>";
		body = str.getBytes();
	}
	
	private int getStatus() throws Exception{
				
		if (!flag) return 200;
		File file = new File (pathname);
		if (!file.exists()){
			proccessError(404);
			return 404;
		}else {
			proccess();
			return 200;
		}
	}
	
	private String getType(){
		
		if (pathname.endsWith(".png")||pathname.endsWith(".ico")){
			return "image/png";
		}else if (pathname.endsWith(".wav")){
			return "audio/wav";
		}else return "text/html";
	}
	
	private void setError(){
		
		error.put(404, "Not Found");
	}

	public boolean match(Request request) {
		try {
			request.proccess();
			return "close".equals(request.getHead("Connection"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private String getRandomString(){
		String base = "1234567890abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		String str = "";
		for (int i=0; i<9; i++){
			int n = random.nextInt(base.length());
			str += base.charAt(n);
		}
		logger.info("get random {}",str);
		return str;
	}
	

}
