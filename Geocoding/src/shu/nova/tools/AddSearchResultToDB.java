package shu.nova.tools;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.hibernate.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import shu.nova.model.DuplicateData;
import shu.nova.model.DuplicateDataDAO;
import shu.nova.model.LongLatData;
import shu.nova.model.LongLatDataDAO;

public class AddSearchResultToDB {

	 public static void init(String path,String addrName,String uuid){
		   
		   LongLatDataDAO llddDao=new LongLatDataDAO();
		   List list=llddDao.findByAddrName(addrName);
		   
		   if(list.size()>=1){
			   Timestamp ts1=new Timestamp(System.currentTimeMillis());
			   System.out.println("list.size()="+list.size());
			   DuplicateData ddData=new DuplicateData(addrName,uuid,ts1);
			   DuplicateDataDAO ddDao=new DuplicateDataDAO();
			   Transaction tx = ddDao.getSession().beginTransaction();
			   ddDao.save(ddData);
		  	   tx.commit();
		  	   ddDao.getSession().close();
			   return;
		   }
		   
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
	 
	      
	          //System.out.println("status="+status);
	          //System.out.println("latitude="+latitude);
	          //System.out.println("longitude="+longitude);
	          //System.out.println("precise="+precise);
	          //System.out.println("confidence="+confidence);
	          //System.out.println("level="+level);
	           
	          boolean bprecise=true;
	          
	          if(precise.equals("0")){
	        	  bprecise=false;
	          }
	          
	          Timestamp ts=new Timestamp(System.currentTimeMillis());
	          //UUID uuid=UUID.randomUUID();
	          
	          LongLatData lldData=new LongLatData(addrName,status,latitude,longitude, bprecise,confidence,level, "上海", ts, uuid);
	          
	          //LongLatDataDAO lldDao=new LongLatDataDAO();  
	           
	          Transaction tx = llddDao.getSession().beginTransaction();
	          llddDao.save(lldData);
	  		  tx.commit();
	  		  llddDao.getSession().flush();
	  		  llddDao.getSession().close();
	  		  //List list1=lldDao.findAll();
	          //System.out.println("size==="+list1.size());
	          
	       } catch (ParserConfigurationException e) {
	           e.printStackTrace();
	       } catch (SAXException e) {
	           e.printStackTrace();
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	   }
	 public static void output(Node node){
	       TransformerFactory transFactory=TransformerFactory.newInstance();
	        try {
	            Transformer transformer = transFactory.newTransformer();
	            transformer.setOutputProperty("encoding", "gb2312");
	            transformer.setOutputProperty("indent", "yes");

	           DOMSource source=new DOMSource();
	            source.setNode(node);
	            StreamResult result=new StreamResult();
	            result.setOutputStream(System.out);
	            
	            transformer.transform(source, result);
	        } catch (TransformerConfigurationException e) {
	            e.printStackTrace();
	        } catch (TransformerException e) {
	            e.printStackTrace();
	        }   
	    }
}
