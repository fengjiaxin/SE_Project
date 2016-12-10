package Action;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;
import Data.Student;
import Data.Academy;
import Data.Major;
import Data.Research;
public class StudentPageAction extends Student{

	private String HI;//学生获奖统计，HI=国际级，HC=国家级，HP=省级，取值为‘1’或‘0’
	private String HC;
	private String HP;
	private String RemarkI;//对应上面三种获奖情况的备注
	private String RemarkC;
	private String RemarkP;
	private String Academy1;
	private String Research;
	private Map<Integer,List<Major>> Mymap;
	private Map<Integer,List<Research>> Mysearch;
	private List<Academy>Mylist;
	private List<Academy>Mylist1;
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
	
	public StudentPageAction() throws Exception
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
		String p = "select * from department"; 
		DB QL = new DB();
		ResultSet rs = QL.executeQuery(p);
		Mylist1 = new ArrayList<Academy>();
		Mysearch=new HashMap<Integer,List<Research>>();
		while(rs.next())
		{
			Academy am1 = new Academy();
			am1.setId(rs.getInt("academyid"));
			am1.setName(rs.getString("academyname"));
			Mylist1.add(am1);
			
			List<Research> researchlist=new ArrayList<Research>();
			String research[]=rs.getString("research").split(",");
			for(int j=0;j<research.length;j++)
			{   
				Research rh=new Research();
				rh.setId(j+1);
				rh.setName(research[j]);
				researchlist.add(rh);
			}
			Mysearch.put(am1.getId(),researchlist);
		}
		
	}
	
	public String Display() throws Exception
	{
		DB mydb = new DB();
		String s ="select * from student where id=" + getId();
		ResultSet r=mydb.executeQuery(s);
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
		
		s = "select * from studentlabel where id=" + getId();
		r = mydb.executeQuery(s);
		if(r.next())
		{
			setPoint(r.getString("point"));
			setAcademy(r.getString("academy"));
			setMajor(r.getString("major"));
			setInterest(r.getString("interest"));
			setExperience(r.getString("experience"));
			setHonor(r.getString("honor"));
		}
		HttpServletRequest request = ServletActionContext.getRequest();
    	request.setAttribute("Id", getId());
		return "Display";
	}
	
	public String ToUpdate() throws Exception
	{
		DB mydb = new DB();
		
		String s ="select * from student where id=" + getId();
		ResultSet r=mydb.executeQuery(s);
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
		
		HttpServletRequest request = ServletActionContext.getRequest();
    	request.setAttribute("Id", getId());
    	
    	DB myDB = new DB();
    	String ss="select* from studentlabel where id="+getId();
    	ResultSet rsn=myDB.executeQuery(ss);
    	if(rsn.next())
    	{
    		setPoint(rsn.getString("Point"));
    		String honorarray[]=rsn.getString("honor").split(";");
    		setRemarkI(honorarray[0]);
    		setRemarkC(honorarray[1]);
    		setRemarkP(honorarray[2]);
    		setExperience(rsn.getString("experience"));
    	}
    	
		return "ToUpdate";
	}
	
	public String Update() throws Exception
	{
		DB mydb = new DB();
		if(getHI().equals("0"))
		{
			setRemarkI("无");
		}
		if(getHC().equals("0"))
		{
			setRemarkI("无");
		}
		if(getHP().equals("0"))
		{
			setRemarkP("无");
		}
		String honor=getRemarkI()+";"+getRemarkC()+";"+getRemarkP();
		setHonor(honor);
		String staca = "select * from department where academyid="+getAcademy();
		String stacb="select* from department where academyid="+getAcademy1();
		String aca="";
		String Smajor="";
		String majors[];
		String Sresearch="";
		String researchs[];
		int maI=Integer.parseInt(getMajor());
		int Rch=Integer.parseInt(getResearch());
		ResultSet r= mydb.executeQuery(staca);
		if(r.next())
		{
			aca=r.getString("academyname");
			majors=r.getString("major").split(",");
			Smajor=majors[maI-1];
			
			researchs=r.getString("research").split(",");
			Sresearch=researchs[Rch-1];
		}
		DB mydbt = new DB();
		ResultSet rt= mydbt.executeQuery(stacb);
		if(rt.next())
		{
			aca=rt.getString("academyname");
			researchs=rt.getString("research").split(",");
			Sresearch=researchs[Rch-1];
		}
		setInterest(Sresearch);
		String s = "update studentlabel set point="+getPoint()+
											",academy="+"'"+aca+"'"+
											",major="+"'"+Smajor+"'"+
											",interest="+"'"+getInterest()+"'"+
											",experience="+"'"+getExperience()+"'"+
											",honor="+"'"+getHonor()+"'"+
											",EnglishLevel="+"'"+getEnglishLevel()+"'"+
											" where id="+getId()+";";
		System.out.println(s);
		mydb.executeUpdate(s);
		return "Update";
	}

	public Map<Integer,List<Research>> getMysearch() {
		return Mysearch;
	}

	public void setMysearch(Map<Integer,List<Research>> mysearch) {
		Mysearch = mysearch;
	}

	public List<Academy> getMylist1() {
		return Mylist1;
	}

	public void setMylist1(List<Academy> mylist1) {
		Mylist1 = mylist1;
	}

	public String getHI() {
		return HI;
	}

	public void setHI(String hI) {
		HI = hI;
	}

	public String getHC() {
		return HC;
	}

	public void setHC(String hC) {
		HC = hC;
	}

	public String getHP() {
		return HP;
	}

	public void setHP(String hP) {
		HP = hP;
	}

	public String getRemarkI() {
		return RemarkI;
	}

	public void setRemarkI(String remarkI) {
		RemarkI = remarkI;
	}

	public String getRemarkC() {
		return RemarkC;
	}

	public void setRemarkC(String remarkC) {
		RemarkC = remarkC;
	}

	public String getRemarkP() {
		return RemarkP;
	}

	public void setRemarkP(String remarkP) {
		RemarkP = remarkP;
	}

	public String getAcademy1() {
		return Academy1;
	}

	public void setAcademy1(String academy1) {
		Academy1 = academy1;
	}

	public String getResearch() {
		return Research;
	}

	public void setResearch(String research) {
		Research = research;
	}
}
