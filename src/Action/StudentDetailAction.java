package Action;

import Data.Student;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;

public class StudentDetailAction {
	private Student student;
	
	
	public  Student getStudent()
	{
		return this.student;
	}
	public  void setStudent(Student student)
	{
		this.student=student;
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
		System.out.println(str);
		ResultSet rsn = mydb.executeQuery(str);
		if(rsn.next())
		{
			student.setName(rsn.getString("name"));
			student.setSex(rsn.getString("sex"));
			student.setAge(rsn.getString("age"));
			student.setTelephone(rsn.getString("telephone"));
			student.setEmail(rsn.getString("email"));
			student.setStatus(rsn.getString("status"));
			System.out.println(student.getStatus());
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
			String hon = rsn1.getString("honor");
			String change_hon = "";
			String[] honlist = hon.split(";");
			if(!honlist[0].equals("нч"))
				change_hon += honlist[0];
			if(!honlist[1].equals("нч"))
				change_hon += honlist[1];
			if(!honlist[2].equals("нч"))
				change_hon += honlist[2];			
            System.out.println(change_hon);
			student.setHonor(change_hon);
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("TeacherId", TeacherID);
    	request.setAttribute("StudentId", StudentID);
		
		return "StudentDetail";
	}
	
	
	
	
	
	
	
	
	

}
