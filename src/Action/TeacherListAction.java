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
import Data.ApplyStudent;
import Data.Teacher;

public class TeacherListAction extends Teacher{
	private List<Academy>Mylist;
	
	public List<Academy> getMylist() {
		return Mylist;
	}

	public void setMylist(List<Academy> mylist) {
		Mylist = mylist;
	}
	
	public TeacherListAction() throws Exception
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
		StatusMap.put("W", "未选择");
		StatusMap.put("Y", "已同意");
		StatusMap.put("Q", "已确认");
		List<ApplyStudent>StudentList = new ArrayList<ApplyStudent>();
		DB mydb = new DB();
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
		
		s = "select * from teacherlist where id="+getId();
		r = mydb.executeQuery(s);
		int num = 0;
		if(r.next())
		{
			if(r.getString("list") != null && !r.getString("list").matches("\\s*"))
			{
				String list[] =  r.getString("list").split(",");
				num = list.length;
				for (int i=0;i<num;i++)
				{
					String idandsta[] = list[i].split(":");
					s = "select name from student where id="+idandsta[0];
					ResultSet r2 = mydb.executeQuery(s);
					if(r2.next())
					{
						ApplyStudent one = new ApplyStudent();
						one.setName(r2.getString("name"));
						one.setId(Integer.parseInt(idandsta[0]));
						one.setStatus(idandsta[1]);
						StudentList.add(one);
					}
					
				}
			}
			
			
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("Id", getId());
    	request.setAttribute("StudentList", StudentList);
    	request.setAttribute("Map", StatusMap);
		return "ListDisplay";
	}

}
