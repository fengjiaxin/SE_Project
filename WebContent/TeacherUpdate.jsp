<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Teacher System</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/System.css"/>
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
				
				<a class="btnedit" href=<s:url value="toTeacherEdit.action">
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
		
		
		<a class="nav-btn" href=
			<s:url action="TeacherRecommendation">
			<s:param name="Id" value="getId()"></s:param>
			 </s:url>>系统推荐</a>
		<s:if test="getApplyStation()=='0'.toString()">
			<a class="nav-btn" href=<s:url action="TeacherList">
		        <s:param name="Id" value="getId()"></s:param>
			    </s:url>>申请列表</a>
		</s:if>
		<s:else> 
		        <a class="nav-btn" href=<s:url action="TeacherList">
			    <s:param name="Id" value="getId()"></s:param>
				</s:url>><span title="申请列表有更新">申请列表<span style="color:red;font-size: 15px">！</span></span></a>
		</s:else>
		</div>
	</div>
	
	<div class="Contentbox">
		<s:form action="TeacherUpdate" method="post">
				<s:textfield name="Id" type="hidden" value="%{#request.Id}"></s:textfield>
			     <s:doubleselect name="Academy" doubleName="Research" label="学院和研究方向"
						         list="Mylist" doubleList="Mymap.get(top.Id)"
						         listKey="Id" listValue="Name"
						         doubleListKey="Id" doubleListValue="Name" 
						         >
				 </s:doubleselect> 

					 <s:textfield name="Article" value="%{Article}" label="论文："></s:textfield>
					 <s:textfield name="Experience" value="%{Experience}" label="经历："></s:textfield>
					 <s:textfield name="Honor" value="%{Honor}" label="荣誉："></s:textfield>
					 <s:textfield name="NeedNum" value="%{NeedNum}" label="招生人数："></s:textfield><br>
					  <div>
					 <table class="imagetable">
					 <tr>
					 <th>招生需求</th><th></th>
					 </tr>
					 <tr>
					 <td>学分绩</td><td><s:select Name="Grade" list="#{'90':'>=90','80':'>=80','70':'>=70','60':'无要求'}" value="%{Grade}" >
					                         
				                      </s:select>
				                  </td>
				     </tr>
				     <tr>
				     <td>项目经验</td><td><s:select  Name="ExperienceS" list="#{'Y':'有要求','N':'无要求'}" value="%{ExperienceS}">
				                                      
				                       </s:select>
				                   </td>
				     </tr>
				     <tr>
				     <td>获奖等级</td><td><s:select  Name="Award" list="#{'I':'国际级','C':'国家级','P':'省级','N':'无'}" value="%{Award}">
				                                    
				                       </s:select>
				                    </td>
				     </tr>
				     <tr>
				     <td>英语水平</td><td><s:select Name="EnglishLevel" list="#{'N':'无','L':'英语6级','Y':'雅思或托福'}" value="%{EnglishLevel}">
				                                     
                                                      
                                       </s:select>
                                   </td>
                      </tr>
                     <tr>
                     <td>性别要求</td><td><s:select  Name="SexS" list="#{'M':'男','W':'女','N':'无'}" value="%{SexS}">
                                                      
				                       </s:select>
				                    </td>
				     </tr>
                     </table>
					
					   
                         
					 </div>
				     <s:submit value="更新"></s:submit>
		   </s:form>
	</div>
</body>
</html>