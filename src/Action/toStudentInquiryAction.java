package Action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;
import Data.Academy;
import Data.Student;

public class toStudentInquiryAction extends Student{
	private List<Academy>Mylist;
	
	public List<Academy> getMylist() {
		return Mylist;
	}

	public void setMylist(List<Academy> mylist) {
		Mylist = mylist;
	}
	
	public String excute() throws Exception
	{
		DB SQL = new DB();
		Mylist = new ArrayList<Academy>();
		String s = "select * from department"; 
		ResultSet r = SQL.executeQuery(s);
		while(r.next())
		{
			Academy am = new Academy();
			am.setId(r.getInt("academyid"));
			am.setName(r.getString("academyname"));
			Mylist.add(am);
		}
		
		DB mydb = new DB();
		String s2 ="select * from student where id=" + getId();
		ResultSet r2=mydb.executeQuery(s2);
		if(r2.next())
		{
			setName(r2.getString("name"));
			setAge(r2.getString("age"));
			setEmail(r2.getString("email"));
			setSex(r2.getString("sex"));
			setTelephone(r2.getString("telephone"));
		}
		
		s2 = "select * from studentlabel where id=" + getId();
		r2 = mydb.executeQuery(s2);
		if(r2.next())
		{
			setPoint(r2.getString("point"));
			setAcademy(r2.getString("academy"));
			setMajor(r2.getString("major"));
			setInterest(r2.getString("interest"));
			setExperience(r2.getString("experience"));
			setHonor(r2.getString("honor"));
		}
		HttpServletRequest request = ServletActionContext.getRequest();
    	request.setAttribute("Id", getId());
		return "toStudentInquiry";
	}
}
