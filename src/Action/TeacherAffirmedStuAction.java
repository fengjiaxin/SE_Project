package Action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;
import Data.Student;
import Data.Teacher;

public class TeacherAffirmedStuAction extends Teacher{
	
	
	public String excute() throws Exception
	{
		DB mydb = new DB();
		String s="select * from teacher where id="+getId();
		ResultSet r = mydb.executeQuery(s);
		List<Student>StudentList = new ArrayList<Student>();
		/*导航栏信息设置*/
		if(r.next())
		{
			setName(r.getString("name"));
			setAge(r.getString("age"));
			setEmail(r.getString("email"));
			setSex(r.getString("sex"));
			setTelephone(r.getString("telephone"));
			setApplyStation(r.getString("ApplyStation"));
		}
		
		s = "select * from teacherlist where id="+getId();
		ResultSet r1 = mydb.executeQuery(s);
		int num = 0;
		if(r1.next())
		{
			num = r1.getInt("num");
		}
		if(num > 0)
		{
			String lists[] = r1.getString("list").split(",");
			
			for (int i=0;i<lists.length;i++)
			{
				String stuId_sta[] = lists[i].split(":");
				if(stuId_sta[1].equals("Q"))
				{
					DB mydb2 = new DB();
					String findStu = "select * from student where id="+stuId_sta[0];
					ResultSet r3 = mydb2.executeQuery(findStu);
					Student one = new Student();
					if(r3.next())
					{
						one.setName(r3.getString("name"));
						one.setSex(r3.getString("sex"));
						one.setTelephone(r3.getString("telephone"));
						one.setEmail(r3.getString("email"));
					}
					
					findStu = "select * from studentlabel where id="+stuId_sta[0];
					r3 = mydb2.executeQuery(findStu);
					if (r3.next())
					{
						one.setAcademy(r3.getString("academy"));
					}
					StudentList.add(one);
				}
			}
		}
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("Id", getId());
    	request.setAttribute("StudentList", StudentList);
		return "success";
	}
	
	

}
