package WebServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.LoggerFactory;	
import org.slf4j.Logger;

public class WebServer {
	final static Logger logger = LoggerFactory.getLogger(WebServer.class);
	
	public static void main(String[] args) throws IOException{
		
		int port = 10001;
		ServerSocket server = new ServerSocket(port);
		logger.info("set up Server {}",port);
		
		ExecutorService pool = Executors.newCachedThreadPool();
		boolean f = true;
		while (f){
			Socket socket = server.accept();
			pool.execute(new WebThread(socket));
			logger.info("execute pool {}",socket);
		}
		server.close();
		logger.info("close the Server");
	}
}
