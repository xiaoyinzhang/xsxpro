package shu.nova.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Transaction;

import shu.nova.model.DuplicateData;
import shu.nova.model.DuplicateDataDAO;
import shu.nova.model.LongLatData;
import shu.nova.model.LongLatDataDAO;
import shu.nova.model.TaskFileData;
import shu.nova.model.TaskFileDataDAO;
import shu.nova.tools.OperateExcel;

public class UploadFile extends HttpServlet {

	
	/**
	 * Constructor of the object.
	 */
	public UploadFile() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("-------------------------------------------");
	 //要在最后加上斜杠:temp/
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/databases"+path;
		String back=basePath+"/index.jsp";
		
		System.out.println("path========="+path);
		System.out.println("basePath========="+basePath);
		UUID uuid=UUID.randomUUID();
		String uuidStr=uuid.toString();
		String tempDirectory = path+"/temp/";   
		//String mainPath="C:/Program Files/Apache Software Foundation/Tomcat 8.0/webapps";
		String mainPath="/usr/local/apache-tomcat-8.0.27/webapps/databases";
		
		String xlsName=mainPath+path+"/XSL-FILE/";
		
		String xmlFile=mainPath+path+"/XML-FILE/";
		String xlsPath=mainPath+path+"/XLS_DOWNLOAD/";
		String xlsDownloadPath=basePath+"/XLS_DOWNLOAD/";
		
		String txtPath=mainPath+path+"/TXT_DOWNLOAD/";
		String txtDownloadPath=basePath+"/TXT_DOWNLOAD/";
		
		
		if(basePath.startsWith("http://localhost:")){
			xlsDownloadPath=xlsDownloadPath.replace("localhost", "mysite.local");
			txtDownloadPath=txtDownloadPath.replace("localhost", "mysite.local");
		}
		
		try {
			int sizeThreshold = 1024 * 1024*64;  //写满该大小的缓存后，存入硬盘中。
			File repositoryFile = new File(tempDirectory);
			FileItemFactory factory = new DiskFileItemFactory(sizeThreshold, repositoryFile);
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(500 * 1024 * 1024); // set every upload file'size less than 500M
			upload.setHeaderEncoding("utf-8");

			
			List items = upload.parseRequest(request);   //这里开始执行上传
			Iterator iter = items.iterator();
			String fileName ="";
			String fileName2="";
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();   //FileItem就是表示一个表单域。
				
				if(item.isFormField()){ //isFormField方法用于判断FileItem是否代表一个普通表单域(即非file表单域)
					System.out.println("***"+item.getFieldName());	
				}else {
					String fieldName = item.getFieldName();  //获取表单域name属性的值
					fileName = item.getName();     //返回该文件在客户机上的文件名。e.g: e:\dianying\\video\1.wmv
					if(fileName.equals("")||fileName==null||fileName.isEmpty()||fileName==""){
						//System.out.println("type="+type);
						response.sendRedirect("typeError.jsp");
						return;
					}
					System.out.println("*****"+fieldName);
					System.out.println("*****"+fileName);
					String path1 = item.getName();
					String type=path1.substring(path1.lastIndexOf(".")+1);
					System.out.println("type="+type);
					if(!(type.equals("xls")||type.equals("xlsx"))){
						System.out.println("type="+type);
						response.sendRedirect("typeError.jsp");
						return;
					}
					Timestamp ts=new Timestamp(System.currentTimeMillis());
					fileName = path1.substring(path1.lastIndexOf("\\")+1);
					System.out.println(path1+"---------"+fileName);
					 //System.out.println(System.getProperty("user.home"));
					fileName2=fileName.substring(0,fileName.lastIndexOf("."));
					System.out.println("fileName2="+fileName2);
					String xmlFileName=uuidStr+"_"+fileName;
					xlsName=xlsName + xmlFileName;
					File uploadedFile = new File(xlsName);
					item.write(uploadedFile);
				}
			}	
			txtDownloadPath=txtDownloadPath+fileName2+"_"+uuidStr.substring(0, 6)+".txt";
			xlsDownloadPath=xlsDownloadPath+fileName2+"_"+uuidStr.substring(0, 6)+".xls";
			xmlFile=xmlFile+uuidStr+"_geocoding.xml";
			Timestamp ts=new Timestamp(System.currentTimeMillis());
			TaskFileData tfdData=new TaskFileData(xlsName,uuidStr,ts,"OK","0%",xmlFile,txtPath,txtDownloadPath,xlsPath,xlsDownloadPath,fileName,"0","0");
			TaskFileDataDAO tfdDao=new TaskFileDataDAO();
			Transaction tx = tfdDao.getSession().beginTransaction();
			tfdDao.save(tfdData);
	  		tx.commit();
	  		tfdDao.getSession().close();
	  		
			response.sendRedirect("welcome.action");

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
	
	}

}
