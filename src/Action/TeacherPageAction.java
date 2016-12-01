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

import Data.Teacher;
import Data.Research;
public class TeacherPageAction extends Teacher{
	private Map<Integer,List<Research>> Mymap;
	
	private List<Academy>Mylist;

	public List<Academy> getMylist() {
		return Mylist;
	}

	public void setMylist(List<Academy> mylist) {
		Mylist = mylist;
	}

	public Map<Integer,List<Research>> getMymap() {
		return Mymap;
	}

	public void setMymap(Map<Integer,List<Research>> mymap) {
		Mymap = mymap;
	}
	
	public TeacherPageAction() throws Exception
	{
		DB SQL = new DB();
		String s = "select * from department"; 
		ResultSet r = SQL.executeQuery(s);
		Mymap = new HashMap<Integer,List<Research>>();
		Mylist = new ArrayList<Academy>();
		while(r.next())
		{
			
			Academy am = new Academy();
			am.setId(r.getInt("academyid"));
			am.setName(r.getString("academyname"));
			Mylist.add(am);
			String reseachs[] = r.getString("research").split(",");
			List<Research> majorlist = new ArrayList<Research>();
			for(int i = 0; i < reseachs.length; i++)
			{
				Research mj = new Research();
				mj.setId(i+1);
				mj.setName(reseachs[i]);
				majorlist.add(mj);
			}
			Mymap.put(am.getId(), majorlist);
			
		}		
	}
	
	public String Display() throws Exception
	{
		
		DB mydb = new DB();
		String s ="select * from teacher where id=" + getId();
		ResultSet r=mydb.executeQuery(s);
		if(r.next())
		{
			setName(r.getString("name"));
			setAge(r.getString("age"));
			setEmail(r.getString("email"));
			setSex(r.getString("sex"));
			setTelephone(r.getString("telephone"));
			setApplyStation(r.getString("ApplyStation"));
		}
		
		s = "select * from teacherlabel where id=" + getId();
		r = mydb.executeQuery(s);
		if(r.next())
		{
			
			setAcademy(r.getString("academy"));
			setResearch(r.getString("research"));
			setArticle(r.getString("article"));
			setExperience(r.getString("experience"));
			setHonor(r.getString("honor"));
			setNeedNum(r.getInt("neednum"));
			setNeeds(r.getString("needs"));
		}
		return "Display";
	}
	
	public String ToUpdate() throws Exception
	{
		DB mydb = new DB();
		String s ="select * from teacher where id=" + getId();
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
	
	public String Update() throws Exception
	{
		
		DB mydb = new DB();
		String staca = "select * from department where academyid="+getAcademy();
		String aca="";
		String SResearch="";
		String Researchs[];
		int maI=Integer.parseInt(getResearch());
		ResultSet r= mydb.executeQuery(staca);
		if(r.next())
		{
			aca=r.getString("academyname");
			Researchs=r.getString("research").split(",");
			SResearch=Researchs[maI-1];
		}
		
		
		String s = "update teacherlabel set academy="+"'"+aca+"'"+
											
											",research="+"'"+SResearch+"'"+
											",article="+"'"+getArticle()+"'"+
											",experience="+"'"+getExperience()+"'"+
											",honor="+"'"+getHonor()+"'"+
											",neednum="+getNeedNum()+
											",needs="+"'"+getNeeds()+"'"+
											" where id="+getId()+";";
		mydb.executeUpdate(s);
		return "Update";
	}
}
