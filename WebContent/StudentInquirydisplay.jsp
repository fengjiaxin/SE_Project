<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student System</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/System.css"/>
</head>
<body class="systemback">
	<div class="top" style="display:block;">
		<div class="logo">
			<div class="topright">
				<div class="welcome">
				<br>您好！  <s:property value="Name"  default="ls"/>同学<br>
	    
				电话：<s:property value="Telephone"  default="435455"/><br>
				邮箱：<s:property value="Email"  default="344545"/>
				
				</div>
				<div id="taskbar_right">
				
				<a class="btnedit" href=<s:url value="studentpagedisplay.action">
	    			<s:param name="Id" value="getId()"></s:param>
	    			</s:url>>编辑
				</a>
				
				<a class="btnhome" href=<s:url value="studentpagedisplay.action">
	    			<s:param name="Id" value="getId()"></s:param>
	    			</s:url>>主页
				</a>
				<a class="btnquit" href=<s:url value="return.action"></s:url>>退出
				</a>
				
				</div>
			</div>
		</div>
	</div>
	
	<div class="top-nav">
		<div class="navlist">
		<a class="nav-btn" href=
		      <s:url action="StudentCompletePersonalInformation">
		      <s:param name="Id" value="getId()"></s:param>
			  </s:url>>信息完善</a>
		
		<a class="nav-btn" href=
		      <s:url action="toStudentInquiry">
		      <s:param name="Id" value="getId()"></s:param>
			  </s:url>>查询信息</a>
		
		
		<a class="nav-btn" href=<s:url action="StudentRecommendation">
			 <s:param name="Id" value="getId()"></s:param>
			 </s:url>>系统推荐</a>
			 
			 
		<s:if test="getApplyStation()=='0'.toString()">
			    <a class="nav-btn" href=<s:url action="StudentList">
		        <s:param name="Id" value="getId()"></s:param>
			    </s:url>>申请列表</a>
		</s:if>
		<s:else> 
		        <a class="nav-btn" href=<s:url action="StudentList">
			    <s:param name="Id" value="getId()"></s:param>
				</s:url>><span title="申请列表有更新">申请列表<span style="color:red;font-size: 15px">！</span></span></a>
		</s:else>

			
		<s:if test="getInviteStation()=='0'.toString()">
			    <a class="nav-btn" href=<s:url action="StudentInviteList">
		        <s:param name="Id" value="getId()"></s:param>
			    </s:url>>邀请列表</a>
		</s:if>
		<s:else> 
		        <a class="nav-btn" href=<s:url action="StudentInviteList">
			    <s:param name="Id" value="getId()"></s:param>
				</s:url>><span title="邀请列表有更新">邀请列表<span style="color:red;font-size: 15px">！</span></span></a>
   		</s:else>

		<a class="nav-btn" href=<s:url action="StudentChoosedTea">
			 <s:param name="Id" value="getId()"></s:param>
			 </s:url>>所选导师</a>	 
			 
		</div>
	</div>
	
	<div class="Contentbox">
		<div class="list">
	    <table border="1" width="100%" cellpadding="0" cellspacing="0" align="center">  
			<tr style="background-color: #D1E9E9">
				<td align="center">导师姓名</td>
				<td align="center">研究方向</td>
			</tr>  
			<s:iterator value="#request.List"> 
				<tr>
					<td class="name" align="center">
						<a href=
							<s:url value="TeacherDetail.action">
								<s:param name="TeacherId" value="Id"/>
								<s:param name="StudentId" value="%{#request.Id}"/>
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
</body>
</html>