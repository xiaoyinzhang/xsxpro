package shu.cloud.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;



import shu.cloud.model.DuplicateData;
import shu.cloud.model.DuplicateDataDAO;
import shu.cloud.model.LongLatData;
import shu.cloud.model.LongLatDataDAO;

public class OperateTxt {

	public static void createText(String txtFile,String uuid){
		try
		{
			String seprator="\t";
			String seprator2="\t\t\t\t";
		       System.out.println("txtFile="+txtFile);
		       File file=new File(txtFile);	
		       if(!file.exists())
		             file.createNewFile();

		       FileOutputStream fos = new FileOutputStream(file);
		       StringBuilder sb = new StringBuilder();    	  
		       sb.append("地址");
		       sb.append(seprator2+"\t\t");
		       sb.append("纬度");
		       sb.append(seprator);
		       sb.append("经度");
		       sb.append(seprator);
		       sb.append("是否精确查找");
		       sb.append(seprator);
		       sb.append("可信度");
		       sb.append(seprator);
		       sb.append("级别\r\n");
		       
		       fos.write(sb.toString().getBytes("utf-8"));

			LongLatDataDAO llddDao=new LongLatDataDAO();
			List<LongLatData> list=llddDao.findByUuid(uuid);
			//List<LongLatData> list=llddDao.findAll();
			
			DuplicateData ddData=null;
	 		DuplicateDataDAO ddDao=new DuplicateDataDAO();
			List listdd=ddDao.findByUuid(uuid);
			for(int i=0;i<listdd.size();i++){
				ddData=(DuplicateData) listdd.get(i);
				String addrName=ddData.getAddrName();
				List listll=llddDao.findByAddrName(addrName);
				list.addAll(listll);
			}
			
			System.out.println("size==="+list.size());
			Iterator<LongLatData> iter1 = list.iterator();
			
			int i=1;
			while (iter1.hasNext()) {
				LongLatData item = iter1.next(); 
				
				StringBuilder sbi = new StringBuilder();
				sbi.append(item.getAddrName());
				sbi.append(seprator2);
				sbi.append(item.getLatitude());
				sbi.append(seprator);
				sbi.append(item.getLongitude());
				sbi.append(seprator);
				sbi.append(item.getPrecise());
				sbi.append(seprator);
				sbi.append(item.getConfidence());
				sbi.append(seprator);
				sbi.append(item.getLevel()+"\r\n");
				fos.write(sbi.toString().getBytes("UTF-8"));
				i++;
			}
			fos.flush();
			fos.close();
			System.out.println("txt文件生成...");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
