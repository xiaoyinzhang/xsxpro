package shu.nova.control;

import com.opensymphony.xwork2.ActionSupport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

import shu.nova.model.LongLatData;
import shu.nova.tools.AddSearchResultToDB;
import shu.nova.tools.OperateXml;
import shu.nova.tools.SearchLongLatitude;

public class SearchSingle extends ActionSupport {

	private String singleText;
	private String path;
	private String searchResult="";
	
	private String addrName2="";
	private String status="";
	private String latitude="";
	private String longitude="";
	private Boolean precise=true;
	private String confidence="";
	private String level="";
	
	public SearchSingle() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		UUID uuid=UUID.randomUUID();
		//String xmlFile="D:\\xyzhang\\myeclipse_project\\Geocoding11\\WebRoot\\XML-FILE\\";
		//String xmlFile="C:/Program Files/Apache Software Foundation/Tomcat 8.0/webapps"+path+"/XML-FILE/";
		String xmlFile="/home/xsx/Project/data/"+path+"/XML-FILE/";
		try{
			
			System.out.println("------------------------------------------------------------");
			
			SearchLongLatitude sll=new SearchLongLatitude();
			
			String addrName=URLEncoder.encode(singleText, "UTF-8");
	        String cityName=URLEncoder.encode("上海", "UTF-8");
	        String searchStr="http://api.map.baidu.com/geocoder?address="+addrName+"&output=xml&key=37492c0ee6f924cb5e934fa08c6b1676&city="+cityName;	        
		
	        xmlFile=xmlFile+uuid.toString()+"_geocoding.xml";
	        searchResult=sll.SendURLPost(searchStr,xmlFile);	
			AddSearchResultToDB.init(xmlFile,singleText,uuid.toString());
			LongLatData lldData=OperateXml.getContent(xmlFile, addrName, uuid.toString());
			
			addrName2=URLDecoder.decode(lldData.getAddrName(), "UTF-8");
			status=lldData.getStatus();
			latitude=lldData.getLatitude();
			longitude=lldData.getLongitude();
			precise=lldData.getPrecise();
			confidence=lldData.getConfidence();
			level=lldData.getLevel();
			
			
		}catch(IOException e){
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	

	public String getSingleText() {
		return singleText;
	}

	public void setSingleText(String singleText) {
		this.singleText = singleText;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(String searchResult) {
		this.searchResult = searchResult;
	}

	

	public String getAddrName2() {
		return addrName2;
	}

	public void setAddrName2(String addrName2) {
		this.addrName2 = addrName2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Boolean getPrecise() {
		return precise;
	}

	public void setPrecise(Boolean precise) {
		this.precise = precise;
	}

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	
}
