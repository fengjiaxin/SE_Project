<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
	<s:form action="sign_in" method="post">
		<s:textfield name="UserName" label="用户名："></s:textfield>
		<s:input type="password" name="Password" label="密码："></s:input>
		<s:radio list="#{'1':'学生','0':'教师'}" name="Status" value='1'></s:radio>
		<s:submit value="登录"></s:submit>
	</s:form>
	
	<s:form action="toregister" method="post">
		<s:submit value="注册"></s:submit>
	</s:form>
</body>
</html>