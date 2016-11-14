<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TeacherPage</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/HomePage.css"/>
</head>


    
    <div class="header">
	    <div class="headerleft"><h1>导师主页</h1></div>
	    <div class="headerright">
	    														
		    姓名：<s:property value="teacher.Name"  default=" "/>;
		    性别：<s:property value="teacher.Sex"  default=" "/>;
		    年龄：<s:property value="teacher.Age"  default=" "/>;
		   <br>
		   													  
		     邮箱：<s:property value="teacher.Email"  default=" "/>;
		    电话：<s:property value="teacher.Telephone"  default=" "/><br>
		     招生人数：<s:property value="teacher.NeedNum"  default=" "/>；是否已满：<s:if test="teacher.Status == 0">否</s:if> <s:else>是</s:else>
		</div>
    </div>
    
	<div class="content" >
		<div class="main">
		     学院 ：<s:property value="teacher.Academy"  default=" "/><br>
	      研究方向：<s:property value="teacher.Research"/><br>
	       发表论文 :<s:property value="teacher.Article"/><br>
		   经历:<s:property value="teacher.Experience"  default=" "/><br>
		  荣誉 :<s:property value="teacher.Honor"  default=" "/><br> 
		  招生需求：<s:property value="teacher.Needs"  default=" "/>
		  <s:form action="studentapply">
		  	<s:textfield name="StudentId" type="hidden" value="%{#request.StudentId}"></s:textfield>
		  	<s:textfield name="TeacherId" type="hidden" value="%{#request.TeacherId}"></s:textfield>
		  	<s:submit class="button" value="申请"></s:submit>
		  </s:form>
		  </div>
	</div>
	<div class="footer">@版权归 先锋战队所有 <br>详情请咨询454285842@qq.com</div>

