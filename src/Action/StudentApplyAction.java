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
				if(rsnt.next())//到对应的老师
				{
					if(rsnt.getInt("num")>=10)//执行这一步说明与该导师达成意向的学生人数已达导师需求的上限
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
							RepeatArry=slist.split("[a-zA-Z]");//进一步分割
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
									return "DontApp";//说明该老师已经申请过了
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
						//           向导师发送邮件提醒
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
						
				        String smtp = "smtp.163.com";// qq服务器
				        String from = "mykindletui@163.com";// 邮件显示名称
				        String to = teacherEmail;// 收件人的邮件地址，必须是真实地址
				        String copyto = "mykindletui@163.com";// 抄送人邮件地址
				        String subject = "考研系统提醒";// 邮件标题
				        String content = "你好!自爱发哈服务费是减肥计划阿法狗人格的多个" + studentName + "同学" + "向您发出导师申请";// 邮件内容
				        String username = "mykindletui";// 发件人真实的账户名
				        String password = "110220aaa";// 发件人密码
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
