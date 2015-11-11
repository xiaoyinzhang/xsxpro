package shu.nova.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchLongLatitude {
	
	private URL url;
	private HttpURLConnection httpURLConnection;
	
	
	public SearchLongLatitude() {
		super();
	}


	public String SendURLPost(String urlStr,String xmlFile) throws IOException {  
		
		   url = new URL(urlStr);   
	       httpURLConnection = (HttpURLConnection) url.openConnection(); 
	       httpURLConnection.setRequestMethod("POST");   
	       httpURLConnection.setDoOutput(true);   
  
	       OutputStream os = httpURLConnection.getOutputStream();      
	       os.flush();   
	       os.close();   
	       
	       //File file=new File();
	       File file=new File(xmlFile);
	       if(!file.exists())
	             file.createNewFile();
   
	       FileOutputStream fos = new FileOutputStream(file);
	     
	       InputStream is = httpURLConnection.getInputStream();  
	       StringBuilder sb = new StringBuilder();    	       
	     
	       int size = 0;  
	       while ((size = is.read()) !=-1) {  
	    	   fos.write(size);
	    	   
	    	   //String str = new String(bytes, 0, size, "UTF-8");  
	           //sb.append(str);  
	    	   sb.append(size);
	       } 
	       /*
	       is.reset();
	       byte[] bytes = new byte[4096];     
	       while ((size = is.read(bytes)) > 0) { 
	    	   fos.write(bytes);
	    	   
	    	   String str = new String(bytes, 0, size, "UTF-8");  
	           sb.append(str);  
	       }  */
	       String content = new String(sb);          
	       System.out.println(content);   
	       
	       fos.flush();
	       fos.close();
	       is.close();
	       
	       return content;
	} 
	 
	
}
