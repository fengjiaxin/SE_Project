<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>学生注册</title>
</head>
<body>
	<s:form action="studentregister" method="post">
		<s:textfield type="text" name="UserName" label="用户名："></s:textfield>
		<s:textfield type="password" name="Password" label="密码："></s:textfield>
		<s:textfield type="password" name="Password2" label="确认密码："></s:textfield>
		<s:textfield type="text" name="Email" label="邮箱："></s:textfield>
		<s:textfield name="StudentId" label="学生号："></s:textfield>
		<s:textfield name="Name" label="真实姓名："></s:textfield>
		<s:radio list="#{'1':'男','0':'女'}" name="Sex" value='1'></s:radio>
		<s:textfield name="Age" label="年龄："></s:textfield>
		<s:textfield name="Telephone" label="手机号："></s:textfield>
		<s:select name="Specialty" label="专业:" headerKey="-1" headerValue="Select Specialty"
		list="#{'计算机科学与技术', '信息安全', '物联网', '软件工程'}"  />
		<s:textfield name="Introduce" label="自我介绍："></s:textfield>
		<s:submit value="注册"></s:submit>
	</s:form>
</body>
</html>