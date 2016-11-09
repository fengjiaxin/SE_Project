package Action;




import Data.Teacher;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;

public class TeacherDetailAction  {

	private Teacher teacher;

	
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
			teacher.setNeeds(rsn1.getString("needs"));
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("TeacherID", TeacherID);
    	request.setAttribute("StudentID", StudentID);
		return "TeacherDetail";
    }
		
	
	
	
}