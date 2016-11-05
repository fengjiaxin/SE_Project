<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/Register.css"/>
<title>学生注册</title>
</head>
<body class="bodyback">
<h1>用户注册</h1>
	<div class="center">
	<center>
		<s:form action="studentregister" method="post">
			<s:textfield type="text" name="UserName" label="用户名："></s:textfield>
			<s:textfield type="password" name="Password" label="密码："></s:textfield>
			<s:textfield name="StudentId" label="学号："></s:textfield>
			<s:textfield name="Name" label="真实姓名："></s:textfield>
			<s:radio list="#{'1':'男','0':'女'}" name="Sex" value='1' label="性别："></s:radio>
			<s:textfield name="Age" label="年龄："></s:textfield>
			<s:textfield name="Telephone" label="手机号："></s:textfield>
			<s:textfield name="Email" label="邮箱："></s:textfield>
			<s:submit value="注册"></s:submit>
		</s:form>
	</center>
	</div>
</body>
</html>