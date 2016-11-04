<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/Welcome.css"/>

<title>Welcome</title>
</head>
<body >

	<div  class="bodyback" >
		<h1>考研师生互选系统</h1>
		<div class="signinform">
			<h2>SignIn</h2>
			<s:form action="sign_in" method="post">
				<s:textfield name="UserName" label="用户名："></s:textfield>
				<s:textfield type="password" name="Password" label="密码："></s:textfield>
				<s:radio list="#{'1':'学生','0':'教师'}" name="InStatus" value='1'></s:radio>
				<s:submit value="登录"></s:submit>
			</s:form>
			<h2>Register</h2>
			<s:form action="toregister" method="post">
				<s:radio list="#{'1':'学生','0':'教师'}" name="ReStatus" value='1'></s:radio>
			<s:submit value="注册"></s:submit>
			</s:form>
		</div>
	</div>
	
	
</body>
</html>