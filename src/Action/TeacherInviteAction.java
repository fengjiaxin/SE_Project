package Action;

import java.sql.ResultSet;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;

public class TeacherInviteAction {
	/*��ʦ�鿴ѧ����ҳ��������Ĳ���*/
	/*��ѧ���Ѿ�������ʦ����ʦ����ϵ������ʧ��*/
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
			System.out.println("����="+TeacherID);
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
				if(teacher_id_list == null || teacher_id_list.matches("\\s*")) //����Ϊ��
				{
					teacher_id_list = TeacherID;
				}
				else                               //�ǿ����жϴ�ID�Ƿ��ظ������ظ��Ӷ��ź����
				{
					int i = 0;
					tc_id = teacher_id_list.split(",");
					
					for(i = 0; i<tc_id.length&&!tc_id[i].equals(TeacherID); i++);
					
					if(i == tc_id.length)           //˵��û���ظ���TeacherID
					    teacher_id_list = teacher_id_list + "," + TeacherID;
				}
				Student = "update studentlist set teacherId = "+"'"+teacher_id_list+"' where id =" +StudentID; 
				db.executeUpdate(Student);
				
					
				return "Invite";
			}
		}
}
