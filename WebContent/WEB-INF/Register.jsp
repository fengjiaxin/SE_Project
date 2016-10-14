<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>注册</title>
</head>
<body>
	<s:form action="register" method="post">
		<s:radio list="#{'1':'学生','0':'教师'}" name="Status" value='1'></s:radio>
		<s:input type="text" name="UserName" label="用户名："></s:input>
		<s:input type="password" name="Password" label="密码："></s:input>
		<s:input type="password" name="Password2" label="确认密码："></s:input>
		<s:input type="text" name="Email" label="邮箱："></s:input>
		<s:submit value="注册"></s:submit>
	</s:form>
</body>
</html>