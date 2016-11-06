<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student message update</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/backround.css"/>
</head>


    
    <div class="header">
	    <s:property value="Name"  default=" "/>
	    <i><s:property value="Email"  default=" "/></i>
	    <br><s:property value="Telephone"  default=" "/>
	    <s:property value="Age"  default=" "/>
	    <s:property value="Sex"  default=" "/>
    </div>
    
	<div class="main" >
	     <div class="option">
	     <table  width="100%" border="2" cellpadding="49" cellspacing="1" >
		      <tr><td><a href=<s:url action="TeacherCompletePersonalInformation">
		      <s:param name="Id" value="getId()"></s:param>
			 </s:url>>个人信息完善</a></td></tr>
		      <tr><td><s:form action="TeacherInquiry" method="post">
			    <s:select name="AcademyId" list="Mylist" label="学院" listValue="Name" listKey="id"></s:select>
			    <s:textfield name="Id" type="hidden" value="%{#request.Id}"></s:textfield>
			<s:submit value="查询"></s:submit></s:form></td></tr>
		     <tr><td><a href=<s:url action="ApplicationList">
			 </s:url>>申请列表</a></td></tr>
			<tr><td><a href=<s:url action="SystemreCommendation">
			 </s:url>>系统推荐</a></td></tr>
			 </table>
	     </div>
	     
	    <div class="list">
			   学院       <s:property value="Academy"  default=" "/><br>
			    研究方向 <s:property value="Research"  default="  "/><br>
			   论文  <s:property value="Article"  default=" "/><br>
			   经历  <s:property value="Experience"  default=" "/><br>
			   荣誉  <s:property value="Honor"  default=" "/><br> 
			   招生人数 <s:property value="NeedNum"  default=" "/><br>
			  招生需求<s:property value="Needs"  default=" "/> 
	    </div>
	</div>
	<div class="footer">底部</div>




