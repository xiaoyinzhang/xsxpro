package shu.nova.control;

import shu.nova.tools.OperateExcel;
import shu.nova.tools.OperateTxt;

import com.opensymphony.xwork2.ActionSupport;

public class ExportTxtData extends ActionSupport {
	private String uuid;
	private String txtPath;
	private String txtFile="";
	private String txtDownloadPath;
	private String fileName;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stu
		try{
			System.out.println("uuid="+uuid);
			System.out.println("txtPath="+txtPath);
			
			fileName=fileName.substring(0,fileName.lastIndexOf("."));
			txtPath=txtPath+fileName+"_"+uuid.substring(0, 6)+".txt";
			//txtPath=txtPath+uuid+".txt";
			
			OperateTxt.createText(txtPath, uuid);
			txtFile=txtDownloadPath;
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
	public String getTxtPath() {
		return txtPath;
	}
	public void setTxtPath(String txtPath) {
		this.txtPath = txtPath;
	}
	public String getTxtFile() {
		return txtFile;
	}
	public void setTxtFile(String txtFile) {
		this.txtFile = txtFile;
	}
	public String getTxtDownloadPath() {
		return txtDownloadPath;
	}
	public void setTxtDownloadPath(String txtDownloadPath) {
		this.txtDownloadPath = txtDownloadPath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


}
