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

	/*��ʦϵͳ�Ƽ�����
	 
	 1.��ȡ��ʦ��ѧԺ��needs
	 2.����list����¼ѧ����Ϣ��Ϊѧ�����
	 3.��list��������
	 4.�����������Ƽ�ѧ��������Ϊ����������
             ע1��������ʦȷ��ʦ����ϵ��ѧ�����ٱ��Ƽ�
            ע2��list{�������Ա�ѧԺ���ɼ�����ѧʵ����������������Ӣ��ɼ���}*/
	
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
	    	Academy = Rs.getString("academy");             //���1
	    	neednum = Rs.getInt("neednum");
	    }
	    List<StudentPoint>StudentList = new ArrayList<StudentPoint>();//����list
	    String Student = "select * from studentlabel where academy="+"'"+Academy+"'";
	    r = db.executeQuery(Student);
	    NeedsSplit = Needs.split(";");
	    
	    while(r.next())            //�ҵ���Ӧרҵ��ѧ��
	    {
		    int id = r.getInt("id");
		    String Student1 = "select * from student where id="+id; //����name,sex,status
		    ResultSet r_student = db1.executeQuery(Student1);
		    int status = 0;
		    String sex = "";
		    if(r_student.next())
		    {
		    	status = r_student.getInt("status");
		    	sex = r_student.getString("sex");
		    }
		    System.out.println("�ҵ���Ӧרҵ��ѧ��");
		    int point = 0;              //��¼ѧ���÷�
	    	if(status != 1)         //������δ�뵼ʦ���Э��
	    	{
	    		System.out.println("����status");
		    	StudentPoint findstudent = new StudentPoint();
		    	
		    	String name = r_student.getString("name");
		    	findstudent.setId(id);
		    	findstudent.setName(name);
		    	findstudent.setAcademy(Academy);
		    	for(int i = 0; i<NeedsSplit.length; i++)
		    	{
		    		System.out.println("����pointϵͳ");
		    		switch(i)
		    		{
		    		case 0:            //ѧ�ּ�
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
		    		case 1:             //��Ŀʵ������
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
		    		case 2:                //��
		    			String hon = r.getString("honor");
		    			String[] honlist = hon.split(";");
		    			String A = "���ʽ���";
		    			String B = "���Ҽ����";
		    			String C = "ʡ�����";
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
		    		case 3:                //Ӣ��ˮƽ
		    			String Eng = r.getString("EnglishLevel");
		    			String English;
		    			if(Eng.equals("Y"))
		    					English = "��˼�и�";
		    			else if(Eng.equals("L"))
		    					English = "����";
		    			else
		    					English = "�ļ�";
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
		    		case 4:             //�Ա�
		    			findstudent.setSex(sex);
		    			switch(NeedsSplit[4].charAt(0))
		    			{
		    			case 'B':
		    				if(sex.equals("��"))
		    					point += 3;
		    				break;
		    			case 'G':
		    				if(sex.equals("Ů"))
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
		      //����������
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
