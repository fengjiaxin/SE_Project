package Action;

import java.sql.ResultSet;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;

public class StudentAffirmAction {
	/*ȷ�Ϻ��޸�1.student��״̬��   2.ɾ��studentlist�еĶ��������ȷ����  
	 3.�ж��Ƿ��޸�teacher��״̬��  4.�޸�teacherlist״̬����ȷ��������һ
	 5.ȷ����ɾ��teacherlist��������ʦlist�и�ͬѧ�����룬������ֱ��ɾ����ͬ���agreenum-- */
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
		
		String Student = "update student set status ="+1+" where id ="+StudentID; //���1
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
			if(s[0].equals(TeacherID))             //�ҵ�ȷ���ĵ�ʦ
			{
				Student = "update studentlist set list = "+"'"+s[0]+":Q"+"',num = 1 where id =" +StudentID; //���2
				db.executeUpdate(Student);
			}
			else                         //ɾ�������ĵ�ʦ
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
			Teacher = "update teacherlist set num = "+num+" where id =" +TeacherID; //���4
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
	    	Teacher = "update teacher set status ="+1+" where id ="+TeacherID; //���3
	    	db.executeUpdate(Teacher);
	    }
	    
	    return "Affirm";
	}
	
	/*ɾ��IDΪtc_id�ĵ�ʦlist��IDΪst_id��ѧ����Ϣ*/
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
		Teacher = "update teacherlist set list = "+"'"+tc_list+"' ,"+"agreenum="+agree_n+" where id =" +te_id; //���2
		db.executeUpdate(Teacher);
	    System.out.println(Teacher);
    }
    
}
