package Action;

import java.sql.ResultSet;

import DBcon.DB;
public class MainAction {
	 private String UserName;
	 private String Password;
	 private int   InStatus;
	 private int   ReStatus;
	 private int Id;
	 
	 public void setId(int Id)
	 {
		 this.Id = Id;
	 }
	 public int getId()
	 {
		 return this.Id;
	 }
	 
	 
	 public void setUserName(String UserName)
	 {
		 this.UserName=UserName;
	 }
	 public String getUserName()
	 {
		 return this.UserName;
	 }
	 
	 
	 public void setPassword(String Password)
	 {
		 this.Password=Password;
	 }
	 public String getPassword()
	 {
		 return this.Password;
	 }
	 
	 
	
	 
	 
	
	 
	 public String LoginAction() throws Exception
	 {
		 DB SQL=new DB();
		 if(getInStatus()==0)//老师登陆
		 {
			
			 String TeacherName="select * from teacher where user_name="+"'"+getUserName()+"'";
			 ResultSet                                 RsN=SQL.executeQuery(TeacherName);
			 if(RsN.next())
			 {
			     
				 if(RsN.getString("password").equals(getPassword()))
				 {
					 setId(RsN.getInt("id"));
					 return "TeacherLogin";//教师登陆成功
				 }
				 
			 }
			 return "Password_or_UsernameError";//教师登陆密码输入错误
		 }
			 //执行下面说明是学生登陆
	   else
	    {
				 String StudentName="select * from student where user_name="+"'"+getUserName()+"'";
				 ResultSet                                 RsN=SQL.executeQuery(StudentName);
				 if(RsN.next())
				 {
					 if(RsN.getString("password").equals(getPassword()))
					 {
						 setId(RsN.getInt("id"));
						 return "StudentLogin";
					 }
				 }
				 return "StudentLoginfail";//学生登陆失败
		}
			 
	}
	 
	 
	 
	 public String ToRegister()
	 {
		 if(getReStatus()== 0)
		 {
			 return "TeacherRegister";
		 }
		 else
		 {
			 return "StudentRegister"; 
		 }
	 }
	 
	 public String InitialPage()
	 {
		 return "InitialPage";
	 }
	public int getReStatus() {
		return ReStatus;
	}
	public void setReStatus(int reStatus) {
		ReStatus = reStatus;
	}
	public int getInStatus() {
		return InStatus;
	}
	public void setInStatus(int inStatus) {
		InStatus = inStatus;
	}
	





}