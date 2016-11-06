package Action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;
import Data.Academy;
import Data.Major;
import Data.Student;
import Data.Teacher;

public class TeacherSearchAction extends Teacher{
	private int AcademyId;
	private Map<Integer,List<Major>> Mymap;
	
	private List<Academy>Mylist;

	public int getAcademyId() {
		return AcademyId;
	}

	public void setAcademyId(int academyId) {
		AcademyId = academyId;
	}
	
	public TeacherSearchAction() throws Exception
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
	
	public String excute() throws Exception
	{
		DB mydb = new DB();
		DB mydb2 = new DB();
		List<Student>StudentList = new ArrayList<Student>();
		String s="select * from teacher where id="+getId();
		ResultSet r = mydb.executeQuery(s);
		if(r.next())
		{
			setName(r.getString("name"));
			setAge(r.getString("age"));
			setEmail(r.getString("email"));
			setSex(r.getString("sex"));
			setTelephone(r.getString("telephone"));
		}
		
		s = "select academyname from department where academyid="+getAcademyId();
		System.out.println(s);
		ResultSet r2 = mydb.executeQuery(s);
		if(r2.next())
		{
			String academy = r2.getString("academyname");
			String sels = "select * from studentlabel where academy="+"'"+academy+"'";
			System.out.println(sels);
			 r = mydb.executeQuery(sels);
			while(r.next())
			{
				Student findstudent = new Student();
				int teaid = r.getInt("id");
				String res = r.getString("interest");
				String selname = "select name from student where id="+teaid;
				System.out.println(selname);
				ResultSet nR = mydb2.executeQuery(selname);
				if(nR.next())
				{
					findstudent.setId(teaid);
					findstudent.setName(nR.getString("name"));
					findstudent.setAcademy(academy);
					findstudent.setInterest(res);
					StudentList.add(findstudent);
				}
				
			}
		}
		HttpServletRequest request = ServletActionContext.getRequest();
    	request.setAttribute("List", StudentList);
		return "Search";
	}
	
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
}
