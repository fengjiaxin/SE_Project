﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
				
				<a class="btnedit" href=<s:url value="toStudentEdit.action">
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
	         <s:form class="formsty" action="StudentUpdate" method="post" theme="simple">
			 <s:textfield name="Id" type="hidden" value="%{#request.Id}" theme="simple"></s:textfield>
	         <div>
				            民族:<s:textfield name="Nation"  value="%{Nation}" theme="simple"></s:textfield><br>
			                         籍贯:<s:textfield name="NativaPlace" value="%{NativaPlace}" theme="simple"></s:textfield><br>
	         </div>
	         <div class=nobr>
	         <h4>请选择学院和专业：</h4>
			 <s:doubleselect theme="simple" name="Academy"  doubleName="Major"
					         list="Mylist" doubleList="Mymap.get(top.Id)"
					         listKey="Id" listValue="Name"
					         doubleListKey="Id" doubleListValue="Name">
		     </s:doubleselect><br>
		     <h4>英语级别</h4>
				        <s:select  Name="EnglishLevel"  list="#{'N':'英语4级','L':英语6级,'Y':'雅思或托福'}" value="%{EnglishLevel}" >
				        </s:select>
				      
		
	         </div>
	         <div>
	         <h3>本科平均学分绩</h3>
			 <s:textfield name="Point" value="%{Point}" label="分数" theme="simple"></s:textfield>
	         </div>
	         <div>
	                                 是否获得过国际级奖项： <s:radio list="#{'1':'是','0':'否'}" name="HI" value="%{HI}" ></s:radio>
		                   备注:<s:textarea name="RemarkI" value="%{RemarkI}"  theme="simple"></s:textarea><br>
                                               是否获得过国家级奖项：  <s:radio list="#{'1':'是','0':'否'}" name="HC" value="%{HC}"></s:radio>
                                               备注:<s:textarea name="RemarkC" value="%{RemarkC}" theme="simple"></s:textarea><br> 
                                               是否获得过省级奖项 ：    <s:radio list="#{'1':'是','0':'否'}" name="HP" value="%{HP}" ></s:radio>
			     备注:<s:textarea name="RemarkP" value="%{RemarkP}" theme="simple"></s:textarea><br>
	         </div>
		   <div class=nobr>
			 <h3>请根据所选学院来选择研究方向</h3>
		    <s:doubleselect theme="simple" name="Academy1" listValue="%{Academy1}"  doubleName="Research" doubleListValue="%{Research}"
    				         list="Mylist1" doubleList="Mysearch.get(top.Id)"
					         listKey="Id" listValue="Name"
					         doubleListKey="Id" doubleListValue="Name"
					         >
				 	</s:doubleselect><br>
				
				</div>
				<div>
				 经历:<s:textarea name="Experience"  value="%{Experience}" theme="simple"></s:textarea><br>
				</div>    
		         <s:submit value="更新"></s:submit>
		     </s:form>
	</div>
</body>
</html>
</html>