<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>StudentApplyList</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/backround.css"/>
</head>


    
    <div class="header">
	    姓名：<s:property value="Name"  default=" "/><br>
	    年龄：<s:property value="Age"  default=" "/>
	    性别：<s:property value="Sex"  default=" "/><br>
	    联系方式：<s:property value="Email"  default=" "/>
	    <s:property value="Telephone"  default=" "/>
    </div>
    
	<div class="main" >
	     <div class="option">
	     
	     <div class="tab">
		      <a href=
		      <s:url action="StudentCompletePersonalInformation">
		      <s:param name="Id" value="getId()"></s:param>
			  </s:url>>个人信息完善</a>
			 </div>
		     <div class="tab">
		     <s:form action="StudentInquiry" method="post">
			    <s:select name="AcademyId" list="Mylist" label="学院" listValue="Name" listKey="id"></s:select>
			    <s:textfield name="Id" type="hidden" value="%{#request.Id}"></s:textfield>
			    <s:submit value="查询"></s:submit>
			 </s:form>
			</div>
		      <div class="tab">
		      <a href=<s:url action="StudentList">
		      <s:param name="Id" value="getId()"></s:param>
			 </s:url>>申请列表</a>
			 </div>

			 <div class="tab">
			 <a href=<s:url action="SystemreCommendation">
			 </s:url>>系统推荐</a>
			 </div>
	     </div>
	     
	    <div class="list">
	    <table border="1" width="75%" cellpadding="0" cellspacing="0" align="center">  
			<tr style="background-color: yellow">
				<td align="center">导师姓名</td>
				<td align="center">申请状态</td>
				<td align="center">操作</td>
				
			</tr>  
			<s:iterator value="#request.TeacherList"> 
				<tr>
					<td  align="center">
						<a href=
							<s:url value="TeacherDetail.action">
								<s:param name="TeacherId" value="Id"/>
								<s:param name="StudentId" value="%{#request.Id}"/>
							</s:url>>
							<s:property value="Name"/>
						</a>
					</td>
					<td align="center">					
							<s:property value="%{#request.Map[Status]}"/>
					</td>
					<td class="apply" align="center">
						<s:if test="Status == 'T'.toString()">
							<a  href=
							<s:url value="StudentAffirm.action">
								<s:param name="TeacherId" value="Id"/>
								<s:param name="StudentId" value="%{#request.Id}"/>
							</s:url>>
							确认
							</a>
						</s:if>
						<s:else> </s:else>
						<s:if test="Status == 'Q'.toString()"> </s:if>
						<s:else>
							<a class="apply" href=
							<s:url value="StudentCancel.action">
								<s:param name="TeacherId" value="Id"/>
								<s:param name="StudentId" value="%{#request.Id}"/>
							</s:url>>
							取消
							</a>
						</s:else>
					</td>
				</tr>
			</s:iterator>
		</table>
	    </div>
	</div>
	<div class="footer">底部<s:debug></s:debug></div>

