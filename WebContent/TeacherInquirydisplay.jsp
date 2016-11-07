<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Teacher message update</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/backround.css"/>
</head>
<body>



   
   
    <div class="header">顶部
    <marquee bgcolor=FF0000></marquee>
    <s:property value="Name"  default=" "/>
    <i><s:property value="Email"  default=" "/></i>
    <br><s:property value="Telephone"  default=" "/>
    <s:property value="Age"  default=" "/>
    <s:property value="Sex"  default=" "/>
    </div>
	<div class="main">
	    
	     <div class="option">
			     <a href=<s:url action="TeacherCompletePersonalInformation">
			     <s:param name="Id" value="getId()"></s:param>
				 </s:url>>个人信息完善</a>
				
				 <s:form action="TeacherInquiry" method="post">
					 <s:select name="AcademyId" list="Mylist"  label="学院" listValue="Name" listKey="id"></s:select>
					 <s:textfield name="Id" type="hidden" value="%{#request.Id}"> </s:textfield>
					 <s:submit value="查询"></s:submit>
				 </s:form>
				 <br><a href=<s:url action="ApplicationList_Student">
				 </s:url>>申请列表</a>
				 <br><a href=<s:url action="TeacherSystemreCommendation">
				 </s:url>>系统推荐</a>
	     </div>
	     
	     
	    <div class="list">
	    <table border="1" width="50%" cellpadding="0" cellspacing="0" align="center">  
	    
			<s:iterator  value="request.List">
				<tr>
				<td>
				<a href=<s:url action="StudentDetails.action">
				<s:param name="StudentId" value="Id"></s:param>
				</s:url>
				>
				<s:property value="Name"/>
				</a>
				</td>
				<td align="center">					
						<s:property value="Interest"/>
				</td>
				</tr>
           </s:iterator> 
       </table>
	   </div>
	</div>
	<div class="footer">底部 </div>


<img src=WEB-INF/updatabrand.jsp>
</body>