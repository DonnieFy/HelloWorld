package WebServer;

import java.io.IOException;
import java.net.Socket;

public class WebThread extends Thread{
	Socket socket;
	
	public WebThread(Socket socket){
		this.socket = socket;
		start();
	}
	
	public static void execute(Socket socket) throws IOException{
		String root = "D:/MailMaster";
		WebRequest request = new WebRequest(socket.getInputStream());
		boolean flag = true;
		while (flag){
			String str = request.getHead();
			if (str!="GET"){
				flag = false;
			}else {
				WebResponse response = new WebResponse(socket.getOutputStream(),root+request.getUrl());
				response.send();
			}
		}
		socket.close();
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
