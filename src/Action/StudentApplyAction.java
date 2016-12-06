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
        if(rsn.next())//首先找到学生
		{
			setId(rsn.getInt("id"));
			if(rsn.getInt("num")>=3)//执行这个分支说明学生申请导师的数量已经达到上限，不能再申请了
			{
				return "DontApp";
			}
			
			else//说明学生可以向导师发出申请
			{
				DB dbt=new DB();
				String strt="select* from teacherlist where id="+TeacherID;
				ResultSet rsnt=dbt.executeQuery(strt);
				if(rsnt.next())//找到对应的老师
				{
					int n=10;//存储老师招生人数
					DB dbtr=new DB();
					String st="select* from teacherlabel where id="+TeacherID;
					ResultSet rstnr=dbtr.executeQuery(st);
					if(rstnr.next())
					{
						n=rstnr.getInt("neednum");
					}
					
					if(rsnt.getInt("num")>=n)//执行这一步说明与该导师达成意向的学生人数已达导师需求的上限
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
									return "DontApp";//说明已经向该导师发出过申请
								}
							}
						}
						//往下执行说明可以进行申请
						//首先更新学生信息
						if(num==0)
						{
							slist=TeacherID+":D";//考虑第一次申请或申请过但由于种种原因导致list为空的情况
						}
						else
						{
							slist+=","+TeacherID+":D";
						}
						num++;
						String s = "update studentlist set list="+"'"+slist+"'"+",num="+num+" where id="+StudentID+";";
						db.executeUpdate(s);
						//更新老师信息
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
