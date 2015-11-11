<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String back=basePath+"/index.html";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'typeError.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body  style="background-image:url(PHOTO/1e30e924b899a9012d78d4441f950a7b0208f577.jpg)">
    <center>
   		 您选择的文件格式错！<br>
   		 请选择含有地址的xls或xlsx格式的excel文件……
   		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		<a href="<%=back%>">返回</a>
    </center>
  </body>
</html>
