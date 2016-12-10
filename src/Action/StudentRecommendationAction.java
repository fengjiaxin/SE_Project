package Action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;
import Data.RecTeacher;
import Data.Student;

public class StudentRecommendationAction extends Student{
	/*系统向学生推荐导师：
	 * 范围：学生所在学院导师
	 * 推荐标准：
	 * 1.该生感兴趣方向的导师;
	 * 2.招该生概率较大的导师;
	 * 推荐人数：最多为5(因为学生至多只能向3名导师申请)
	 * 
	 * */
	
	
	@SuppressWarnings("unchecked")
	public String excute() throws Exception
	{
		String studentAcademy = "";
		List<RecTeacher>TeacherList = new ArrayList<RecTeacher>();
		
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
		
		
		
		
		/*开始查询*/
		s = "select * from studentlabel where id="+getId();
		r = mydb.executeQuery(s);
		if(r.next())
		{
			studentAcademy = r.getString("academy");
			setAcademy(studentAcademy);
			setInterest(r.getString("interest"));
			setPoint(r.getString("point"));
			setExperience(r.getString("experience"));
			setHonor(r.getString("honor"));
			setEnglishLevel(r.getString("EnglishLevel"));
			
		}
		
		/*查询学院导师*/
		s = "select * from teacherlabel where academy='"+studentAcademy+"'";
		r = mydb.executeQuery(s);
		while(r.next())
		{
			int teaId = r.getInt("id");
			int teaStatus = 0;
			String s_find = "select * from teacher where id="+teaId;
			DB mydb2 = new DB();
			ResultSet r_stea = mydb2.executeQuery(s_find);
			if(r_stea.next())
			{
				teaStatus = r_stea.getInt("status");
			}
			
			if(teaStatus == 1)
			{
				continue;    //  导师名额已满
			}
			else
			{
			
				
				RecTeacher one = new RecTeacher();
				
				one.setId(teaId);
				one.setName(r_stea.getString("name"));
				one.setAge(r_stea.getString("age"));
				one.setSex(r_stea.getString("sex"));
				
				
				one.setAcademy(r.getString("academy"));
				one.setResearch(r.getString("research"));
				one.setNeeds(r.getString("needs"));
				
				judge(one);//打分
				
				TeacherList.add(one);
			}
		}
		
		//sort
		 Collections.sort(TeacherList, new Comparator() {
	            public int compare(Object a, Object b) {
	                int Point1 = ((RecTeacher) a).getSysPoint();
	                int Point2 = ((RecTeacher) b).getSysPoint();
	                return Point2 - Point1;
	            }
	        });
		
		 List<RecTeacher>SendList = new ArrayList<RecTeacher>();
		 
		 for (int j=0;j<5 && j<TeacherList.size();j++)
		 {
			 SendList.add(TeacherList.get(j));
		 }
		 
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("Id", getId());
		request.setAttribute("TeacherList", SendList);
		return "SystemRecommendation";
	}
	
	public void judge(RecTeacher one)
	{
		
		int point = 0;
		
		int needtotalpoint = 0;//老师需求总分
		
		int judgeneedpoint = 0;//根据老师需求评判的学生得分
		
		String ChineseNeeds = "";
		
		//导师研究方向与学生兴趣方向得分
		if(one.getResearch().equals(getInterest()))
		{
			point+=50;
		}
		
		String needs[] = one.getNeeds().split(";");
		
		//学分绩得分
		
		ChineseNeeds += "本科学分绩要求:";
		if(Integer.parseInt(needs[0]) == 90)
		{
			needtotalpoint +=20;
			if (Integer.parseInt(getPoint()) >= Integer.parseInt(needs[0]))
			{
				judgeneedpoint+=20;
			}
			ChineseNeeds += "90以上;";
		}
		else if(Integer.parseInt(needs[0]) == 80)
		{
			needtotalpoint +=15;
			if (Integer.parseInt(getPoint()) >= Integer.parseInt(needs[0]))
			{
				judgeneedpoint+=15;
			}
			ChineseNeeds += "80以上;";
		}
		else if(Integer.parseInt(needs[0]) == 70)
		{
			needtotalpoint +=10;
			if (Integer.parseInt(getPoint()) >= Integer.parseInt(needs[0]))
			{
				judgeneedpoint+=10;
			}
			ChineseNeeds += "70以上;";
		}
		else
		{
			needtotalpoint +=5;
			if (Integer.parseInt(getPoint()) >= Integer.parseInt(needs[0]))
			{
				judgeneedpoint+=5;
			}
			ChineseNeeds += "60以上;";
		}
		
		//experience
		
		if (needs[1].equals("Y"))
		{
			needtotalpoint +=12;
			if(!getExperience().matches("\\s*"))
			{
				judgeneedpoint += 12;
			}
			ChineseNeeds +="最好有项目开发经历;";
		}
		
		
		//honor
		String honors[]=getHonor().split(";");
		System.out.println(getHonor());
		System.out.println(honors.length);
		if(needs[2].equals("I"))
		{
			needtotalpoint += 16;
			
			if(!honors[0].equals("无"))
			{
				judgeneedpoint += 16;
			}
			ChineseNeeds +="获得过国际级奖项者优先;";
		}
		else if(needs[2].equals("C"))
		{
			needtotalpoint += 12;
			if(!honors[0].equals("无") || !honors[1].equals("无"))
			{
				judgeneedpoint += 12;
			}
			ChineseNeeds +="获得过国家级及以上奖项者优先;";
		}
		else if(needs[2].equals("P"))
		{
			needtotalpoint += 8;
			if(!honors[0].equals("无") || !honors[1].equals("无") ||!honors[2].equals("无"))
			{
				judgeneedpoint += 8;
			}
			ChineseNeeds +="获得过省级及以上奖项者优先;";
		}
		
		
		//englishlevel
		ChineseNeeds +="英语水平要求:";
		if (needs[3].equals("Y"))
		{
			needtotalpoint += 12;
			if(getEnglishLevel().equals("Y"))
			{
				judgeneedpoint += 12;
			}
			ChineseNeeds +="托福90+或雅思6.0+;";
			
		}
		else if(needs[3].equals("L"))
		{
			needtotalpoint += 6;
			if(getEnglishLevel().equals("Y") || getEnglishLevel().equals("L"))
			{
				judgeneedpoint += 6;
			}
			ChineseNeeds +="过6级;";
		}
		else
		{
			ChineseNeeds +="过4级;";
		}
		
		//sex
		if(needs[4].equals("B"))
		{
			needtotalpoint += 8;
			if(getSex().equals("男"))
			{
				judgeneedpoint += 8;
			}
			ChineseNeeds+="男士优先;";
		}
		else if(needs[4].equals("G"))
		{
			needtotalpoint += 8;
			if(getSex().equals("女"))
			{
				judgeneedpoint += 8;
			}
			ChineseNeeds += "女士优先;";
		}
		one.setNeeds(ChineseNeeds);
		//该导师总得分
		point += 50 * judgeneedpoint / needtotalpoint;
		one.setSysPoint(point);
	}
}
