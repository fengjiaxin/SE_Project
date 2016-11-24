package Action;

import java.sql.ResultSet;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;

public class StudentCancelAction {
	/*取消后修改    1.删除studentlist中的取消项,num-1 2.删除teacherlist中的取消项,若老师同意后取消，agreenum-1
	 3.被拒绝后取消只删除studentlist中的取消项*/
	
	private int Id;
	 public void setId(int Id)
	 {
		 this.Id = Id;
	 }
	 public int getId()
	 {
		 return this.Id;
	 }
	 
	public String excute() throws Exception
	{
		DB db=new DB();
		String TeacherID;
		String StudentID;
		String str_list = "";
		String stu_list = "";
		String tc_list = "";
    	int number, num=0, agree_n = 0;
		String[] s_l = null;
		
		TeacherID=ServletActionContext.getRequest().getParameter("TeacherId");
		StudentID=ServletActionContext.getRequest().getParameter("StudentId");
		setId(Integer.parseInt(StudentID));
		
		String Student = "select * from studentlist where id="+StudentID;
		ResultSet Rs=db.executeQuery(Student);
	    if(Rs.next())
		{
	    	num = Rs.getInt("num");
	    	str_list= Rs.getString("list");
	    }	
		s_l = str_list.split(",");
		number = s_l.length;
		for(int i = 0; i < number; i++)
		{
			String[] s = s_l[i].split(":");
			if(!s[0].equals(TeacherID))
			{
				if(stu_list == "")
				    stu_list = s_l[i];
				else
				{
					stu_list = stu_list +","+ s_l[i];
				}
			}
			else
			{
				num--;
			}
		}
		Student = "update studentlist set list = "+"'"+stu_list+"',"+"num="+num+" where id =" +StudentID; //完成1
		//System.out.println(stu_list);
		db.executeUpdate(Student);
		
		
		String Teacher = "select * from teacherlist where id="+TeacherID;
		ResultSet RsN=db.executeQuery(Teacher);
	    if(RsN.next())
		{
	    	agree_n = RsN.getInt("agreenum");
	    	str_list= RsN.getString("list");
	    }	
		s_l = str_list.split(",");
		number = s_l.length;
		for(int i = 0; i < number; i++)
		{
			String[] s = s_l[i].split(":");
			if(!s[0].equals(StudentID))
			{
				if(tc_list == "")
				    tc_list = s_l[i];
				else
				{
					tc_list = tc_list +","+ s_l[i];
				}
			}
			else if (s[1].equals("Y"))
			{
				agree_n--;
			}
		}
		Teacher = "update teacherlist set list = "+"'"+tc_list+"' ,"+"agreenum="+agree_n+" where id =" +TeacherID; //完成2
		db.executeUpdate(Teacher);
		return "Cancel";
	}
}
