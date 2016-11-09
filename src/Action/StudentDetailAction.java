package Action;

import Data.Student;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;

public class StudentDetailAction {
	private Student student;
	private int TeacherId;
	
	public  int getTeacherId()
	{
		return this.TeacherId;
	}
	public  void setTeacherId(int TeacherId)
	{
		this.TeacherId=TeacherId;
	}
	
	public  Student getstudent()
	{
		return this.student;
	}
	public  void setstudent(Student teacher)
	{
		this.student=teacher;
	}
	
	
	public String excute() throws Exception
	{
		String TeacherID;
		String StudentID;
		student=new Student();
		
		TeacherID=ServletActionContext.getRequest().getParameter("TeacherId");
		StudentID=ServletActionContext.getRequest().getParameter("StudentId");
		DB mydb = new DB();
		String str="select* from student where id="+StudentID;
		ResultSet rsn = mydb.executeQuery(str);
		if(rsn.next())
		{
			student.setName(rsn.getString("name"));
			student.setSex(rsn.getString("sex"));
			student.setAge(rsn.getString("age"));
			student.setTelephone(rsn.getString("telephone"));
			student.setEmail(rsn.getString("email"));
		}
		DB mydb1 = new DB();
		String str1="select* from studentlabel where id="+StudentID;
		ResultSet rsn1 = mydb1.executeQuery(str1);
		if(rsn1.next())
		{
			student.setPoint(rsn1.getString("point"));
			student.setAcademy(rsn1.getString("academy"));
			student.setMajor(rsn1.getString("interest"));
			student.setExperience(rsn1.getString("experience"));
			student.setHonor(rsn1.getString("honor"));
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("TeacherID", TeacherID);
    	request.setAttribute("StudentID", StudentID);
		
		return "StudentDetail";
	}
	
	
	
	
	
	
	
	
	

}
