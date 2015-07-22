package WebServer;


import java.net.Socket;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class WebThread extends Thread{
	private Socket socket;
	private Map<String, Action> actions;
	final static Logger logger = LoggerFactory.getLogger(WebThread.class);
	
	public WebThread(Socket socket, Map<String, Action> actions){
		this.socket = socket;
		this.actions = actions;
	}

	public void execute(Socket socket) throws Exception{
		WebRequest request = new WebRequest(socket.getInputStream());
		WebResponse response = new WebResponse(socket.getOutputStream());
		Action action = null;
		while(!CloseHandler.match(request)){
			String uri = request.getUrl();
			if (actions.containsKey(uri)){
				action = actions.get(uri);
			}else {
				action = new AllAction();
			}
			if("GET".equals(request.getMethod())){
				action.onGet(request, response);
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
