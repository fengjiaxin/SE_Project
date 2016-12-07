<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Not Exist</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/backround.css"/>
</head>
<body>







    <s:if test="getApplyStation()=='0'.toString()">
			    <a class="nav-btn" href=<s:url action="StudentList">
		        <s:param name="Id" value="getId()"></s:param>
			    </s:url>>申请列表</a>
				  </s:if>
				  <s:else> 
		        <a class="nav-btn" href=<s:url action="StudentList">
			    <s:param name="Id" value="getId()"></s:param>
				</s:url>><span title="申请列表有更新">申请列表<img src="<%=request.getContextPath()%>/css/1.png"  height="15" width="15"/></span></a>
	</s:else>
	<s:if test="getInvitStation()=='0'.toString()">
			    <a class="nav-btn" href=<s:url action="StudentInviteList">
		        <s:param name="Id" value="getId()"></s:param>
			    </s:url>>邀请列表</a>
				  </s:if>
				  <s:else> 
		        <a class="nav-btn" href=<s:url action="StudentInviteList">
			    <s:param name="Id" value="getId()"></s:param>
				</s:url>><span title="邀请列表有更新">邀请列表<img src="<%=request.getContextPath()%>/css/1.png"  height="15" width="15"/></span></a>
   </s:else>
</body>
</html>