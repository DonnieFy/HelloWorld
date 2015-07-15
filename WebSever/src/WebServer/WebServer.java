package WebServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {
	
	public static void main(String[] args) throws IOException{
		int port = 10010;
		ServerSocket server = new ServerSocket(port);
		ExecutorService pool = Executors.newCachedThreadPool();
		boolean f = true;
		while (f){
			Socket socket = server.accept();
			pool.execute(new WebThread(socket));
		}
		server.close();
	}
}
