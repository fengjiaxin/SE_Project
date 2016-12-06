package Action;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;
import Data.Student;

public class toStudentEditAction extends Student{
	private String UsrName;
	private String StudentId;
	private String Password;
	public String excute() throws Exception
	{
		DB mydb = new DB();
		String s2 ="select * from student where id=" + getId();
		ResultSet r2=mydb.executeQuery(s2);
		if(r2.next())
		{
			setId(getId());
			setUsrName(r2.getString("user_name"));
			setStudentId(r2.getString("student_id"));
			setPassword(r2.getString("password"));
			setName(r2.getString("name"));
			setAge(r2.getString("age"));
			setEmail(r2.getString("email"));
			setSex(r2.getString("sex"));
			setTelephone(r2.getString("telephone"));
			setInvitStation(r2.getString("InvitStation"));
			setApplyStation(r2.getString("ApplyStation"));
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("Id ", StudentId);

		return "toStudentEdit";
	}


	public String getUsrName() {
		return UsrName;
	}


	public void setUsrName(String usrName) {
		UsrName = usrName;
	}


	public String getStudentId() {
		return StudentId;
	}


	public void setStudentId(String studentId) {
		StudentId = studentId;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}
	
}
