<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Not Exist</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/backround.css"/>
</head>
<body>

	<div>
	 

	 <s:if test="2==2">
	 <div>
	     申请列表
	 </div> 
	 </s:if>
	 <s:else> 
	<s:if test="2==2"> 
	<div >
	
	<span title=<s:property value="student.Name"  default="您有未读取的新消息"/>><s:property value="student.Name"  default="申请列表 "/></span><img src="<%=request.getContextPath()%>/css/1.png"  height="15" width="15"/>
	  
	</div>
	</s:if>
	
	</s:else>
	<s:if test="3=">
	<s:property value="3"/>
	 </s:if>
	 </div>
</body>
</html>