package shu.nova.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.hibernate.Transaction;

import shu.nova.model.DuplicateData;
import shu.nova.model.DuplicateDataDAO;
import shu.nova.model.LongLatData;
import shu.nova.model.LongLatDataDAO;
import shu.nova.model.TaskFileData;
import shu.nova.model.TaskFileDataDAO;


public class OperateExcelXLS {

	public static void createExcel(String xlsFile,String uuid){
		try
		{
			HSSFWorkbook workbook = new HSSFWorkbook(); 
			//HSSFWorkbook workbook = new HSSFWorkbook(); 
			HSSFSheet sheet = workbook.createSheet(); 
			workbook.setSheetName(0,"firstSheet");
			//sheet.setColumnWidth(0, 40);
			//sheet.setColumnWidth(1, 50);
			//sheet.setColumnWidth(2, 60);
			//sheet.setColumnWidth(3, 70);
			//sheet.setColumnWidth(4, 80);
			//sheet.setColumnWidth(5, 90);
			
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
			HSSFRow row =null;
			
			//HSSFCell cell1=null;
			HSSFCell cell7 =null;
			HSSFCell cell6 =null;
			HSSFCell cell5 =null;
			HSSFCell cell4 =null;
			HSSFCell cell3 =null;
			HSSFCell cell2 =null;
			HSSFCell cell1 =null;
			
			row = sheet.createRow(0);
			
			cell1= row.createCell(0);
			
			cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell1.setCellValue("地址");
			
			cell2= row.createCell(1);	
			cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell2.setCellValue("状态");
			
			
			cell3= row.createCell(2);
			cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell3.setCellValue("纬度");
			
			cell4= row.createCell(3);
			cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell4.setCellValue("经度");
			
			cell5= row.createCell(4);
			cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell5.setCellValue("是否精确查找");
			
			cell6= row.createCell(5);
			cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell6.setCellValue("可信度");
			
			cell7= row.createCell(6);
			cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell7.setCellValue("级别");
			
			int i=1;
			while (iter1.hasNext()) {
				LongLatData item = iter1.next(); 
				
				row = sheet.createRow(i);
				/*
				cell1= row.createCell(0);
				cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell1.setCellValue(""+(i+1));
				*/
				cell1= row.createCell(0);
				cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell1.setCellValue(item.getAddrName());
				
				cell2= row.createCell(1);
				cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell2.setCellValue(item.getStatus());
				
				cell3= row.createCell(2);
				cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell3.setCellValue(item.getLatitude());
				
				cell4= row.createCell(3);
				cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell4.setCellValue(item.getLongitude());
				
				cell5= row.createCell(4);
				cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell5.setCellValue(item.getPrecise());
				
				cell6= row.createCell(5);
				cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell6.setCellValue(item.getConfidence());
				
				cell7= row.createCell(6);
				cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell7.setCellValue(item.getLevel());

				i++;
			}
			
			
			
			
			FileOutputStream fOut = new FileOutputStream(xlsFile);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
			System.out.println("文件生成...");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static int readExcel(String xlsFile,String uuid,String xmlFile){
		int doneSum=0;
		try
		{

			FileInputStream fIn=new FileInputStream(xlsFile);
			HSSFWorkbook readWorkBook= new HSSFWorkbook(fIn);
			HSSFSheet readSheet=readWorkBook.getSheetAt(0);
			HSSFRow readRow =null;
			HSSFCell readCell=null;	
			
	
			int rowCount=readSheet.getLastRowNum()+1;
			System.out.println("rowCount="+rowCount);
			
			TaskFileDataDAO tDao = new TaskFileDataDAO();
	  		List list1=tDao.findByUuid(uuid);
	  		Iterator iter=list1.iterator();
	  		while(iter.hasNext()){
	  			TaskFileData tfdData=(TaskFileData) iter.next();

	  			tfdData.setTotalCount(""+rowCount);
	  			Transaction tx1 = tDao.getSession().beginTransaction();
	  			tDao.merge(tfdData);
	  			tx1.commit();
	  			tDao.getSession().flush();
		  		tDao.getSession().close();
	  		}
			
	  		
			for(int i=0;i<rowCount;i++)
			{
				System.out.println("rowCount="+rowCount);
				System.out.println("i=="+i);
				String rowStr="";
				readRow =readSheet.getRow(i);
				if(readRow!=null){
					int colCount=readRow.getLastCellNum()+1;
					System.out.println("colCount="+colCount);
					//readRow.
					for(int j=0;j<colCount;j++){
						//System.out
						readCell=readRow.getCell(j);
						if(readCell!=null){
							
							int cellType=readCell.getCellType();
							if(cellType==HSSFCell.CELL_TYPE_STRING){
								rowStr=rowStr+readCell.getStringCellValue();
							}else if(cellType==HSSFCell.CELL_TYPE_NUMERIC){
								rowStr=rowStr+readCell.getNumericCellValue();
							}else if(cellType==HSSFCell.CELL_TYPE_BLANK){
								rowStr=rowStr+"";
							}else if(cellType==HSSFCell.CELL_TYPE_BOOLEAN){
								rowStr=rowStr+readCell.getBooleanCellValue();
							}else if(cellType==HSSFCell.CELL_TYPE_ERROR){
								rowStr=rowStr+readCell.getErrorCellValue();
							}else if(cellType==HSSFCell.CELL_TYPE_FORMULA){
								rowStr=rowStr+readCell.getCachedFormulaResultType();
							}
								
									
							System.out.println("j=="+j);
						}
						
						
					}
					System.out.println("rowStr="+rowStr);		
			        String addrName=URLEncoder.encode(rowStr, "UTF-8");
			        String cityName=URLEncoder.encode("上海", "UTF-8");
			        String searchStr="http://api.map.baidu.com/geocoder?address="+addrName+"&output=xml&key=37492c0ee6f924cb5e934fa08c6b1676&city="+cityName;	        
					SearchLongLatitude sll=new SearchLongLatitude();
					sll.SendURLPost(searchStr,xmlFile);			
					AddSearchResultToDB.init(xmlFile,rowStr,uuid);
					doneSum++;
				}			
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			return doneSum;
		}
		return doneSum;
	}
	
}
