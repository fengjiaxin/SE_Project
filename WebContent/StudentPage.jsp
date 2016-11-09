<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>StudentPage</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/HomePage.css"/>
</head>


    
    <div class="header">
	    <div class="headerleft"><h1>学生主页</h1></div>
	    <div class="headerright">
	    														<br>
		    姓名：<s:property value="student.Name"  default=" "/>
		    性别：<s:property value="student.Sex"  default=" "/>
		    年龄：<s:property value="student.Age"  default=" "/><br>
		   
		   													  
		     邮箱：<s:property value="student.Email"  default=" "/>
		    电话：<s:property value="student.Telephone"  default=" "/><br>
		</div>
    </div>
    
	<div class="content" >
		<div class="main">
		     学院 ：<s:property value="student.Academy"  default=" "/><br>
	    专业：<s:property value="student.Major"/><br>
	       感兴趣的方向：<s:property value="student.Intest"/><br>
		   经历:<s:property value="student.Experience"  default=" "/><br>
		  荣誉 :<s:property value="student.Honor"  default=" "/><br> 
		 本科成绩：<s:property value="student.Point"  default=" "/>
		 <s:form name="teacherask">
		  	<s:textfield name="StudentId" type="hidden" value="%{#request.StudentId}"></s:textfield>
		  	<s:textfield name="TeacherId" type="hidden" value="%{#request.TeacherId}"></s:textfield>
		  	<s:submit class="button" value="邀请"></s:submit>
		  </s:form>
		  </div>
	</div>
	<div class="footer">底部</div>

