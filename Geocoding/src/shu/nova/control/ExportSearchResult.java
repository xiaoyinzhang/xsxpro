package shu.nova.control;

import java.util.Iterator;
import java.util.List;

import shu.nova.tools.OperateExcelXLS;
import shu.nova.model.LongLatData;
import shu.nova.model.LongLatDataDAO;
import shu.nova.tools.OperateExcel;

import com.opensymphony.xwork2.ActionSupport;

public class ExportSearchResult extends ActionSupport {

	private String uuid;
	private String xlsPath;
	private String xlsFile="";
	private String xlsDownloadPath;
	private String fileName;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stu
		try{
			System.out.println("uuid="+uuid);
			System.out.println("xlsPath="+xlsPath);
			
			fileName=fileName.substring(0,fileName.lastIndexOf("."));
			xlsPath=xlsPath+fileName+"_"+uuid.substring(0, 6)+".xls";
			//xlsPath=xlsPath+uuid+".xls";
			
			String type=xlsPath.substring(xlsPath.lastIndexOf(".")+1);
			System.out.println("type="+type);

			if((type.equals("xls"))){
				OperateExcelXLS.createExcel(xlsPath,uuid);
			}else if(type.equals("xlsx")){
				OperateExcel.createExcel(xlsPath,uuid);
			}
			
			xlsFile=xlsDownloadPath;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	
		return SUCCESS;
		
	}
	public String getUuid() {
		
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getXlsPath() {
		return xlsPath;
	}
	public void setXlsPath(String xlsPath) {
		this.xlsPath = xlsPath;
	}
	public String getXlsFile() {
		return xlsFile;
	}
	public void setXlsFile(String xlsFile) {
		this.xlsFile = xlsFile;
	}
	public String getXlsDownloadPath() {
		return xlsDownloadPath;
	}
	public void setXlsDownloadPath(String xlsDownloadPath) {
		this.xlsDownloadPath = xlsDownloadPath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

}
