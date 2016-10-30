<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	.bodyback{
	height: 600px;
	position: relative;}
	.signinform{
	position: absolute;
	height: 391px;
	width: 345px;
	top: 90px;
	right: 70px;
	}
	body{
	color: #444;
	min-height: 600px;
	height: 100%;
	overflow-y: scroll;
}
</style>


<title>Welcome</title>
</head>
<body >

	<div  class="bodyback" style="background-image:url('css/background.jpg')">
		<div class="signinform">
			<s:form action="sign_in" method="post">
				<s:textfield name="UserName" label="用户名："></s:textfield>
				<s:textfield type="password" name="Password" label="密码："></s:textfield>
				<s:radio list="#{'1':'学生','0':'教师'}" name="InStatus" value='1'></s:radio>
				<s:submit value="登录"></s:submit>
			</s:form>
			<s:form action="toregister" method="post">
				<s:radio list="#{'1':'学生','0':'教师'}" name="ReStatus" value='1'></s:radio>
			<s:submit value="注册"></s:submit>
			</s:form>
		</div>
	</div>
	
	
</body>
</html>