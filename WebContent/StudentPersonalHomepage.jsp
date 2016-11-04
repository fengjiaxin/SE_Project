<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/backround.css"/>
<title>Student profile management</title>

</head>
<body>

<div class="homepage">
   
   
    <div class="header">顶部
    <marquee bgcolor=FF0000></marquee>
    <s:property value="Name"  default="郭延龙"/>
    <i><s:property value="Email"  default="1078530334@qq。com"/></i>
    <br><s:property value="Telephone"  default="18845099586"/>
    <s:property value="Age"  default="23"/>
    <s:property value="Sex"  default="男"/>
    </div>
	<div class="main">
	    
	     <div class="option">
		     <a href=<s:url action="StudentCompletePersonalInformation">
		     <s:param name="Id" value="getId()"></s:param>
			 </s:url>>个人信息完善</a>
			 <br><a href=<s:url action="StudentInquiry">
			 </s:url>>查询</a>
			 <br><a href=<s:url action="ApplicationList_Teacher">
			 </s:url>>申请列表</a>
			 <br><a href=<s:url action="StudentSystemreCommendation">
			 </s:url>>系统推荐</a>
	   </div>
	     
	     
	     <div class="list"></div>
	     学院                      <s:property value="Academy"  default="计算机"/><br>
	     专业                     <s:property value="Major"  default="信息安全 "/><br>
	     学号                     <s:property value="Id"  default="1140310321 "/><br>
	     考研成绩           <s:property value="Point"  default="100"/><br>
	     爱好                    <s:property value="interest"  default="看电影"/><br>
	     荣誉                    <s:property value="Honor"  default="诺贝尔计算机突出贡献奖"/><br>
	     经历                    <s:property value="Experience"  default="去台湾当过交换生"/>
	</div>
	<div class="footer">底部 </div>
</div>

<img src=WEB-INF/updatabrand.jsp>
</body>