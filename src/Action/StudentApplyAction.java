package Action;

import java.sql.ResultSet;



import org.apache.struts2.ServletActionContext;

import DBcon.DB;

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
		String TeacherID;
		String StudentID;
		
		TeacherID=ServletActionContext.getRequest().getParameter("TeacherId");
		StudentID=ServletActionContext.getRequest().getParameter("StudentId");
		setId(Integer.parseInt(StudentID));
		System.out.println(TeacherID);
		System.out.println(StudentID);
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
				if(rsnt.next())//�ҵ���Ӧ����ʦ
				{
					int n=10;//�洢��ʦ��������
					DB dbtr=new DB();
					String st="select* from teacherlabel where id="+TeacherID;
					ResultSet rstnr=dbtr.executeQuery(st);
					if(rstnr.next())
					{
						n=rstnr.getInt("neednum");
					}
					
					if(rsnt.getInt("num")>=n)//ִ����һ��˵����õ�ʦ��������ѧ�������Ѵﵼʦ���������
					{
						return "DontApp";
					}
					else
					{
						int length=0;
						int num=rsn.getInt("num");
						String slist=rsn.getString("list");	
						if(rsn.getString("list") != null)
						{
							String[] list=slist.split(",");
							String[] rlist={};
							length=list.length;
							for(int i=0;i<length;i++)
							{
								rlist=list[i].split(":");
								if(rlist[0].equals(TeacherID))
								{
									return "DontApp";//˵���Ѿ���õ�ʦ����������
								}
							}
						}
						//����ִ��˵�����Խ�������
						//���ȸ���ѧ����Ϣ
						if(num==0)
						{
							slist=TeacherID+":D";//���ǵ�һ����������������������ԭ����listΪ�յ����
						}
						else
						{
							slist+=","+TeacherID+":D";
						}
						num++;
						String s = "update studentlist set list="+"'"+slist+"'"+",num="+num+" where id="+StudentID+";";
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
						
						System.out.println(td);
						d.executeUpdate(td);
						String sss="update student set  ApplyStation="+1+" where id="+StudentID;
						System.out.println(sss);
						d.executeUpdate(sss);
						return "HavaRefresh";
					}
				}
			}
		}
		return "DontApp";
}
	
	
	
	
	
	
	
	
	

}
