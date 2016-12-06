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
import Data.ApplyTeacher;
import Data.Student;

public class StudentListAction extends Student{
	private List<Academy>Mylist;
	
	public List<Academy> getMylist() {
		return Mylist;
	}

	public void setMylist(List<Academy> mylist) {
		Mylist = mylist;
	}
	
	public StudentListAction() throws Exception
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
	}
	
	public String listdisplay() throws Exception
	{
		Map<String,String> StatusMap = new HashMap<String,String>();
		StatusMap.put("D", "待定");
		StatusMap.put("T", "同意");
		StatusMap.put("Q", "确定");
		StatusMap.put("J", "拒绝");
		List<ApplyTeacher>TeacherList = new ArrayList<ApplyTeacher>();
		
		DB mydb = new DB();
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
		
		s = "select * from studentlist where id="+getId();
		r = mydb.executeQuery(s);
		int num = 0;
		
		if(r.next())
		{
			num = r.getInt("num");
			
			if(num != 0)
			{
				String list[] =  r.getString("list").split(",");
				for (int i=0;i<num;i++)
				{
					String idandsta[] = list[i].split(":");
					s = "select name from teacher where id="+idandsta[0];
					ResultSet r2 = mydb.executeQuery(s);
					if(r2.next())
					{
						ApplyTeacher one = new ApplyTeacher();
						one.setName(r2.getString("name"));
						one.setId(Integer.parseInt(idandsta[0]));
						one.setStatus(idandsta[1]);
						TeacherList.add(one);
					}
					
				}
			}
			
			
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("Id", getId());
    	request.setAttribute("TeacherList", TeacherList);
    	request.setAttribute("Map", StatusMap);
    	return "ListDisplay";
	}
}
