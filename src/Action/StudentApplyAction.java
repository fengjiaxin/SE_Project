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
		if(rsn.next())//首先找到学生
		{
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
					if(rsnt.getInt("num")>=10)//执行这一步说明与该导师达成意向的学生人数已达导师需求的上限
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
							RepeatArry=slist.split("[a-zA-Z]");//进一步分割
						}
						
						length=RepeatArry.length;
						for(int i=0;i<length;i++)
						{
							if( RepeatArry[i].equals(":")|| i==length-1)
							{
								if(ID_temporary.equals(TeacherID))
								{
									return "DontApp";//说明该老师已经申请过了
								}
								ID_temporary="";
							}
							else
							{
								ID_temporary+=RepeatArry[i];
							}
						}
						//往下执行说明可以进行申请
						//更新学生信息
						String S_list=rsn.getString("list");
						int    S_num=rsn.getInt("num");
						if(rsn.getInt("num")==0)
						{
							S_list=TeacherID+":D";//考虑第一次申请
						}
						else
						{
							S_list+=","+TeacherID+":D";
						}
						S_num++;
						String s = "update studentlist set list="+"'"+S_list+"'"+",num="+S_num+" where id="+StudentID+";";
						db.executeUpdate(s);
						//更新老师信息
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
