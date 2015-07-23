package WebServer;


import java.net.Socket;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class WebThread extends Thread{
	private Socket socket;
	private Map<String, Action> actions;
	private FilterChain filter;
	final static Logger logger = LoggerFactory.getLogger(WebThread.class);
	
	public WebThread(Socket socket, Map<String, Action> actions, FilterChain filter){
		this.socket = socket;
		this.actions = actions;
		this.filter = filter;
	}

	public void execute(Socket socket) throws Exception{
		Request request = new WebRequest(socket.getInputStream());
		Response response = new WebResponse(socket.getOutputStream());
		Handler handler = new FileHandler();
		Action action = null;
		while(!handler.match(request)){
			String uri = request.getUrl();
			if (uri==null) continue;
			filter.doFilter(request);
			uri = request.getUrl();
			if (actions.containsKey(uri)){
				action = actions.get(uri);
			}else {
				action = new AllAction();
			}
			if("GET".equals(request.getMethod())){
				action.onGet(request, response);
			}else if ("POST".equals(request.getMethod())){
				action.onPost(request, response);
			}
			if (action.isLogin()){
				filter.doLogin(request.getUser());
			}
			if (action.isLogout()){
				filter.doLogout();
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
