package Action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import DBcon.DB;
import Data.ApplyTeacher;
import Data.InviteTeacher;
import Data.Student;

public class InviteAction extends Student{
	/*学生点击邀请列表后的操作*/
	/*根据studentlist中的teacherId找到对应老师的信息  */
	
	public String InviteListDisplay() throws Exception
	{
		List<InviteTeacher>TeacherList = new ArrayList<InviteTeacher>();
		
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
		}
		
		s = "select * from studentlist where id="+getId();
		r = mydb.executeQuery(s);
		
		if(r.next())
		{
			String list[] =  r.getString("teacherId").split(",");
			for (int i=0;i<list.length;i++)
			{
				InviteTeacher one = new InviteTeacher();
				s = "select * from teacher where id="+list[0];
				ResultSet r2 = mydb.executeQuery(s);
				if(r2.next())
				{
					one.setName(r2.getString("name"));
					one.setId(Integer.parseInt(list[0]));
				}
				s = "select * from teacherlabel where id="+list[0];
				ResultSet r3 = mydb.executeQuery(s);
				if(r3.next())
					one.setAcademy(r3.getString("Academy"));
				
				TeacherList.add(one);
			}
					
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("Id", getId());
    	request.setAttribute("TeacherList", TeacherList);
    	return "InviteListDisplay";
	}
}
