package Test;

import java.net.Socket;
import java.io.*;

public class NewThread extends Thread{
	
	private Socket socket = null;
	private InputStream is = null;
	private OutputStream os = null;
	
	public NewThread(Socket socket){
		this.socket = socket;
		start();
	}
	
	public void run(){
		try {
			File file = new File("C:/Users/John/Desktop/Succez.html");
			os = socket.getOutputStream();
			//read the html;
			is = new FileInputStream(file);
			int len = (int)file.length();
			byte[] b = new byte[len];
			int offset = 0;
			while (offset<len){
				offset = is.read(b,offset,len-offset);
			}
			os.write(b);
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}		
	}
	public void close(){
		try {
			is.close();
			os.close();
			socket.close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
