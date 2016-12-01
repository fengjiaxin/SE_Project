package Data;

public class Teacher {
	private int Id;
	private String Name;
	private String Email;
	private String Telephone;
	private String Age;
	private String Sex;
	private String    ApplyStation;
	
	private String Academy;
	private String Research;
	private String Article;
	private String Experience;
	private String Honor;
	private int NeedNum;
	private String Needs;
	public void setApplyStation(String ApplyStation)
	{
		this.ApplyStation=ApplyStation;
	}
	public String getApplyStation()
	{
		return this.ApplyStation;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getAcademy() {
		return Academy;
	}
	public void setAcademy(String academy) {
		Academy = academy;
	}
	public String getResearch() {
		return Research;
	}
	public void setResearch(String research) {
		Research = research;
	}
	public String getArticle() {
		return Article;
	}
	public void setArticle(String article) {
		Article = article;
	}
	public String getExperience() {
		return Experience;
	}
	public void setExperience(String experience) {
		Experience = experience;
	}
	public int getNeedNum() {
		return NeedNum;
	}
	public void setNeedNum(int needNum) {
		NeedNum = needNum;
	}
	
	public String getHonor() {
		return Honor;
	}
	public void setHonor(String honor) {
		Honor = honor;
	}
	public String getNeeds() {
		return Needs;
	}
	public void setNeeds(String needs) {
		Needs = needs;
	}
}
