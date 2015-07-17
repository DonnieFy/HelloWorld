package WebServer;

import java.io.IOException;
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
	
	public static void execute(Socket socket) throws Exception{
		WebRequest request = new WebRequest(socket.getInputStream());
		WebResponse response = new WebResponse(socket.getOutputStream());
		FileHandler handler = new FileHandler ();
		while(!CloseHandler.match(request)){
			if (handler.match(request)){
				handler.handle(request, response);
			}
		}		
		socket.close();		
		logger.info("shut down Socket {}",socket);
	}
	
	public void run(){
		try {
			execute(socket);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
