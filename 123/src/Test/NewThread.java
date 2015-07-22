package Test;

import java.net.Socket;
import java.io.*;

import com.sun.javafx.font.LogicalFont;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class NewThread extends Thread{
	
	private Socket socket = null;
	private InputStream is = null;
	private PrintStream os = null;
	private Reader in = null;
	private StringBuffer request;
	private String file = null;
	
	public NewThread(Socket socket){
		this.socket = socket;
		start();
	}
	
	public void run(){
		try {
			file = "D:/MailMaster";
			os = new PrintStream(socket.getOutputStream());
			//read the html;
	//		is = new FileInputStream(file);
			in = new InputStreamReader(socket.getInputStream());
			
	//		while (true){
				readRequest();
				logic();
				
			
	/*		int len = (int)file.length();
			byte[] b = new byte[len];
			int offset = 0;
			while (offset<len){
				offset = is.read(b,offset,len-offset);
			}
			os.write(b);  */

			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		//	close();
		}		
	}
	public void close(){
		try {
			in.close();
			os.close();
			socket.close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void logic() throws IOException{		
		String[] req = request.toString().split(" ");
		String filename = "";
		if (req.length==3){
			filename += req[1]; 
		}
	//	System.out.println(filename);
		if (filename.endsWith("/")){
			showDir(file+filename);	
		}
		else if (!(filename=="")){
			showFile(file+filename);
		}
	}
	
	public void readRequest() throws IOException{
		request = new StringBuffer();
		while (true){
			int c = in.read();
			if (c=='\n'||c=='\t'||c==-1){
				break;
			}
			request.append((char)c);
		}
		System.out.println(request);
	}
	
	public void showDir(String pathname) throws IOException{
		File dir = new File(pathname);
		String[] child = dir.list();
		os.println("HTTP/1.0 200 sendFile");
		os.println();
		os.println("<html><body><h1>"+pathname+"'s directory</h1><table border=\"8\">");
	//	System.out.println(pathname);
		int len = child.length;
		for(int i=0; i<len; i++){	
		//	System.out.println(child.length);
			File tempFile = new File(pathname+child[i]);
			if (tempFile.isDirectory()) child[i] += "/";
			os.print("<tr><td><b><a href='"+child[i]+"'>");
			os.print(child[i]);
			os.print("</a></b></td></tr>");
		}
		os.println("</table></body></html>");
		os.flush();
	//	os.close();
	}
	public void showFile(String pathname) throws IOException{
		File f = new File (pathname);
		InputStream inp = new FileInputStream(f);
		int len = (int)f.length();
		byte b[] = new byte[len];
		inp.read(b);
		os.write(b);
	}
}

