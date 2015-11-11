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
  <!-- 
  <body style="background-image:url(PHOTO/0ff41bd5ad6eddc48c0e79853bdbb6fd536633e7.jpg)">
   -->
   <body　bgcolor="rgb(200,200,150)">
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
  		  单地址搜索	（例如：输入上海大学）<br>	
		 文件搜索	（选择包含地址的EXCEL文件）
	</div> 
	<br>
	<div id="tasks">
		<form action="welcome.action" method="post">
  			<input type="submit" value="刷新查看数据" name="submitSingle" style="width: 120px"	/> 
  		</form>
	   <table border="1">
	   		<tr><td>序号</td><td>文件名</td><td>总共地址数目</td><td>解析完成数目</td><td>状态</td><td>任务创建时间</td><td>导出为TXT文件</td><td>导出为EXCEL文件</td></tr>
		    <s:iterator value="tasks">
			    <tr>
			    <td><s:property value="id"/></td>
			    <td><s:property value="fileName"/></td>
			    <td><s:property value="totalCount"/></td>
			    <td><s:property value="doneCount"/></td>
			    <td><s:property value="status"/></td>
			    <td><s:property value="crateTime"/></td>

			    <td align="center">
				    <form action="exportTexData.action" method="post">
					<input name="uuid" type="hidden" value="<s:property value="uuid"/>" style="width: 500px" />
					<input name="txtPath" type="hidden" value="<s:property value="txtPath"/>" style="width: 500px" />
					<input name="txtDownloadPath" type="hidden" value="<s:property value="txtDownloadPath"/>" style="width: 500px" />
					<input name="fileName" type="hidden" value="<s:property value="fileName"/>" style="width: 500px" />
					<input type="submit" value="导出TXT" name="submitText" style="width: 80px"	/>	
					</form>
			    </td>
			    <td align="center">
				    <form action="exportData.action" method="post">
					<input name="uuid" type="hidden" value="<s:property value="uuid"/>" style="width: 500px" />
					<input name="xlsPath" type="hidden" value="<s:property value="xlsPath"/>" style="width: 500px" />
					<input name="xlsDownloadPath" type="hidden" value="<s:property value="xlsDownloadPath"/>" style="width: 500px" />
					<input name="fileName" type="hidden" value="<s:property value="fileName"/>" style="width: 500px" />
					<input type="submit" value="导出EXCEL" name="submitExcel" style="width: 80px"	/>	
					</form>
			    </td>
			    </tr>
		    
		    </s:iterator>
    
    </table>
	
	</div>
	</center>
	<!-- 
	<img alt="123" src="PHOTO/0ff41bd5ad6eddc48c0e79853bdbb6fd536633e7.jpg">
	 -->
	
  </body>
</html>
