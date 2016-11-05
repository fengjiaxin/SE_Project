package Action;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;
import Data.Student;
import Data.Academy;
import Data.Major;
public class StudentPageAction extends Student{
	private Map<Integer,List<Major>> Mymap;
	
	private List<Academy>Mylist;

	public Map<Integer,List<Major>> getMymap() {
		return Mymap;
	}

	public void setMymap(Map<Integer,List<Major>> mymap) {
		Mymap = mymap;
	}

	public List<Academy> getMylist() {
		return Mylist;
	}

	public void setMylist(List<Academy> mylist) {
		Mylist = mylist;
	}
	
	public StudentPageAction() throws Exception
	{
		DB SQL = new DB();
		String s = "select * from department"; 
		ResultSet r = SQL.executeQuery(s);
		Mymap = new HashMap<Integer,List<Major>>();
		Mylist = new ArrayList<Academy>();
		while(r.next())
		{
			
			Academy am = new Academy();
			am.setId(r.getInt("academyid"));
			am.setName(r.getString("academyname"));
			Mylist.add(am);
			String majors[] = r.getString("major").split(",");
			List<Major> majorlist = new ArrayList<Major>();
			for(int i = 0; i < majors.length; i++)
			{
				Major mj = new Major();
				mj.setId(i+1);
				mj.setName(majors[i]);
				majorlist.add(mj);
			}
			Mymap.put(am.getId(), majorlist);
			
		}		
	}
	
	public String Display() throws Exception
	{
		DB mydb = new DB();
		String s ="select * from student where id=" + getId();
		ResultSet r=mydb.executeQuery(s);
		if(r.next())
		{
			setName(r.getString("name"));
			setAge(r.getString("age"));
			setEmail(r.getString("email"));
			setSex(r.getString("sex"));
			setTelephone(r.getString("telephone"));
		}
		
		s = "select * from studentlabel where id=" + getId();
		r = mydb.executeQuery(s);
		if(r.next())
		{
			setPoint(r.getString("point"));
			setAcademy(r.getString("academy"));
			setMajor(r.getString("major"));
			setInterest(r.getString("interest"));
			setExperience(r.getString("experience"));
			setHonor(r.getString("honor"));
		}
		return "Display";
	}
	
	public String ToUpdate() throws Exception
	{
		DB mydb = new DB();
		
		String s ="select * from student where id=" + getId();
		ResultSet r=mydb.executeQuery(s);
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
		return "ToUpdate";
	}
	
	public String Update()
	{
		DB mydb = new DB();
		String s = "update studentlabel set point="+getPoint()+
											",academy="+"'"+getAcademy()+"'"+
											",major="+"'"+getMajor()+"'"+
											",interest="+"'"+getInterest()+"'"+
											",experience="+"'"+getExperience()+"'"+
											",honor="+"'"+getHonor()+"'"+
											" where id="+getId()+";";
		System.out.println(s);
		mydb.executeUpdate(s);
		return "Update";
	}
}
