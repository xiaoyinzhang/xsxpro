<%@ page language="java" import="java.util.*,shu.nova.model.*,shu.nova.tools.*,org.hibernate.Transaction,java.io.PrintWriter;"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body style="background-image:url(PHOTO/0ff41bd5ad6eddc48c0e79853bdbb6fd536633e7.jpg)">
    <center>
    <div>
    	<br>
    	<h1>位置映射</h1>
    	<h2>地址　－〉纬度、经度</h2>
    </div>
    <div  id="singleSearch" style="acenter">
   		
  		<form action="singleSearch.action" method="post">
  			
  			<input name="singleText" type="text" value="上海大学" style="width: 500px" />
  			<!-- 
  			<select name="city">
  				<option　value="上海">上海</option>
  				<option　value="北京">北京</option>
  				<option　value="天津">天津</option>
  				<option　value="重庆">重庆</option>
  			</select>
  			 -->
  			<input name="path" type="hidden" value="<%=path%>" style="width: 500px" />
  			<input type="submit" value="搜索" name="submitSingle" style="width: 80px"	/> 
  		</form>
	</div>
	<div  id="fileSearch">
		
		<form enctype="multipart/form-data"  method="post"  action="UploadFile">
			
   			<input type="file" name="file1" style="width: 500px"/>
   			<input name="path" type="hidden" value="<%=path%>" style="width: 500px" />
   			<input type="submit" name="submit1" value="搜索" style="width: 80px"/>
		</form>
		<!-- 
  		<form action="fileSearch.action" method="post">
  			<input name="fileText" type="file" value="37492c0ee6f924cb5e934fa08c6b167" style="width: 500px" />
  			<input type="submit" value="搜索" name="submitFile" style="width: 80px"	/>	
  		</form>
  		 -->
	</div> 
	<div id="searchResult">
		<%
		String flag=(String)request.getAttribute("flag");
		String uuidStr=(String)request.getAttribute("uuidStr");
		String txtPath=(String)request.getAttribute("txtPath");
		String txtDownloadPath=(String)request.getAttribute("txtDownloadPath");
		String xlsPath=(String)request.getAttribute("xlsPath");
		String xlsDownloadPath=(String)request.getAttribute("xlsDownloadPath");
		String back=(String)request.getAttribute("back");
		System.out.println("uuidStr="+uuidStr);
		System.out.println("flag="+flag);
		if(flag==null){
		
		}else if(flag.equals("upload")||flag.equals("refresh")){
		
			
			
			System.out.println("uuidStr="+uuidStr);
			System.out.println("flag");
			
			LongLatDataDAO llddDao=new LongLatDataDAO();
			Transaction tx1 = llddDao.getSession().beginTransaction();
			@SuppressWarnings("unchecked")
			List<LongLatData> list=llddDao.findByUuid(uuidStr);
			//@SuppressWarnings("unchecked")
			//List<LongLatData> list=llddDao.findAll();
	 		tx1.commit();
	 		llddDao.getSession().close();
	 		
	 		DuplicateData ddData=null;
	 		DuplicateDataDAO ddDao=new DuplicateDataDAO();
			List listdd=ddDao.findByUuid(uuidStr);
			for(int i=0;i<listdd.size();i++){
				ddData=(DuplicateData) listdd.get(i);
				String addrName=ddData.getAddrName();
				List listll=llddDao.findByAddrName(addrName);
				list.addAll(listll);
			}
			
	        System.out.println("size==="+list.size());
	        Iterator<LongLatData> iter1 = list.iterator();
	        %>
			
			<form action="RefreshData" method="post">
				<input name="uuid" type="hidden" value="<%=uuidStr %>" style="width: 500px" />
				<input name="txtPath" type="hidden" value="<%=txtPath %>" style="width: 500px" />
				<input name="txtDownloadPath" type="hidden" value="<%=txtDownloadPath %>" style="width: 500px" />
				<input name="xlsPath" type="hidden" value="<%=xlsPath %>" style="width: 500px" />
				<input name="xlsDownloadPath" type="hidden" value="<%=xlsDownloadPath %>" style="width: 500px" />
				<input name="back" type="hidden" value="<%=back %>" style="width: 500px" />
				<input type="submit" value="刷新" name="refresh" style="width: 80px"	/>	
			</form>
			
			<form action="exportTexData.action" method="post">
				<input name="uuid" type="hidden" value="<%=uuidStr %>" style="width: 500px" />
				<input name="txtPath" type="hidden" value="<%=txtPath %>" style="width: 500px" />
				<input name="txtDownloadPath" type="hidden" value="<%=txtDownloadPath %>" style="width: 500px" />
				<input type="submit" value="导出TXT" name="submitText" style="width: 80px"	/>	
			</form>
			<form action="exportData.action" method="post">
				<input name="uuid" type="hidden" value="<%=uuidStr %>" style="width: 500px" />
				<input name="xlsPath" type="hidden" value="<%=xlsPath %>" style="width: 500px" />
				<input name="xlsDownloadPath" type="hidden" value="<%=xlsDownloadPath %>" style="width: 500px" />
				<input type="submit" value="导出EXCEL" name="submitExcel" style="width: 80px"	/>	
			</form>
			
			
			<a href="<%=back %>">首页</a>
			<table border="1">
			<tr>
			<td>序号</td><td>地址</td><td>纬度</td><td>经度</td><td>是否精确查找</td><td>置信度</td><td>级别</td>
			</tr>
			<%
			int i=1;
			while (iter1.hasNext()) {
				
				LongLatData item = iter1.next();
				%> 
				<tr>
				
				<td>"+i+"</td>
				<td>"+item.getAddrName()+"</td>
				<td>"+item.getLatitude()+"</td>
				<td>"+item.getLongitude()+"</td>
				<td>"+item.getPrecise()+"</td>
				<td>"+item.getConfidence()+"</td>
				<td>"+item.getLevel()+"</td>		
				</tr>
				<%
				i++;
			}
			%>
			</table>
			<%
		}
		
		 %>
	</div>
	
	</center>
	<!-- 
	<img alt="123" src="PHOTO/0ff41bd5ad6eddc48c0e79853bdbb6fd536633e7.jpg">
	 -->
	 单地址搜索	（例如：输入上海大学）<br>	
	 文件搜索	（选择包含地址的EXCEL文件）
  </body>
</html>
