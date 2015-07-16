package WebServer;

import java.io.IOException;
import org.slf4j.*;

public class Parse {
	final static Logger logger = LoggerFactory.getLogger(Parse.class);

	public static void parse(WebRequest request,WebResponse response) throws IOException {
		String root = "D:/MailMaster";
		boolean flag = true;
		while (flag){
			request.parse();
			String str = request.getStr();
			if (str==null||"".equals(str)){
				flag = false;
			}else if ("GET".equals(request.getMethod())){			
				String pathname = root+request.getUrl();
				logger.info("get response from {}",pathname);
				response.send(pathname);
			}else if ("close".equals(request.getHead("Connection"))){
				flag = false;
			}
		}
	}
}
