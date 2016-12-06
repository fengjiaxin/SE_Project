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

public class StudentSearchAction extends Student{
	private int AcademyId;
	private Map<Integer,List<Major>> Mymap;
	
	private List<Academy>Mylist;

	public int getAcademyId() {
		return AcademyId;
	}

	public void setAcademyId(int academyId) {
		AcademyId = academyId;
	}
	
	public StudentSearchAction() throws Exception
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
		List<Teacher>TeacherlList = new ArrayList<Teacher>();
		String s="select * from student where id="+getId();
		ResultSet r = mydb.executeQuery(s);
		if(r.next())
		{
			setName(r.getString("name"));
			setAge(r.getString("age"));
			setEmail(r.getString("email"));
			setSex(r.getString("sex"));
			setTelephone(r.getString("telephone"));
			setInvitStation(r.getString("InvitStation"));
			setApplyStation(r.getString("ApplyStation"));
		}
		
		s = "select academyname from department where academyid="+getAcademyId();
		System.out.println(s);
		ResultSet r2 = mydb.executeQuery(s);
		if(r2.next())
		{
			String academy = r2.getString("academyname");
			String sels = "select * from teacherlabel where academy="+"'"+academy+"'";
			System.out.println(sels);
			 r = mydb.executeQuery(sels);
			while(r.next())
			{
				Teacher findteacher = new Teacher();
				int teaid = r.getInt("id");
				String res = r.getString("research");
				String selname = "select name from teacher where id="+teaid;
				System.out.println(selname);
				ResultSet nR = mydb2.executeQuery(selname);
				if(nR.next())
				{
					findteacher.setId(teaid);
					findteacher.setName(nR.getString("name"));
					findteacher.setAcademy(academy);
					findteacher.setResearch(res);
					TeacherlList.add(findteacher);
				}
				
			}
		}
		HttpServletRequest request = ServletActionContext.getRequest();
    	request.setAttribute("List", TeacherlList);
    	request.setAttribute("Id", getId());
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
