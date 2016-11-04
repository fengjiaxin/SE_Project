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
	    <s:property value="Name"  default="郭延龙"/>
	    <i><s:property value="Email"  default="1078530334@qq.com"/></i>
	    <br><s:property value="Telephone"  default="18845099586"/>
	    <s:property value="Age"  default="23"/>
	    <s:property value="Sex"  default="男"/>
    </div>
    
	<div class="main" >
	     <div class="option">
	     <table  width="100%" border="2" cellpadding="49" cellspacing="1" >
		      <tr><td><a href=<s:url action="CompletePersonalInformation">
		      <s:param name="Id" value="getId()"></s:param>
			 </s:url>>个人信息完善</a></td></tr>
		      <tr><td><a href=<s:url action="StudentInquiry">
			 </s:url>>查询</a></td></tr>
		     <tr><td><a href=<s:url action="ApplicationList">
			 </s:url>>申请列表</a></td></tr>
			<tr><td><a href=<s:url action="SystemreCommendation">
			 </s:url>>系统推荐</a></td></tr>
			 </table>
	     </div>
	     
	    <div class="list">
		     <s:form ation="StudentUpDate" method="post">
			    
				 <s:textfield name="Point" label="分数"></s:textfield>
				 <s:textfield name="Interest" label="个人兴趣爱好"></s:textfield>
				 <s:textfield name="Honor" label="荣誉"></s:textfield>
				 <s:textfield name="Experience" label="经验"></s:textfield>
		         <s:submit value="更新"></s:submit>
		     </s:form>
	    </div>
	</div>
	
	<div class="footer">底部 </div>




