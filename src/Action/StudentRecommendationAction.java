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
	/*ϵͳ��ѧ���Ƽ���ʦ��
	 * ��Χ��ѧ������ѧԺ��ʦ
	 * �Ƽ���׼��
	 * 1.��������Ȥ����ĵ�ʦ;
	 * 2.�и������ʽϴ�ĵ�ʦ;
	 * �Ƽ����������Ϊ5(��Ϊѧ������ֻ����3����ʦ����)
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
		
		/*��������Ϣ����*/
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
		
		
		
		
		/*��ʼ��ѯ*/
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
		
		/*��ѯѧԺ��ʦ*/
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
				continue;    //  ��ʦ��������
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
				
				judge(one);//���
				
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
		
		int needtotalpoint = 0;//��ʦ�����ܷ�
		
		int judgeneedpoint = 0;//������ʦ�������е�ѧ���÷�
		
		String ChineseNeeds = "";
		
		//��ʦ�о�������ѧ����Ȥ����÷�
		if(one.getResearch().equals(getInterest()))
		{
			point+=50;
		}
		
		String needs[] = one.getNeeds().split(";");
		
		//ѧ�ּ��÷�
		
		ChineseNeeds += "����ѧ�ּ�Ҫ��:";
		if(Integer.parseInt(needs[0]) == 90)
		{
			needtotalpoint +=20;
			if (Integer.parseInt(getPoint()) >= Integer.parseInt(needs[0]))
			{
				judgeneedpoint+=20;
			}
			ChineseNeeds += "90����;";
		}
		else if(Integer.parseInt(needs[0]) == 80)
		{
			needtotalpoint +=15;
			if (Integer.parseInt(getPoint()) >= Integer.parseInt(needs[0]))
			{
				judgeneedpoint+=15;
			}
			ChineseNeeds += "80����;";
		}
		else if(Integer.parseInt(needs[0]) == 70)
		{
			needtotalpoint +=10;
			if (Integer.parseInt(getPoint()) >= Integer.parseInt(needs[0]))
			{
				judgeneedpoint+=10;
			}
			ChineseNeeds += "70����;";
		}
		else
		{
			needtotalpoint +=5;
			if (Integer.parseInt(getPoint()) >= Integer.parseInt(needs[0]))
			{
				judgeneedpoint+=5;
			}
			ChineseNeeds += "60����;";
		}
		
		//experience
		
		if (needs[1].equals("Y"))
		{
			needtotalpoint +=12;
			if(!getExperience().matches("\\s*"))
			{
				judgeneedpoint += 12;
			}
			ChineseNeeds +="�������Ŀ��������;";
		}
		
		
		//honor
		String honors[]=getHonor().split(";");
		System.out.println(getHonor());
		System.out.println(honors.length);
		if(needs[2].equals("I"))
		{
			needtotalpoint += 16;
			
			if(!honors[0].equals("��"))
			{
				judgeneedpoint += 16;
			}
			ChineseNeeds +="��ù����ʼ�����������;";
		}
		else if(needs[2].equals("C"))
		{
			needtotalpoint += 12;
			if(!honors[0].equals("��") || !honors[1].equals("��"))
			{
				judgeneedpoint += 12;
			}
			ChineseNeeds +="��ù����Ҽ������Ͻ���������;";
		}
		else if(needs[2].equals("P"))
		{
			needtotalpoint += 8;
			if(!honors[0].equals("��") || !honors[1].equals("��") ||!honors[2].equals("��"))
			{
				judgeneedpoint += 8;
			}
			ChineseNeeds +="��ù�ʡ�������Ͻ���������;";
		}
		
		
		//englishlevel
		ChineseNeeds +="Ӣ��ˮƽҪ��:";
		if (needs[3].equals("Y"))
		{
			needtotalpoint += 12;
			if(getEnglishLevel().equals("Y"))
			{
				judgeneedpoint += 12;
			}
			ChineseNeeds +="�и�90+����˼6.0+;";
			
		}
		else if(needs[3].equals("L"))
		{
			needtotalpoint += 6;
			if(getEnglishLevel().equals("Y") || getEnglishLevel().equals("L"))
			{
				judgeneedpoint += 6;
			}
			ChineseNeeds +="��6��;";
		}
		else
		{
			ChineseNeeds +="��4��;";
		}
		
		//sex
		if(needs[4].equals("B"))
		{
			needtotalpoint += 8;
			if(getSex().equals("��"))
			{
				judgeneedpoint += 8;
			}
			ChineseNeeds+="��ʿ����;";
		}
		else if(needs[4].equals("G"))
		{
			needtotalpoint += 8;
			if(getSex().equals("Ů"))
			{
				judgeneedpoint += 8;
			}
			ChineseNeeds += "Ůʿ����;";
		}
		one.setNeeds(ChineseNeeds);
		//�õ�ʦ�ܵ÷�
		point += 50 * judgeneedpoint / needtotalpoint;
		one.setSysPoint(point);
	}
}
