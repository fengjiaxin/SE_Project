package Action;

import java.sql.ResultSet;



import org.apache.struts2.ServletActionContext;

import DBcon.DB;

public class StudentApplyAction {
	
	
	
	
	public String excute() throws Exception
	{
		DB db=new DB();
		String TeacherID;
		String StudentID;
		
		TeacherID=ServletActionContext.getRequest().getParameter("TeacherId");
		StudentID=ServletActionContext.getRequest().getParameter("StudentId");
		
		String str="select* from studentlist where id="+StudentID;
		ResultSet rsn=db.executeQuery(str);
		System.out.println(str);
		if(rsn.next())//�����ҵ�ѧ��
		{
			if(rsn.getInt("num")>=3)//ִ�������֧˵��ѧ�����뵼ʦ�������Ѿ��ﵽ���ޣ�������������
			{
				return "DontApp";
			}
			else//˵��ѧ��������ʦ��������
			{
				DB dbt=new DB();
				String strt="select* from teacherlist where id="+TeacherID;
				ResultSet rsnt=dbt.executeQuery(strt);
				if(rsnt.next())//�ҵ���Ӧ����ʦ
				{
					if(rsnt.getInt("num")>=10)//ִ����һ��˵����õ�ʦ��������ѧ�������Ѵﵼʦ���������
					{
						return "DontApp";
					}
					else
					{
						int length=0;
						String ID_temporary="";
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
						}
						
						length=RepeatArry.length;
						for(int i=0;i<length;i++)
						{
							if( RepeatArry[i].equals(":")|| i==length-1)
							{
								if(ID_temporary.equals(TeacherID))
								{
									return "DontApp";//˵������ʦ�Ѿ��������
								}
								ID_temporary="";
							}
							else
							{
								ID_temporary+=RepeatArry[i];
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
						int T_num=rsnt.getInt("num");
						if(rsnt.getInt("num")==0)
						{
							T_list=StudentID+":W";
						}
						else
						{
							T_list+=","+StudentID+":W";
						}
						T_num++;
						String t="update teacherlist set list="+"'"+T_list+"'"+",num="+T_num+" where id="+TeacherID+";";
						System.out.println(t);
						dbt.executeUpdate(t);
						return "HavaRefresh";
					}
				}
			}
		}
		return "DontApp";
}
	
	
	
	
	
	
	
	
	

}
