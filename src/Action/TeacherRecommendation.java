package Action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBcon.DB;

import Data.StudentPoint;
import Data.Teacher;

public class TeacherRecommendation extends Teacher{

	/*导师系统推荐功能
	 
	 1.获取导师的学院，needs
	 2.创建list；记录学生信息，为学生打分
	 3.将list按分排序
	 4.按分数优先推荐学生（个数为招生数量）
             注1：已与老师确定师生关系的学生不再被推荐
            注2：list{姓名；性别；学院；成绩；大学实践经历；获奖荣誉；英语成绩；}*/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String excute() throws Exception
	{
		DB db=new DB();
		DB db1=new DB();
		int neednum = 1;
		String Needs = "";
		String[] NeedsSplit;
		String Academy = "";
		
		String s ="select * from teacher where id=" + getId();
		ResultSet r=db.executeQuery(s);
		if(r.next())
		{
			setName(r.getString("name"));
			setAge(r.getString("age"));
			setEmail(r.getString("email"));
			setSex(r.getString("sex"));
			setTelephone(r.getString("telephone"));
			setApplyStation(r.getString("ApplyStation"));
		}
		String Teacher = "select * from teacherlabel where id="+getId();
		ResultSet Rs=db.executeQuery(Teacher);
	    if(Rs.next())
		{
	    	Needs= Rs.getString("needs");
	    	Academy = Rs.getString("academy");             //完成1
	    	neednum = Rs.getInt("neednum");
	    }
	    List<StudentPoint>StudentList = new ArrayList<StudentPoint>();//创建list
	    String Student = "select * from studentlabel where academy="+"'"+Academy+"'";
	    r = db.executeQuery(Student);
	    NeedsSplit = Needs.split(";");
	    
	    while(r.next())            //找到对应专业的学生
	    {
		    int id = r.getInt("id");
		    String Student1 = "select * from student where id="+id; //包含name,sex,status
		    ResultSet r_student = db1.executeQuery(Student1);
		    int status = 0;
		    String sex = "";
		    if(r_student.next())
		    {
		    	status = r_student.getInt("status");
		    	sex = r_student.getString("sex");
		    }
		    System.out.println("找到对应专业的学生");
		    int point = 0;              //记录学生得分
	    	if(status != 1)         //该生还未与导师达成协议
	    	{
	    		System.out.println("进入status");
		    	StudentPoint findstudent = new StudentPoint();
		    	
		    	String name = r_student.getString("name");
		    	findstudent.setId(id);
		    	findstudent.setName(name);
		    	findstudent.setAcademy(Academy);
		    	for(int i = 0; i<NeedsSplit.length; i++)
		    	{
		    		System.out.println("进入point系统");
		    		switch(i)
		    		{
		    		case 0:            //学分绩
		    			int point1 = r.getInt("point");
		    			findstudent.setPoint(point1);
		    			switch(Integer.parseInt(NeedsSplit[0]))
		    			{
		    			case 90:
		    				if(point1 > 90)
		    					point += 5;
		    				break;
		    			case 80:
		    				if(point1 > 80)
		    					point += 4;
		    				break;
		    			case 70:
		    				if(point1 > 70)
		    					point += 3;
		    				break;
		    			case 60:
		    				if(point1 > 60)
		    					point += 2;
		    				break;
		    			}
		    			break;
		    		case 1:             //项目实践经历
		    			String exp = r.getString("experience");
		    			findstudent.setExperience(exp);
		    			switch(NeedsSplit[1].charAt(0))
		    			{
		    			case 'Y':
		    				if(!exp.matches("\\s*") || !(exp == null))
		    					point += 2;
		    				break;
		    					
		    			case 'N':
		    				break;
		    			}
		    			break;
		    		case 2:                //获奖
		    			String hon = r.getString("honor");
		    			String[] honlist = hon.split(";");
		    			String A = "国际奖：";
		    			String B = "国家级奖项：";
		    			String C = "省级奖项：";
                        String D = ";  ";
		    			String finish = A + honlist[0] + D + B + honlist[1] + D + C + honlist[2];
		    			
		    			findstudent.setHonor(finish);
		    			switch(NeedsSplit[2].charAt(0))
		    			{
		    			case 'I':
		    				if(!honlist[0].matches("\\s*"))
		    					point += 6;
		    				break;
		    			case 'C':
		    				if(!honlist[1].matches("\\s*"))
		    					point += 4;
		    				break;
		    			case 'P':
		    				if(!honlist[2].matches("\\s*"))
		    					point += 2;
		    				break;
		    			case 'N':
		    				point += 0;
		    				break;
		    			}
		    			break;
		    		case 3:                //英语水平
		    			String Eng = r.getString("EnglishLevel");
		    			String English;
		    			if(Eng.equals("Y"))
		    					English = "雅思托福";
		    			else if(Eng.equals("L"))
		    					English = "六级";
		    			else
		    					English = "四级";
		    			System.out.println(English);
		    			findstudent.setEnglish(English);
		    			switch(NeedsSplit[3].charAt(0))
		    			{
		    			case 'Y':
		    				if(Eng.equals("Y"))
		    					point += 5;
		    				break;
		    			case 'L':
		    				if(Eng.equals("L") || Eng.equals("Y"))
		    					point += 3;
		    				break;
		    			case 'N':
		    				point += 0;
		    				break;
		    			}
		    			break;
		    		case 4:             //性别
		    			findstudent.setSex(sex);
		    			switch(NeedsSplit[4].charAt(0))
		    			{
		    			case 'B':
		    				if(sex.equals("男"))
		    					point += 3;
		    				break;
		    			case 'G':
		    				if(sex.equals("女"))
		    					point += 3;
		    				break;
		    			case 'N':
		    				point += 0;
		    				break;
		    				
		    			}
		    			findstudent.setSystemPoint(point);
		    			System.out.println(point);
		    			break;		
		    		}	
		    	}
		    	StudentList.add(findstudent);
		    	System.out.println(findstudent);
	    	}
	    	System.out.println(StudentList);

	    }
		      //按分数排序
		 Collections.sort(StudentList, new Comparator() {
	          public int compare(Object a, Object b) {
	              int Point1 = ((StudentPoint) a).getPoint();
	              int Point2 = ((StudentPoint) b).getPoint();
	              return Point2 - Point1;
	          }
	      });
		 
		 List<StudentPoint>SendList = new ArrayList<StudentPoint>();
		 
		 for (int j=0;j<neednum && j<StudentList.size();j++)
		 {
			 SendList.add(StudentList.get(j));
		 }
		 
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("Id", getId());
		request.setAttribute("StudentList", SendList);
		System.out.println(SendList);
		return "Success";
	}
}
