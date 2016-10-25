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
	private String Sex;
	private String Age;
	private String Telephone;
	private String Specialty;
	private String Introduce;
	
	 public void setId(String Id)
	 {
		 this.Id=Id;
	 }
	 public String getId()
	 {
		 return this.Id;
	 }
	 
	 public void setSpecialty(String Specialty)
	 {
		 this.Specialty=Specialty;
	 }
	 public String getSpecialty()
	 {
		 return this.Specialty;
	 }
	 
	 public void setIntroduce(String Introduce)
	 {
		 this.Introduce=Introduce;
	 }
	 public String getIntroduce()
	 {
		 return this.Introduce;
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
	 
	 public void setSex(String Sex)
	 {
		 this.Sex=Sex;
	 }
	 public String getSex()
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
	 
	 
	 
	 
	 public String Check() throws Exception
	 {
		 if(getStatus()=="0")//满足条件说明是教师注册
		 {
			 DB SQL=new DB();
			 String TeacherName="select* from teacher where user_name="+"'"+getUserName()+"'";
			 ResultSet     RsN=SQL.executeQuery(TeacherName);
			 if( RsN.next() )
			 {
				 return "TeacherNameExist";
			 }
			 return "TeacherRegistering";
		 }
		 //往下执行说明是学生注册
		 else
		 {
			 DB SQL=new DB();
			 String TeacherName="select* from student where user_name="+"'"+getUserName()+"'";
			 ResultSet     RsN=SQL.executeQuery(TeacherName);
			 if( RsN.next() )
			 {
				 return "StudentNameExist";
			 }
			 return "StudentRegistering";
		 }
	 }
	 
	 
	 public String StudentRegisterAction()throws Exception
	 {
		
		     DB SQL=new DB();
			 String Student="insert into student values("    +"null"+","
				                                             +"'"+getUserName()+"'"+","
						                                     +"'"+getPassword()+"'"+","
				                                             +"'"+getStudentId()+"'"+","
						                                     +"'"+getName()+"'"+","
				                                             +"'"+getSex()+"'"+","
				                                             +"'"+getAge()+"'"+","
				                                             +"'"+getTelephone()+"'"+","
				                                             +"'"+getEmail()+"'"+","
				                                             +"'"+getSpecialty()+"'"+","
				                                             +"'"+getIntroduce()+"'"
			                                        +")";
			    try
				{
				    SQL.executeUpdate(Student);
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
			 String Teacher="insert into teacher values("    +"null"+","
										                     +"'"+getUserName()+"'"+","
										                     +"'"+getPassword()+"'"+","
										                     +"'"+getName()+"'"+","
										                     +"'"+getSex()+"'"+","
										                     +"'"+getAge()+"'"+","
										                     +"'"+getTelephone()+"'"+","
										                     +"'"+getEmail()+"'"+","
										                     +"'"+getSpecialty()+"'"+","
										                     +"'"+getIntroduce()+"'"
                                                    +")";
				try
				{
				SQL.executeUpdate(Teacher);
				return "TeacherRegisterSuccessed!";
				}
				catch(Exception e)
				{
				return "TeacherRegisterFail!";
				}
	 }
		 
	
	

}
