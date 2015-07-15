package WebServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class WebResponse implements Response{
	private String pathname = null;
	private InputStream is;
	private PrintStream os;

	public WebResponse(OutputStream os,String pathname){
		this.os = (PrintStream) os;
		this.pathname = pathname;
	}

	public void send() throws IOException {
		sendHead();
		if (pathname.endsWith("/")){
			sendDir();
		}
		else sendFile();
	}

	public void sendHead() {
		os.println("HTTP/1.0 200 sendFile");
		os.println();		
	}
	
	private void sendDir() throws IOException{
		File f = new File(pathname);
		String[] childFile = f.list();
		os.println("<html><body><h1>"+pathname+"'s directory</h1><table border=\"8\">");
		for (int i=0; i<childFile.length; i++){
			File tempFile = new File(pathname+childFile[i]);
			if (tempFile.isDirectory()) childFile[i] += "/";
			os.print("<tr><td><b><a href='"+childFile[i]+"'>");
			os.print(childFile[i]);
			os.print("</a></b></td></tr>");
		}
		os.print("</table></body></html>");
		os.close();
	}
	
	private void sendFile() throws IOException{
		File f = new File (pathname);
		is = new FileInputStream(f);
		int len = (int)f.length();
		byte b[] = new byte[len];
		is.read(b);
		os.write(b);
		os.close();
		is.close();
	}

}
