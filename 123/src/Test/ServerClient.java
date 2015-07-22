package Test;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;



public class ServerClient {

	
	public static void main(String[] args){
		int port = 10001;
		ServerSocket serverSocket = null;
		Socket socket = null;
		ExecutorService pool = Executors.newFixedThreadPool(50);
				
		try {
			serverSocket = new ServerSocket(port);
			while (true){
				socket = serverSocket.accept();
				System.out.println("ok");
				NewThread t = new NewThread(socket);
				pool.execute(t);
			}
		}
		catch (Exception e) {
			// TODO Au to-generated catch block
			e.printStackTrace();
		}finally{
			try {	
				pool.shutdown();
				serverSocket.close();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
