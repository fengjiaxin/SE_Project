package Action;

import DBcon.DB;

public class StudentEditAction {
	private String Id;
	private String UserName;
	private String StudentId;
	
	private String Password;
	
	private String Email;
	
	private String Name;
	private int Sex;
	private String Age;
	private String Telephone;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getStudentId() {
		return StudentId;
	}
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getSex() {
		return Sex;
	}
	public void setSex(int sex) {
		Sex = sex;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	
	public String excute() 
	{
		DB mydb= new DB();
		String gender;
		 if (getSex() == 1)
		 {
			 gender="ÄÐ";
		 }
		 else
		 {
			 gender="Å®";
		 }
		 String s="update student set password='"+getPassword()+"',"
				 					  +"email='"+getEmail()+"',"
				 					  +"name='"+getName()+"',"
				 					  +"sex='"+gender+"',"
				 					  +"telephone='"+getTelephone()+"',"
				 					  +"age="+getAge()
				 					  +"where id="+getId();
		 mydb.executeUpdate(s);
		 return "EditSuccess";
	}
		
	
}
