package shu.cloud.tools;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
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

import shu.cloud.model.LongLatData;
import shu.cloud.model.LongLatDataDAO;
import shu.cloud.model.TaskFileData;
import shu.cloud.model.TaskFileDataDAO;

public class AddSearchResultToDB {

	 public static void init(String path,String addrName,String uuid){
		   /*
		   
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
		   */
		   
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
	           String latitude="0";
	           String longitude="0";
	           String precise="0";
	           String confidence="0";
	           String level="0";
	           if(status.equals("OK")){
	        	   latitude=locationDom.getElementsByTagName("lat").item(0).getTextContent();
		           longitude=locationDom.getElementsByTagName("lng").item(0).getTextContent();
		           precise=resultDom.getElementsByTagName("precise").item(0).getTextContent();
		           confidence=resultDom.getElementsByTagName("confidence").item(0).getTextContent();
		           level=resultDom.getElementsByTagName("level").item(0).getTextContent();
	           }else if(status.equals("INVALID_PARAMETERS")){
	        	   status="INVALID_PARAMETERS";
	           }else if(status.equals("INVALID_PARAMETERS")){
	        	   status="INVILID_KEY";
	           }
	           
	 
	      
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
	          LongLatDataDAO llddDao=new LongLatDataDAO();
	          Transaction tx = llddDao.getSession().beginTransaction();
	          llddDao.save(lldData);
	  		  tx.commit();
	  		  llddDao.getSession().close();
	  		  
	  		  
	  		  TaskFileDataDAO tDao = new TaskFileDataDAO();
	  		 
	  		  List list=tDao.findByUuid(uuid);
	  		  Iterator iter=list.iterator();
	  		  int doneCount=8;
	  		  while(iter.hasNext()){
	  			
	  			  TaskFileData tfdData1=(TaskFileData) iter.next();
	  			  doneCount=Integer.parseInt(tfdData1.getDoneCount());
	  			  System.out.println();
	  			  doneCount=doneCount+1;

	  			  tfdData1.setDoneCount(doneCount+"");
	  			  Transaction tx1 = tDao.getSession().beginTransaction();
	  			  tDao.merge(tfdData1);
	  			  tx1.commit();
	  			  tDao.getSession().flush();
		  		  tDao.getSession().close();
	  		  }
	  		
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
