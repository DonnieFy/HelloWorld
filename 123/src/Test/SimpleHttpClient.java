package Test;
import java.io.*;
import java.net.*;







public class SimpleHttpClient{
	private static InputStream is = null;
	private static OutputStream os = null;
	
	public static void main(String[] args) {
		
		URL url;
		try {
			String pathName = null;
			pathName = args[0];
		//  System.out.println(args[0]);
			url = new URL(pathName);
			URLConnection uCon = url.openConnection();
			HttpURLConnection con = (HttpURLConnection)uCon;
			is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String html = "";
			String current;
	//		is.read(current);
			while((current = br.readLine())!=null){
				html += current;
			}
			setHtml(html);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void setHtml(String html) throws IOException{
		os = new FileOutputStream("C:/Users/John/Desktop/baidu.html");
		os.write(html.getBytes());
		os.close();
	}
	
}
