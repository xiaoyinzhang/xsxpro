package shu.nova.test;

import shu.nova.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Transaction;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;
import javax.xml.xpath.*;

public class Test {
    public static void main(String[] args) {
       init("");
    }
   public static void init(String path){
	   DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
       Element theBook=null, theElem=null, root=null;
       try {
           factory.setIgnoringElementContentWhitespace(true);
           
           DocumentBuilder db=factory.newDocumentBuilder();
           Document xmldoc=db.parse(new File("D:\\xyzhang\\myeclipse_project\\Test01\\geocoding.xml"));
           root=xmldoc.getDocumentElement();
           output(root);

           Element resultDom=(Element)root.getElementsByTagName("result").item(0);
           Element locationDom=(Element)resultDom.getElementsByTagName("location").item(0);
           
           String status=root.getElementsByTagName("status").item(0).getTextContent();
           String latitude=locationDom.getElementsByTagName("lat").item(0).getTextContent();
           String longitude=locationDom.getElementsByTagName("lng").item(0).getTextContent();
           String precise=resultDom.getElementsByTagName("precise").item(0).getTextContent();
           String confidence=resultDom.getElementsByTagName("confidence").item(0).getTextContent();
           String level=resultDom.getElementsByTagName("level").item(0).getTextContent();
 
      
          System.out.println("status="+status);
          System.out.println("latitude="+latitude);
          System.out.println("longitude="+longitude);
          System.out.println("precise="+precise);
          System.out.println("confidence="+confidence);
          System.out.println("level="+level);
          boolean bprecise=true;
          
          if(precise.equals("0")){
        	  bprecise=false;
          }
          
          Timestamp ts=new Timestamp(System.currentTimeMillis());
          UUID uuid=UUID.randomUUID();
          
          LongLatData lldData=new LongLatData("在北京市内根据“上地十街10号”",status,latitude,longitude, bprecise,confidence,level, "singlesearch", ts, uuid.toString());
          
          LongLatDataDAO lldDao=new LongLatDataDAO();  
           
          Transaction tx = lldDao.getSession().beginTransaction();
          lldDao.save(lldData);
  			tx.commit();
  			lldDao.getSession().close();
  			List list=lldDao.findAll();
          System.out.println("size==="+list.size());
       } catch (ParserConfigurationException e) {
           e.printStackTrace();
       } catch (SAXException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
    

   public static void output(Node node) {//��node��XML�ַ����������̨
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
    
    public static Node selectSingleNode(String express, Object source) {//���ҽڵ㣬�����ص�һ����������ڵ�
       Node result=null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        try {
            result=(Node) xpath.evaluate(express, source, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public static NodeList selectNodes(String express, Object source) {//���ҽڵ㣬���ط�������Ľڵ㼯��
       NodeList result=null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        try {
            result=(NodeList) xpath.evaluate(express, source, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public static void saveXml(String fileName, Document doc) {//��Document������ļ�
       TransformerFactory transFactory=TransformerFactory.newInstance();
        try {
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");

           DOMSource source=new DOMSource();
            source.setNode(doc);
            StreamResult result=new StreamResult();
            result.setOutputStream(new FileOutputStream(fileName));
            
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }   
    }

}
