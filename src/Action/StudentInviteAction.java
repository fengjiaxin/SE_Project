package Action;

import java.sql.ResultSet;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;

public class StudentInviteAction {
	
	/*��studentlist���е�teacherId���뵼ʦ��ID���Զ��Ÿ��� */
	
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
			String Student = "select * from studentlist where id="+StudentID;
			ResultSet Rs=db.executeQuery(Student);
			if(Rs.next())
			{
				teacher_id_list= Rs.getString("teacherId");
		    }
			if(teacher_id_list == null || teacher_id_list == "") //����Ϊ��
			{
				teacher_id_list = TeacherID;
			}
			else                               //�ǿռӶ���
			{
				teacher_id_list = teacher_id_list + "," + TeacherID;
			}
			Student = "update studentlist set teacherId = "+"'"+teacher_id_list+"' where id =" +StudentID; 
			db.executeUpdate(Student);
				
			return "Invite";
		}
}
