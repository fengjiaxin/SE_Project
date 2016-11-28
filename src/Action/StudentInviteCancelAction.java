package Action;

import java.sql.ResultSet;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;

public class StudentInviteCancelAction {
	
	/*点取消后删除teacherId中对应的导师ID*/
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
			setId(Integer.parseInt(StudentID));
			
			String teacher_id_list = null;
			String teacherlist_new = null;
			String[] tc_id;
			String Student = "select * from studentlist where id="+StudentID;
			ResultSet Rs=db.executeQuery(Student);
			if(Rs.next())
			{
				teacher_id_list= Rs.getString("teacherId");
		    }
			tc_id = teacher_id_list.split(",");
			for(int i = 0; i < tc_id.length; i++)    //删除对应ID的导师
			{
				if(!tc_id[i].equals(TeacherID))
				{
					if(teacherlist_new == null)
						teacherlist_new = tc_id[i];
					else
						teacherlist_new = teacherlist_new + "," + tc_id[i];
				}
			}
			Student = "update studentlist set teacherId = "+"'"+teacherlist_new+"' where id =" +StudentID; 
			db.executeUpdate(Student);
			
			
			return "InviteCancel";
		}
}
