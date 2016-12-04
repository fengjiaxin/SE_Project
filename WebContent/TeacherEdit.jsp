<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Teacher System</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/System.css"/>
<script type="text/javascript">
 function checkpwd(){
  var p1=document.form1.Password.value;//获取密码框的值
  var p2=document.form1.Password2.value;//获取重新输入的密码值
  if(p1==""){
   alert("请输入密码！");//检测到密码为空，提醒输入//
   document.form1.Password.focus();//焦点放到密码框
   return false;//退出检测函数
  }
   if(p1!= p2)
   {//判断两次输入的值是否一致，不一致则显示错误信息
	   document.getElementById("msg").innerHTML="两次输入密码不一致，请重新输入";//在div显示错误信息
	   return false;
	}
   else
  {
   //密码一致，可以继续下一步操作 
  }
	 
  }
</script>
</head>
<body class="systemback">
	<div class="top" style="display:block;">
		<div class="logo">
			<div class="topright">
				<div class="welcome">
				<br>您好！  <s:property value="Name"  default="ls"/>老师<br>
	    		
	    		电话：<s:property value="Telephone"  default="435455"/><br>
				邮箱：<s:property value="Email"  default="344545"/>
				
				
				</div>
				<div id="taskbar_right">
				
				<a class="btnedit" href=<s:url value="teacherpagedisplay.action">
	    			<s:param name="Id" value="getId()"></s:param>
	    			</s:url>>编辑
				</a>
				
				<a class="btnhome" href=<s:url value="teacherpagedisplay.action">
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
		      <s:url action="TeacherCompletePersonalInformation">
		      <s:param name="Id" value="getId()"></s:param>
			  </s:url>>信息完善</a>
		
		<a class="nav-btn" href=
		      <s:url action="toTeacherInquiry">
		      <s:param name="Id" value="getId()"></s:param>
			  </s:url>>查询信息</a>
		
		
		<a class="nav-btn" href=<s:url action="SystemreCommendation">
			   </s:url>>系统推荐 </a> 
		       <s:if test="getApplyStation()=='0'.toString()">
			    <a class="nav-btn" href=<s:url action="TeacherList">
		        <s:param name="Id" value="getId()"></s:param>
			    </s:url>>申请列表</a>
				  </s:if>
				  <s:else> 
		        <a class="nav-btn" href=<s:url action="TeacherList">
			    <s:param name="Id" value="getId()"></s:param>
				</s:url>><span title="申请列表有更新">申请列表<img src="<%=request.getContextPath()%>/css/1.png"  height="15" width="15"/></span></a>
		  </s:else>
		      
			
		</div>
	</div>
	<div class="editform">
		<s:form action="teacheredit" name="form1" method="post">
			<s:textfield name="Id" type="hidden" value="%{#request.Id}"></s:textfield>
			<s:textfield type="text" name="UserName" label="用户名：" value="%{UsrName}" readonly="true"></s:textfield>
			
			<s:textfield name="Name" value="%{Name}" label="真实姓名："></s:textfield>
			<s:radio list="#{'1':'男','0':'女'}" name="Sex" value='1' label="性别："></s:radio>
			<s:textfield name="Age" value="%{Age}" label="年龄："></s:textfield>
			<s:textfield name="Telephone" value="%{Telephone}" label="手机号："></s:textfield>
			<s:textfield name="Email" value="%{Email}" label="邮箱："></s:textfield>
			<s:textfield type="password" name="Password" value="%{Password}" label="密码："></s:textfield>
			<s:textfield type="password" name="Password2" label="确认密码：" onchange="checkpwd()" ></s:textfield>
			<s:submit value="编辑"></s:submit>
		</s:form>
	</div>
	
	<div class="Contentbox">
		<div class="index_bg"></div>
	</div>
</body>
</html>