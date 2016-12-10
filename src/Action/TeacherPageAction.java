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
	//以下这5个私有变量是老师对学生的要求,每一行后面的注释代表前台传过来的值
	private String EnglishLevel;//N,L,Y
	private String Grade;//90,80,70,60
	private String Award;//I,C,P,N
	private String SexS;//M,W,N
	private String ExperienceS;//Y,N
	
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
		HttpServletRequest request = ServletActionContext.getRequest();
    	request.setAttribute("Id", getId());
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
			setApplyStation(r.getString("ApplyStation"));
		}
		DB mysl=new DB();
		String sl="select* from teacherlabel where id="+getId();
		ResultSet rsn=mysl.executeQuery(sl);
		if(rsn.next())//为编辑页面做准备，设置默认值，也就是用户上次填写过的值
		{
			setArticle(rsn.getString("article"));
			setExperience(rsn.getString("experience"));
			setHonor(rsn.getString("honor"));
			setNeedNum(rsn.getInt("neednum"));
			String need=rsn.getString("needs");
			String needarry[]=need.split(";");
			setGrade(needarry[0]);
			setExperienceS(needarry[1]);
			setAward(needarry[2]);
			setEnglishLevel(needarry[3]);
			setSexS(needarry[4]);
		}
		HttpServletRequest request = ServletActionContext.getRequest();
    	request.setAttribute("Id", getId());
		return "ToUpdate";
	}
	
	public String Update() throws Exception
	{
		
		DB mydb = new DB();
		String staca = "select * from department where academyid="+getAcademy();
		String  Need=getGrade()+";"+getExperienceS()+";"+getAward()+";"+getEnglishLevel()+";"+getSexS();
		setNeeds(Need);
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

	public String getEnglishLevel() {
		return EnglishLevel;
	}

	public void setEnglishLevel(String englishLevel) {
		EnglishLevel = englishLevel;
	}

	public String getGrade() {
		return Grade;
	}

	public void setGrade(String grade) {
		Grade = grade;
	}

	public String getAward() {
		return Award;
	}

	public void setAward(String award) {
		Award = award;
	}

	public String getSexS() {
		return SexS;
	}

	public void setSexS(String sexS) {
		SexS = sexS;
	}

	public String getExperienceS() {
		return ExperienceS;
	}

	public void setExperienceS(String experienceS) {
		ExperienceS = experienceS;
	}
}
