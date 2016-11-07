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
		      <tr><td><a href=<s:url action="StudentCompletePersonalInformation">
		      <s:param name="Id" value="getId()"></s:param>
			 </s:url>>个人信息完善</a></td></tr>
		      <tr><td><s:form action="StudentInquiry" method="post">
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
	    <table border="1" width="50%" cellpadding="0" cellspacing="0" align="center">  
			<tr style="background-color: yellow">
				<td align="center">姓名</td>
				<td align="center">研究方向</td>
			</tr>  
			<s:iterator value="#request.List"> 
				<tr>
					<td align="center">
						<a href=
							<s:url value="TeacherDetails.action">
								<s:param name="TeacherId" value="Id"/>
							</s:url>>
							<s:property value="Name"/>
							</a>
					</td>
					<td align="center">					
							<s:property value="Research"/>
					</td>
				</tr>
			</s:iterator>
		</table>
	    </div>
	</div>
	<div class="footer">底部</div>

