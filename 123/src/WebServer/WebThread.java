package WebServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.LoggerFactory;

import org.slf4j.LoggerFactory;	
import org.slf4j.Logger;

public class WebThread extends Thread{
	private Socket socket;
	final static Logger logger = LoggerFactory.getLogger(WebThread.class);
	
	public WebThread(Socket socket){
		this.socket = socket;
	}
	
	public static void execute(Socket socket) throws IOException{
		WebRequest request = new WebRequest(socket.getInputStream());
		WebResponse response = new WebResponse(socket.getOutputStream());
		logger.info("get request from Socket {}",socket);		
		Parse.parse(request,response);
		socket.close();
		logger.info("shut down Socket {}",socket);
	}
	
	public void run(){
		try {
			execute(socket);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
