package Test;
import java.net.*;
import java.io.*;



public class ServerClient {

	
	public static void main(String[] args){
		int port = 10012;
		ServerSocket serverSocket = null;
		Socket socket = null;
				
		try {
			serverSocket = new ServerSocket(port);
			while (true){
				System.out.println(serverSocket.getLocalSocketAddress());
				socket = serverSocket.accept();
				System.out.println(socket.getLocalSocketAddress());
				new NewThread(socket);
				System.out.println("OK");
			}
		}
		catch (Exception e) {
			// TODO Au to-generated catch block
			e.printStackTrace();
		}finally{
			try {	
				serverSocket.close();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
