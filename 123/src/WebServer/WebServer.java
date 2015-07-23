package WebServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServer {
	final static Logger logger = LoggerFactory.getLogger(WebServer.class);
	
	public static void main(String[] args) throws IOException{

		Map<String, Action> actions = init();
		ServerSocket server = new ServerSocket(10001);
		FilterChain filter = new FilterChain();
		logger.info("set up Server");
	//	ServerSocket server = ctx.getBean("Server",ServerSocket.class);
		ExecutorService pool = Executors.newCachedThreadPool();
		boolean f = true;
		while (f){
			Socket socket = server.accept();
			pool.execute(new WebThread(socket, actions, filter));
			logger.info("execute pool {}",socket);
		}
		server.close();
		logger.info("close the Server");
	}
	
	public static Map<String, Action> init(){
		Action login = new LoginAction();
		Action regist = new RegistAction();
		Action logout = new LogoutAction();
		Map<String, Action> actions = new HashMap<String,Action>(2);
		actions.put(login.getUri(), login);
		actions.put(regist.getUri(), regist);
		actions.put(logout.getUri(), logout);
		return actions;
	}
}
