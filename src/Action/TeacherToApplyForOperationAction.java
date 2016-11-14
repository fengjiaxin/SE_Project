package Action;
import DBcon.DB;

import java.sql.ResultSet;

import org.apache.struts2.ServletActionContext;

public class TeacherToApplyForOperationAction {
	
	private String Id;
	public void SetId(String Id)
	{
		this.Id=Id;
	}
	public String getId()
	{
		return this.Id;
	}
	
	public String TeacherAgree() throws Exception
	{
		String TeacherID=ServletActionContext.getRequest().getParameter("TeacherId");
		String StudentID=ServletActionContext.getRequest().getParameter("StudentId");
		int neednum=0;
		int aggrenum=0;
		SetId(TeacherID);
		DB ltdb=new DB();
		String t="select* from teacherlabel where id="+TeacherID;
		ResultSet rlsn=ltdb.executeQuery(t);
		if(rlsn.next())
		{
			neednum=rlsn.getInt("neednum");
		}
		DB tdb=new DB();
		String Tstr="select* from teacherlist where id="+TeacherID;
		ResultSet Tsrn=tdb.executeQuery(Tstr);
		
		if(Tsrn.next())
		{
			aggrenum=Tsrn.getInt("agreenum");
			if(aggrenum>=neednum)
			{
				return "AgreeFail";
			}
			int num=Tsrn.getInt("num");
			String Tlist=Tsrn.getString("list");
			String string="";
			String str="";
			String[] arry=Tlist.split(",");
			int length=arry.length;
			for(int i=0;i<length;i++)
			{
				String[] Tarry=arry[i].split(":");
				if(StudentID.equals(Tarry[0]))
				{
					str=StudentID+":Y";
				}
				else
				{
					if(string.length()==0)
					{
						string=arry[i];
					}
					else
					{
						string+=","+arry[i];
					}
				}
			}
			if(string.length()==0)
			{
				string=str;
			}
			else
			{
				string+=","+str;
			}
			aggrenum++;
			String s = "update teacherlist set list="+"'"+string+"'"+",num="+num+",agreenum="+aggrenum+ " where id="+TeacherID+";";
			tdb.executeUpdate(s);
			System.out.println(s);
		}
		    DB sdb=new DB();
			String Sstr="select* from studentlist where id="+StudentID;
			ResultSet Ssrn=sdb.executeQuery(Sstr);
			if(Ssrn.next())
			{
				int num=Ssrn.getInt("num");
				String Slist=Ssrn.getString("list");
				String string="";
				String str="";
				String[] arry=Slist.split(",");
				int length=arry.length;
				for(int i=0;i<length;i++)
				{
					String[] Sarry=arry[i].split(":");
					if(TeacherID.equals(Sarry[0]))
					{
						str=TeacherID+":T";
					}
					else
					{
						if(string.length()==0)
						{
							string=arry[i];
						}
						else
						{
							string+=","+arry[i];
						}
					}
				}
				if(string.length()==0)
				{
					string=str;
				}
				else
				{
					string+=","+str;
				}
				String s = "update studentlist set list="+"'"+string+"'"+",num="+num+" where id="+StudentID+";";
				sdb.executeUpdate(s);
	   }
		
			return "Agree";
	}
	
	
	public String TeacherReject() throws Exception
	{
		String TeacherID=ServletActionContext.getRequest().getParameter("TeacherId");
		String StudentID=ServletActionContext.getRequest().getParameter("StudentId");
		SetId(TeacherID);
	    DB sdb=new DB();                                                 
		String Sstr="select* from studentlist where id="+StudentID;
		ResultSet Ssrn=sdb.executeQuery(Sstr);
		if(Ssrn.next())
		{
			String Slist=Ssrn.getString("list");
			String string="";
			String str="";
			String[] arry=Slist.split(",");
			int length=arry.length;
			for(int i=0;i<length;i++)
			{
				String[] Sarry=arry[i].split(":");
				if(TeacherID.equals(Sarry[0]))
				{
					str=TeacherID+":J";
				}
				else
				{
					if(string.length()==0)
					{
						string=arry[i];
					}
					else
					{
						string+=","+arry[i];
					}
				}
			}
			if(string.length()==0)
			{
				string=str;
			}
			else
			{
				string+=","+str;
			}
			String s = "update studentlist set list="+"'"+string+"'"+" where id="+StudentID+";";
			sdb.executeUpdate(s);
		}
		
		DB tdb=new DB();
		String Tstr="select* from teacherlist where id="+TeacherID;
		ResultSet Tsrn=sdb.executeQuery(Tstr);
		if(Tsrn.next())
		{
			String Tlist=Tsrn.getString("list");
			String string="";
			String[] arry=Tlist.split(",");
			int length=arry.length;
			for(int i=0;i<length;i++)
			{
				String[] Tarry=arry[i].split(":");
				if(!StudentID.equals(Tarry[0]))
				{
					if(string.length()==0)
					{
						string=arry[i];
					}
					else
					{
						string+=","+arry[i];
					}
				}
			}
			String s = "update teacherlist set list="+"'"+string+"'"+" where id="+TeacherID+";";
			tdb.executeUpdate(s);
		}
		  return "Reject";
	 }
}
