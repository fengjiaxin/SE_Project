package Action;

import java.sql.ResultSet;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;

public class TeacherInviteAction {
	/*老师查看学生主页点击邀请后的操作*/
	/*若学生已经与别的老师建立师生关系，返回失败*/
	/*在studentlist表中的teacherId填入导师的ID，以逗号隔开 */
	
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
			TeacherID=ServletActionContext.getRequest().getParameter("TeacherId");
			StudentID=ServletActionContext.getRequest().getParameter("StudentId");
			System.out.println("邀请="+TeacherID);
			setId(Integer.parseInt(TeacherID));
			
			String teacher_id_list = null;
			String[] tc_id;
			String Student = "select * from student where id="+StudentID;
			ResultSet R=db.executeQuery(Student);
			int status = 0;
			if(R.next())
				status = R.getInt("status");
			if(status == 1)
				return "InviteFail";
			else
			{
				String s="update student set  InvitStation="+1+" where id="+StudentID;
				System.out.println(s);
				db.executeUpdate(s);
				Student = "select * from studentlist where id="+StudentID;
				ResultSet Rs=db.executeQuery(Student);
				if(Rs.next())
				{
					teacher_id_list= Rs.getString("teacherId");
			    }
				if(teacher_id_list == null || teacher_id_list.matches("\\s*")) //此列为空
				{
					teacher_id_list = TeacherID;
				}
				else                               //非空先判断此ID是否重复，不重复加逗号后添加
				{
					int i = 0;
					tc_id = teacher_id_list.split(",");
					
					for(i = 0; i<tc_id.length&&!tc_id[i].equals(TeacherID); i++);
					
					if(i == tc_id.length)           //说明没有重复的TeacherID
					    teacher_id_list = teacher_id_list + "," + TeacherID;
				}
				Student = "update studentlist set teacherId = "+"'"+teacher_id_list+"' where id =" +StudentID; 
				db.executeUpdate(Student);
				
					
				return "Invite";
			}
		}
}
