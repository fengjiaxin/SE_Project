package Action;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;
import Data.Teacher;

public class toTeacherEditAction extends Teacher {
	private String UsrName;
	private String Password;
	public String excute() throws Exception
	{
		DB mydb = new DB();
		String s2 ="select * from teacher where id=" + getId();
		System.out.println(getId());
		ResultSet r2=mydb.executeQuery(s2);
		if(r2.next())
		{
			setId(getId());
			setUsrName(r2.getString("user_name"));
			setPassword(r2.getString("password"));
			setName(r2.getString("name"));
			setAge(r2.getString("age"));
			setEmail(r2.getString("email"));
			setSex(r2.getString("sex"));
			setTelephone(r2.getString("telephone"));
			setApplyStation(r2.getString("ApplyStation"));
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("Id ", getId());

		return "toTeacherEdit";
	}


	public String getUsrName() {
		return UsrName;
	}


	public void setUsrName(String usrName) {
		UsrName = usrName;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}
	
}

	
	
	
	
	


