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
			need = "ѧ�ּ�Ҫ��" + needlist[0] + "����;";
			if(needlist[1].equals("Y"))
				need += "��ʵ����Ŀ���������ȣ�";
			if(needlist[2].equals("I"))
				need += "���й��ʼ����������ȣ�";
			else if(needlist[2].equals("C"))
				need += "���й��Ҽ����������ȣ�";
			else if(needlist[2].equals("P"))
				need += "����ʡ�����������ȣ�";
			if(needlist[3].equals("Y"))
				need += "�и�90����˼6.0���ϣ�";
			else if(needlist[3].equals("L"))
				need += "��Ӣ��������";
			else
				need += "��Ӣ���ļ���";
			
			if(needlist[4].equals("B"))
				need += "�������ȣ�";
			else if(needlist[4].equals("G"))
				need += "Ů�����ȣ�";	
			teacher.setNeeds(need);
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("TeacherId", TeacherID);
    	request.setAttribute("StudentId", StudentID);
		return "TeacherDetail";
    }
	
	
	

}