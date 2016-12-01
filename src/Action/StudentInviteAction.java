package Action;

import java.sql.ResultSet;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;

public class StudentInviteAction {
	
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
			setId(Integer.parseInt(TeacherID));
			
			String teacher_id_list = null;
			String[] tc_id;
			String Student = "select * from studentlist where id="+StudentID;
			ResultSet Rs=db.executeQuery(Student);
			if(Rs.next())
			{
				teacher_id_list= Rs.getString("teacherId");
		    }
			if(teacher_id_list == null || teacher_id_list == "") //此列为空
			{
				teacher_id_list = TeacherID;
			}
			else                               //非空先判断此ID是否重复，不重复加逗号后添加
			{
				int i = 0;
				tc_id = teacher_id_list.split(",");
				for(i = 0; !tc_id[i].equals(TeacherID); i++)
				if(i == tc_id.length)           //说明没有重复的TeacherID
				    teacher_id_list = teacher_id_list + "," + TeacherID;
			}
			Student = "update studentlist set teacherId = "+"'"+teacher_id_list+"' where id =" +StudentID; 
			db.executeUpdate(Student);
				
			return "Invite";
		}
}
