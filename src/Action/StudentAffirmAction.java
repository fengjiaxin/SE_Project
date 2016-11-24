package Action;

import java.sql.ResultSet;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;

public class StudentAffirmAction {
	/*确认后修改1.student的状态栏   2.删除studentlist中的多余项，保留确认项  
	 3.判断是否修改teacher的状态栏  4.修改teacherlist状态，已确认数量加一
	 5.确定后删除teacherlist中其他老师list中该同学的申请，待定的直接删，已同意的agreenum-- */
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
		int number;
		int need=0, num=0;
		String[] s_l;
		String str_list = "";
		
		TeacherID=ServletActionContext.getRequest().getParameter("TeacherId");
		StudentID=ServletActionContext.getRequest().getParameter("StudentId");
		setId(Integer.parseInt(StudentID));
		
		String Student = "update student set status ="+1+" where id ="+StudentID; //完成1
		db.executeUpdate(Student);
		Student = "select list from studentlist where id="+StudentID;
		ResultSet Rs=db.executeQuery(Student);
	    if(Rs.next())
		{
	    	
	    	str_list= Rs.getString("list");
	    }
		
		s_l = str_list.split(",");
		number = s_l.length;
		for(int i = 0; i<number; i++)
		{
			String[] s = s_l[i].split(":");
			if(s[0].equals(TeacherID))             //找到确定的导师
			{
				Student = "update studentlist set list = "+"'"+s[0]+":Q"+"',num = 1 where id =" +StudentID; //完成2
				db.executeUpdate(Student);
			}
			else                         //删除其他的导师
			{
				delete_teacherlist(StudentID, s[0]);
			}
		}
		
		String Teacher = "select num from teacherlist where id=" + TeacherID;
		ResultSet RsN=db.executeQuery(Teacher);
	    if(RsN.next())
		{
			num = RsN.getInt("num");
			num++;
			Teacher = "update teacherlist set num = "+num+" where id =" +TeacherID; //完成4
			db.executeUpdate(Teacher);
	    }
	    
		Teacher = "select neednum from teacherlabel where id=" + TeacherID;
		ResultSet RN=db.executeQuery(Teacher);
	    if(RN.next())
		{
			need= RN.getInt("neednum");
	    }
	    if(num == need)
	    {
	    	Teacher = "update teacher set status ="+1+" where id ="+TeacherID; //完成3
	    	db.executeUpdate(Teacher);
	    }
	    
	    return "Affirm";
	}
	
	/*删除ID为tc_id的导师list中ID为st_id的学生信息*/
    public void delete_teacherlist(String st_id, String te_id) throws Exception
    {
		DB db=new DB();
		String str_list = "";
		String tc_list = "";
    	int number, agree_n = 0;
		String[] s_l = null;
		
		String Teacher = "select * from teacherlist where id="+te_id;
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
			if(!s[0].equals(st_id))
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
		Teacher = "update teacherlist set list = "+"'"+tc_list+"' ,"+"agreenum="+agree_n+" where id =" +te_id; //完成2
		db.executeUpdate(Teacher);
	    System.out.println(Teacher);
    }
    
}
