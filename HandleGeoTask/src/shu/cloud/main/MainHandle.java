package shu.cloud.main;

import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import shu.cloud.model.TaskFileData;
import shu.cloud.model.TaskFileDataDAO;
import shu.cloud.tools.OperateExcel;
import shu.cloud.tools.OperateExcelXLS;

public class MainHandle  extends TimerTask {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("timer");
		TaskFileDataDAO tfdDao=new TaskFileDataDAO();
		List list=tfdDao.findByStatus("OK");
		Iterator iter=list.iterator();
		while(iter.hasNext()){
			
			TaskFileData tfdData=(TaskFileData) iter.next();
			tfdData.setStatus("RUNNING");
			tfdDao.merge(tfdData);
			
			String xlsFile=tfdData.getFilePath();
			String type=xlsFile.substring(xlsFile.lastIndexOf(".")+1);
			System.out.println("type="+type);
			
			int doneSum=0;
			if((type.equals("xls"))){
				doneSum=OperateExcelXLS.readExcel(tfdData.getFilePath(),tfdData.getUuid(),tfdData.getXmlPath());
			}else if(type.equals("xlsx")){
				doneSum=OperateExcel.readExcel(tfdData.getFilePath(),tfdData.getUuid(),tfdData.getXmlPath());
			}
			
			System.out.println("Running");
			tfdData.setStatus("DONE");
			tfdDao.merge(tfdData);
			System.out.println("done");
			tfdDao.getSession().flush();
			
			TaskFileDataDAO tDao2 = new TaskFileDataDAO();
	 		List list1=tDao2.findByUuid(tfdData.getUuid());
	 		  Iterator iter1=list1.iterator();
	 		  while(iter1.hasNext()){
	 			  TaskFileData tfdData1=(TaskFileData) iter1.next();
	 			  tfdData1.setDoneCount(doneSum+"");
	 			  tfdData1.setTotalCount(doneSum+"");
	 			  tDao2.merge(tfdData1);
	 			  tDao2.getSession().flush();
	 			 
	 		  }
		}
		
		
	}

}
