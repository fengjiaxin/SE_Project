package Action;




import Data.Teacher;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;

public class TeacherDetailAction  {
	private int StudentId;
	private Teacher teacher;
	
	public  int getStudentId()
	{
		return this.StudentId;
	}
	public  void setStudentId(int StudentId)
	{
		this.StudentId=StudentId;
	}
	
	public  Teacher getTeacher()
	{
		return this.teacher;
	}
	public  void setTeacher(Teacher teacher)
	{
		this.teacher=teacher;
	}
	
	
	
	
	
	
	
	public String excute() throws Exception
	{
		String TeacherID;
		String StudentID;
		teacher=new Teacher();
		
		TeacherID=ServletActionContext.getRequest().getParameter("TeacherId");
		StudentID=ServletActionContext.getRequest().getParameter("StudentId");
		
		DB mydb = new DB();
		String str="select* from teacher where id="+TeacherID;
		ResultSet rsn = mydb.executeQuery(str);
		if(rsn.next())
		{
			teacher.setName(rsn.getString("name"));
			teacher.setSex(rsn.getString("sex"));
			teacher.setAge(rsn.getString("age"));
			teacher.setTelephone(rsn.getString("telephone"));
			teacher.setEmail(rsn.getString("email"));
			teacher.setStatus(rsn.getInt("status"));
		}
		DB mydb1 = new DB();
		String str1="select* from teacherlabel where id="+TeacherID;
		ResultSet rsn1 = mydb1.executeQuery(str1);
		if(rsn1.next())
		{
			teacher.setAcademy(rsn1.getString("academy"));
			teacher.setResearch(rsn1.getString("research"));
			teacher.setArticle(rsn1.getString("article"));
			teacher.setExperience(rsn1.getString("experience"));
			teacher.setHonor(rsn1.getString("honor"));
			teacher.setNeedNum(rsn1.getInt("neednum"));
			String need = rsn1.getString("needs");
			String[] needlist = need.split(";");
			need = "学分绩要求" + needlist[0] + "以上;";
			if(needlist[1].equals("Y"))
				need += "有实际项目经验者优先；";
			if(needlist[2].equals("I"))
				need += "具有国际级奖项者优先；";
			else if(needlist[2].equals("C"))
				need += "具有国家级奖项者优先；";
			else if(needlist[2].equals("P"))
				need += "具有省级奖项者优先；";
			if(needlist[3].equals("Y"))
				need += "托福90或雅思6.0以上；";
			else if(needlist[3].equals("L"))
				need += "过英语六级；";
			else
				need += "过英语四级；";
			
			if(needlist[4].equals("B"))
				need += "男生优先；";
			else if(needlist[4].equals("G"))
				need += "女生优先；";	
			teacher.setNeeds(need);
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("TeacherId", TeacherID);
    	request.setAttribute("StudentId", StudentID);
		return "TeacherDetail";
    }
	
	
	

}