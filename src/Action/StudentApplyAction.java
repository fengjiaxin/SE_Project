package Action;

import java.sql.ResultSet;



import org.apache.struts2.ServletActionContext;

import DBcon.DB;
import Data.Mail;

public class StudentApplyAction {
	
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
		DB db1=new DB();
		String TeacherID;
		String StudentID;
		
		TeacherID=ServletActionContext.getRequest().getParameter("TeacherId");
		StudentID=ServletActionContext.getRequest().getParameter("StudentId");
		setId(Integer.parseInt(StudentID));
		DB dbs=new DB();
		String ss="select* from student where id="+StudentID;
		
		ResultSet rss=dbs.executeQuery(ss);
		if(rss.next())
		{
			if(rss.getInt("status")==1)
			{
				return "DontApp";
			}
		}
		
		String str="select* from studentlist where id="+StudentID;
		ResultSet rsn=db.executeQuery(str);
        if(rsn.next())//�����ҵ�ѧ��
		{
			setId(rsn.getInt("id"));
			if(rsn.getInt("num")>=3)//ִ�������֧˵��ѧ�����뵼ʦ�������Ѿ��ﵽ���ޣ�������������
			{
				return "DontApp";
			}
			else//˵��ѧ��������ʦ��������
			{
				DB dbt=new DB();
				String strt="select* from teacherlist where id="+TeacherID;
				
				ResultSet rsnt=dbt.executeQuery(strt);
				if(rsnt.next())//����Ӧ����ʦ
				{
					if(rsnt.getInt("num")>=10)//ִ����һ��˵����õ�ʦ��������ѧ�������Ѵﵼʦ���������
					{
						return "DontApp";
					}
					else
					{
						int length=0;
						String slist=rsn.getString("list");																																						
						String[] repeat={};
						String[] RepeatArry={};
						if(rsn.getInt("num")>0)
						{
							repeat=slist.split(",");
							slist="";
						    length=repeat.length;
							for(int i=0;i<length;i++)
							{
								slist+=repeat[i];
							}
							RepeatArry=slist.split("[a-zA-Z]");//��һ���ָ�
							slist="";
							for(int i=0;i<length;i++)
							{
								slist+=RepeatArry[i];
							}
							RepeatArry=slist.split(":");
							
						}
						
						length=RepeatArry.length;
						for(int i=0;i<length;i++)
						{
							if( RepeatArry[i].equals(TeacherID))
							{
									return "DontApp";//˵������ʦ�Ѿ��������
							}
							
						}
						//����ִ��˵�����Խ�������
						//����ѧ����Ϣ
						String S_list=rsn.getString("list");
						int    S_num=rsn.getInt("num");
						if(rsn.getInt("num")==0)
						{
							S_list=TeacherID+":D";//���ǵ�һ������
						}
						else
						{
							S_list+=","+TeacherID+":D";
						}
						S_num++;
						String s = "update studentlist set list="+"'"+S_list+"'"+",num="+S_num+" where id="+StudentID+";";
						db.executeUpdate(s);
						//������ʦ��Ϣ
						String T_list=rsnt.getString("list");
						
						if(rsnt.getString("list") == null || rsnt.getString("list").matches("\\s*"))
						{
							T_list=StudentID+":W";
						}
						else
						{
							T_list+=","+StudentID+":W";
						}
						
						String t="update teacherlist set list="+"'"+T_list+"'"+" where id="+TeacherID+";";
						
						dbt.executeUpdate(t);
						DB d=new DB();
						String td="update teacher set ApplyStation="+1+" where id="+TeacherID+";";
						//System.out.println(td);
						d.executeUpdate(td);
						
						String sss="update student set  ApplyStation="+1+" where id="+StudentID;
						//System.out.println(sss);
						d.executeUpdate(sss);
						//           ��ʦ�����ʼ�����
						String tea = "select * from teacher where id = " + TeacherID;
						String stu = "select * from student where id = " + StudentID;
						String teacherEmail = null;
						String studentName = null;
						ResultSet r1=db1.executeQuery(tea);
						
						if(r1.next())
						{
							teacherEmail = r1.getString("email");
						}
						ResultSet r2=db1.executeQuery(stu);
						if(r2.next())
						{
							studentName = r2.getString("name");
						}						
						
				        String smtp = "smtp.163.com";// qq������
				        String from = "mykindletui@163.com";// �ʼ���ʾ����
				        String to = teacherEmail;// �ռ��˵��ʼ���ַ����������ʵ��ַ
				        String copyto = "mykindletui@163.com";// �������ʼ���ַ
				        String subject = "����ϵͳ����";// �ʼ�����
				        String content = "���!�԰�����������Ǽ��ʼƻ��������˸�Ķ��" + studentName + "ͬѧ" + "����������ʦ����";// �ʼ�����
				        String username = "mykindletui";// ��������ʵ���˻���
				        String password = "110220aaa";// ����������
				        System.out.println(content);
				        System.out.println(teacherEmail);
				        Mail.sendAndCc(smtp, from, to, copyto, subject, content, username, password);
				        
				        //
						return "HavaRefresh";
					}
				}
			}
		}
		return "DontApp";
}
	
	
	
	
	
	
	
	
	

}
