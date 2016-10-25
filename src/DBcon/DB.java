package DBcon;
import java.sql.*;
public class DB {
	Connection con =null;
	Statement stat=null;
	ResultSet rs=null;
	
	public DB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");//��������
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/app_commombookdb","root","");//�������ݿ�
            stat=con.createStatement();
		}
		
		catch(Exception e)
		{
			con=null;
		}
	
	}
	
	public ResultSet executeQuery(String sql)
	{
		try
		{
			
			rs=stat.executeQuery(sql);
		}
		
		catch(Exception e)
		{
			rs=null;
		}
		return rs;
	}
	
	public int executeUpdate(String sql)
	{
		try
		{
			stat.executeUpdate(sql);
			return 0;
		}
		catch(Exception e)
		{
			return -1;
		}
	}
}



	
