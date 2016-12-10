package Data;

public class StudentPoint {

	private int Id;
	private String Name;
	private String Sex;
	private String Academy;
	private int Point;
	private String Experience;
	private String Honor;
	private String English;
	private int SystemPoint;
	
	public void setId(int Id)
	{
		this.Id = Id;
	}
	public int getId()
	{
		return this.Id;
	}
	
 	public void setName(String Name)
	{
		this.Name = Name;
	}
	public String getName()
	{
		return this.Name;
	}
	
	public void setSex(String Sex)
	{
		this.Sex = Sex;
	}
	public String getSex()
	{
		return this.Sex;
	}
	
	public void setAcademy(String Academy)
	{
		this.Academy = Academy;
	}
	public String getAcademy()
	{
		return this.Academy;
	}
	
	public void setPoint(int Point)
	{
		this.Point = Point;
	}
	public int getPoint()
	{
		return this.Point;
	}
	
	public String getEnglish() {
		return English;
	}
	public void setEnglish(String english) {
		English = english;
	}
	
	public int getSystemPoint() {
		return SystemPoint;
	}
	public void setSystemPoint(int point2) {
		SystemPoint = point2;
	}

	public String getExperience() {
		return Experience;
	}
	public void setExperience(String experience) {
		Experience = experience;
	}
	
	public String getHonor() {
		return Honor;
	}
	public void setHonor(String honor) {
		Honor = honor;
	}
	
	
}
