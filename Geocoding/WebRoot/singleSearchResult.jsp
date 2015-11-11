<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    
    <title>My JSP 'singleSearchResult.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="background-image:url(PHOTO/1e30e924b899a9012d78d4441f950a7b0208f577.jpg)">
  	<center>
   
    	地　址：<s:property value = "addrName2"/><br>
    	状　态：<s:property value = "status"/><br>
    	纬　度：<s:property value = "latitude"/><br>
    	经　度：<s:property value = "longitude"/><br>
    	精确度：<s:property value = "precise"/><br>
    	置信度：<s:property value = "confidence"/><br>
    	级　别：<s:property value = "level"/><br>
    <a href="<%=back%>">返回</a>
    </center>
  </body>
</html>
