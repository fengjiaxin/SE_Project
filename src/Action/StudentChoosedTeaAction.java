package Action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;
import Data.Student;
import Data.Teacher;

public class StudentChoosedTeaAction extends Student{
	/*找出学生所确定的导师*/
	
	
	
	public String excute() throws Exception
	{
		List<Teacher>TeacherList = new ArrayList<Teacher>();
		DB mydb = new DB();
		String s="select * from student where id="+getId();
		
		ResultSet r = mydb.executeQuery(s);
		
		/*导航栏信息设置*/
		if(r.next())
		{
			setName(r.getString("name"));
			setAge(r.getString("age"));
			setEmail(r.getString("email"));
			setSex(r.getString("sex"));
			setTelephone(r.getString("telephone"));
			setInviteStation(r.getString("InviteStation"));
			setApplyStation(r.getString("ApplyStation"));
		}
		
		
		s = "select * from studentlist where id="+getId();
		
		ResultSet r1 = mydb.executeQuery(s);
		int num = 0;
		if (r1.next())
		{
			num = r1.getInt("num") ;
		}
		
		if(num == 0 || num > 1)//该生还没确定导师
		{
			return "success";
		}
		else
		{
			String teaId_sta[] = r1.getString("list").split(":");
			if(teaId_sta[1].equals("Q"))
			{
				Teacher one = new Teacher();
				String findTea = "select * from teacher where id=" + teaId_sta[0];
				ResultSet r2 = mydb.executeQuery(findTea);
				if(r2.next())
				{
					one.setName(r2.getString("name"));
					one.setTelephone(r2.getString("telephone"));
					one.setEmail(r2.getString("email"));
							
				}
				findTea = "select * from teacherlabel where id=" + teaId_sta[0];
				r2 = mydb.executeQuery(findTea);
				if(r2.next())
				{
					one.setAcademy(r2.getString("academy"));
					one.setResearch(r2.getString("research"));
				}
				TeacherList.add(one);
			}
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("Id", getId());
		request.setAttribute("TeacherList", TeacherList);
		
		return "success";
	}
	

}
