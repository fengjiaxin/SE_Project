package Action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;
import Data.Academy;
import Data.Teacher;

public class toTeacherInquiryAction extends Teacher{
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
		s ="select * from teacher where id="+getId();
		r = mydb.executeQuery(s);
		if(r.next())
		{
			setName(r.getString("name"));
			setAge(r.getString("age"));
			setEmail(r.getString("email"));
			setSex(r.getString("sex"));
			setTelephone(r.getString("telephone"));
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
    	request.setAttribute("Id", getId());
		return "toTeacherInquiry";
	}

}
