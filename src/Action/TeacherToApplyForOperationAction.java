package Action;
import DBcon.DB;
import Data.Mail;

import java.sql.ResultSet;

import org.apache.struts2.ServletActionContext;

public class TeacherToApplyForOperationAction {
	
	private String Id;
	public void SetId(String Id)
	{
		this.Id=Id;
	}
	public String getId()
	{
		return this.Id;
	}
	
	public String TeacherAgree() throws Exception
	{
		String TeacherID=ServletActionContext.getRequest().getParameter("TeacherId");
		String StudentID=ServletActionContext.getRequest().getParameter("StudentId");
		int neednum=0;
		int aggrenum=0;
		SetId(TeacherID);
		DB ltdb=new DB();
		DB db1=new DB();
		
		//           ��ʦ�����ʼ�����
		String tea = "select * from teacher where id = " + TeacherID;
		String stu = "select * from student where id = " + StudentID;
		String teacherName = null;
		String studentEmail = null;
		ResultSet r1=db1.executeQuery(tea);
		
		if(r1.next())
		{
			teacherName = r1.getString("name");
		}
		ResultSet r2=db1.executeQuery(stu);
		if(r2.next())
		{
			studentEmail = r2.getString("email");
		}						
		
        String smtp = "smtp.126.com";// qq������
        String from = "taiyangaaagzy@126.com";// �ʼ���ʾ����
        String to = studentEmail;// �ռ��˵��ʼ���ַ����������ʵ��ַ
        String copyto = "taiyangaaagzy@126.com";// �������ʼ���ַ
        String subject = "����ϵͳ����";// �ʼ�����
        String content = "hello!" + teacherName + "��ʦ" + "ͬ�����������";// �ʼ�����
        String username = "taiyangaaagzy";// ��������ʵ���˻���
        String password = "110220qqaa";// ����������
        System.out.println(content);
        System.out.println(studentEmail);
        Mail.sendAndCc(smtp, from, to, copyto, subject, content, username, password);
        
        //
		String t="select* from teacherlabel where id="+TeacherID;
		ResultSet rlsn=ltdb.executeQuery(t);
		if(rlsn.next())
		{
			neednum=rlsn.getInt("neednum");
		}
		DB tdb=new DB();
		String Tstr="select* from teacherlist where id="+TeacherID;
		ResultSet Tsrn=tdb.executeQuery(Tstr);
		
		if(Tsrn.next())
		{
			aggrenum=Tsrn.getInt("agreenum");
			if(aggrenum>=neednum)
			{
				return "AgreeFail";
			}
			int num=Tsrn.getInt("num");
			String Tlist=Tsrn.getString("list");
			String string="";
			String str="";
			String[] arry=Tlist.split(",");
			int length=arry.length;
			for(int i=0;i<length;i++)
			{
				String[] Tarry=arry[i].split(":");
				if(StudentID.equals(Tarry[0]))
				{
					str=StudentID+":Y";
				}
				else
				{
					if(string.length()==0)
					{
						string=arry[i];
					}
					else
					{
						string+=","+arry[i];
					}
				}
			}
			if(string.length()==0)
			{
				string=str;
			}
			else
			{
				string+=","+str;
			}
			aggrenum++;
			String s = "update teacherlist set list="+"'"+string+"'"+",num="+num+",agreenum="+aggrenum+ " where id="+TeacherID+";";
			tdb.executeUpdate(s);
			
		}
		    DB sdb=new DB();
			String Sstr="select* from studentlist where id="+StudentID;
			ResultSet Ssrn=sdb.executeQuery(Sstr);
			if(Ssrn.next())
			{
				int num=Ssrn.getInt("num");
				String Slist=Ssrn.getString("list");
				String string="";
				String str="";
				String[] arry=Slist.split(",");
				int length=arry.length;
				for(int i=0;i<length;i++)
				{
					String[] Sarry=arry[i].split(":");
					if(TeacherID.equals(Sarry[0]))
					{
						str=TeacherID+":T";
					}
					else
					{
						if(string.length()==0)
						{
							string=arry[i];
						}
						else
						{
							string+=","+arry[i];
						}
					}
				}
				if(string.length()==0)
				{
					string=str;
				}
				else
				{
					string+=","+str;
				}
				String s = "update studentlist set list="+"'"+string+"'"+",num="+num+" where id="+StudentID+";";
				sdb.executeUpdate(s);
				DB d=new DB();
				String td="update student set ApplyStation="+1+" where id="+StudentID+";";
				d.executeUpdate(td);
	   }
		
			return "Agree";
	}
	
	
	public String TeacherReject() throws Exception
	{
		String TeacherID=ServletActionContext.getRequest().getParameter("TeacherId");
		String StudentID=ServletActionContext.getRequest().getParameter("StudentId");
		SetId(TeacherID);
	    DB sdb=new DB();                                                 
		String Sstr="select* from studentlist where id="+StudentID;
		ResultSet Ssrn=sdb.executeQuery(Sstr);
		if(Ssrn.next())
		{
			String Slist=Ssrn.getString("list");
			String string="";
			String str="";
			String[] arry=Slist.split(",");
			int length=arry.length;
			for(int i=0;i<length;i++)
			{
				String[] Sarry=arry[i].split(":");
				if(TeacherID.equals(Sarry[0]))
				{
					str=TeacherID+":J";
				}
				else
				{
					if(string.length()==0)
					{
						string=arry[i];
					}
					else
					{
						string+=","+arry[i];
					}
				}
			}
			if(string.length()==0)
			{
				string=str;
			}
			else
			{
				string+=","+str;
			}
			String s = "update studentlist set list="+"'"+string+"'"+" where id="+StudentID+";";
			sdb.executeUpdate(s);
		}
		
		DB tdb=new DB();
		String Tstr="select* from teacherlist where id="+TeacherID;
		ResultSet Tsrn=sdb.executeQuery(Tstr);
		if(Tsrn.next())
		{
			String Tlist=Tsrn.getString("list");
			String string="";
			String[] arry=Tlist.split(",");
			int length=arry.length;
			for(int i=0;i<length;i++)
			{
				String[] Tarry=arry[i].split(":");
				if(!StudentID.equals(Tarry[0]))
				{
					if(string.length()==0)
					{
						string=arry[i];
					}
					else
					{
						string+=","+arry[i];
					}
				}
			}
			String s = "update teacherlist set list="+"'"+string+"'"+" where id="+TeacherID+";";
			tdb.executeUpdate(s);
		}
		DB d=new DB();
		String td="update student set ApplyStation="+1+" where id="+StudentID+";";
		d.executeUpdate(td);
		return "Reject";
	 }
}
