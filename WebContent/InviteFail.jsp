<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>InviteFail!</title>
</head>
<body>
           该生已完成师生互选，无法再被邀请！
	<a href = <s:url value="teacherpagedisplay.action">
	<s:param name="Id" value="getId()"></s:param> 
	</s:url>>返回个人主页</a>	
</body>
</html>