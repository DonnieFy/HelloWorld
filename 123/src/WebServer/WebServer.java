package WebServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import org.slf4j.LoggerFactory;	
import org.slf4j.Logger;

public class WebServer {
	final static Logger logger = LoggerFactory.getLogger(WebServer.class);
	
	public static void main(String[] args) throws IOException{
		
		ServerSocket server = new ServerSocket(10010);
		logger.info("set up Server");
	//	ServerSocket server = ctx.getBean("Server",ServerSocket.class);
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
