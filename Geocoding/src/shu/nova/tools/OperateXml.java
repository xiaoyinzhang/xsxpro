package shu.nova.tools;

import java.io.File;
import java.sql.Timestamp;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import shu.nova.model.LongLatData;

public class OperateXml {
	 public static LongLatData getContent(String path,String addrName,String uuid){
		 
		 LongLatData lldData=null;
		 DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	       Element theBook=null, theElem=null, root=null;
	       try {
	           factory.setIgnoringElementContentWhitespace(true);
	           
	           DocumentBuilder db=factory.newDocumentBuilder();
	           Document xmldoc=db.parse(new File(path));
	           root=xmldoc.getDocumentElement();
	           //output(root);
	           
	           Element resultDom=(Element)root.getElementsByTagName("result").item(0);
	           Element locationDom=(Element)resultDom.getElementsByTagName("location").item(0);
	           
	           String status=root.getElementsByTagName("status").item(0).getTextContent();
	           String latitude=locationDom.getElementsByTagName("lat").item(0).getTextContent();
	           String longitude=locationDom.getElementsByTagName("lng").item(0).getTextContent();
	           String precise=resultDom.getElementsByTagName("precise").item(0).getTextContent();
	           String confidence=resultDom.getElementsByTagName("confidence").item(0).getTextContent();
	           String level=resultDom.getElementsByTagName("level").item(0).getTextContent();
	      
	          boolean bprecise=true;
	          
	          if(precise.equals("0")){
	        	  bprecise=false;
	          }
	          
	          Timestamp ts=new Timestamp(System.currentTimeMillis());     
	          lldData=new LongLatData(addrName,status,latitude,longitude, bprecise,confidence,level, "上海", ts, uuid);
	          
	          
	       }catch(Exception e){
	    	   e.printStackTrace();
	    	   return null;
	       }
	       return lldData;
	 }
}
