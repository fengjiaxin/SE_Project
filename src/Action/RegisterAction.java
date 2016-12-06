package Action;
import java.sql.ResultSet;

import DBcon.DB;
public class RegisterAction {
	
	private String Id;
	private String UserName;
	private String Password;
	private String StudentId;
	private String Email;
	private String Status;
	private String Name;
	private int Sex;
	private String Age;
	private String Telephone;
	
	 public void setId(String Id)
	 {
		 this.Id=Id;
	 }
	 public String getId()
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
	 
	 
	 public void setStudentId(String StudentId)
	 {
		 this.StudentId=StudentId;
	 }
	 public String getStudentId()
	 {
		 return this.StudentId;
	 }
	 
	 
	 public void setName(String Name)
	 {
		 this.Name=Name;
	 }
	 public String getName()
	 {
		 return this.Name;
	 }
	 
	 
	 public void setEmail(String Email)
	 {
		 this.Email=Email;
	 }
	 public String getEmail()
	 {
		 return this.Email;
	 }
	 
	 public void setSex(int Sex)
	 {
		 this.Sex=Sex;
	 }
	 public int getSex()
	 {
		 return this.Sex;
	 }
	 
	 public void setAge(String Age)
	 {
		 this.Age=Age;
	 }
	 public String getAge()
	 {
		 return this.Age;
	 }
	 
	 
	 public void setTelephone(String Telephone)
	 {
		 this.Telephone=Telephone;
	 }
	 public String getTelephone()
	 {
		 return this.Telephone;
	 }
	 
	
	 public void setStatus(String Status)
	 {
		 this.Status=Status;
	 }
	 public String getStatus()
	 {
		 return this.Status;
	 }
	 
	 
	 
	 
	 public String StudentRegisterAction()throws Exception
	 {
		
		     DB SQL=new DB();
		     String gender;
		     if (getSex() == 1)
		     {
		    	 gender="ÄÐ";
		     }
		     else
		     {
		    	 gender="Å®";
		     }
			 String Student="insert into student values("    +"null"+","
				                                             +"'"+getUserName()+"'"+","
						                                     +"'"+getPassword()+"'"+","
				                                             +"'"+getStudentId()+"'"+","
						                                     +"'"+getName()+"'"+","
				                                             +"'"+gender+"'"+","
				                                             +"'"+getAge()+"'"+","
				                                             +"'"+getTelephone()+"'"+","
				                                             +"'"+getEmail()+"',"
				                                             +0+","
				                                             +0+","
				                                             +0
			                                        +")";
			    try
				{
				    SQL.executeUpdate(Student);
		
				    String s = "select id from student where user_name='"+getUserName()+"'";
				    ResultSet R = SQL .executeQuery(s);
				    
				    if(R.next())
				    {
				    	int id = R.getInt("id");
				    	DB SDB=new DB();
				    	String S="insert into studentlist values("+id+","+"null"+","+0+",null);";
				    	SDB.executeUpdate(S);
				    	
				    	s = "insert into studentlabel values("+id+","
															  +"null"+","
															  +"null"+","
															  +"null"+","
															  +"null"+","
															  +"null"+","
															  +"null"+")";
				    	
				    	
				    }
				    SQL.executeUpdate(s);
				    
				    return "StudentRegisterSuccessed!";
				}
			    catch(Exception e)
				{
					return "StudentRegisterFail!";
				}
	 }
	 
	 public String TeacherRegisterAction()throws Exception
	 {
		 
	         DB SQL=new DB();
	         String gender;
		     if (getSex() == 1)
		     {
		    	 gender="ÄÐ";
		     }
		     else
		     {
		    	 gender="Å®";
		     }
			 String Teacher="insert into teacher values("    +"null"+","
										                     +"'"+getUserName()+"'"+","
										                     +"'"+getPassword()+"'"+","
										                     +"'"+getName()+"'"+","
										                     +"'"+gender+"'"+","
										                     +"'"+getAge()+"'"+","
										                     +"'"+getTelephone()+"'"+","
										                     +"'"+getEmail()+"',"
										                     +0+","
										                     +0
                                                    +");";
				try
				{
					SQL.executeUpdate(Teacher);
					
					String s = "select id from teacher where user_name='"+getUserName()+"'";
				    ResultSet R = SQL .executeQuery(s);
				   
				    if(R.next())
				    {
				    	int id = R.getInt("id");
				    	s = "insert into teacherlabel values("+id+","
															  +"null"+","
															  +"null"+","
															  +"null"+","
															  +"null"+","
															  +"null"+","
															  +"null"+","
															  +"null"+");";
				    	
				    	DB TDB=new DB();
				    	String T="insert into teacherlist values("+id+","+"null"+","+0+","+0+");";
				    	
				    	TDB.executeUpdate(T);
				    	
				    }
				    SQL.executeUpdate(s);
				return "TeacherRegisterSuccessed!";
				}
				catch(Exception e)
				{
				return "TeacherRegisterFail!";
				}
	 }
		 
	
	

}
