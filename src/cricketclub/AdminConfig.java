package cricketclub;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.sql.*;
public class AdminConfig extends HttpServlet
{
	 protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		try
		{
			String myDriver="org.gjt.mm.mysql.Driver";
			String myUrl="jdbc:mysql://localhost/hitmancricketclub";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "");
			PrintWriter pw=res.getWriter();
			String usrname=req.getParameter("username");
			String pwd=req.getParameter("password");
			Statement st= conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT UserName,Password from adminaccount");
			while(rs.next())
			{
				String username=rs.getString("UserName");
				String passwords=rs.getString("Password");
				if(usrname.equals(username) && pwd.equals(passwords))
				{
					RequestDispatcher rd=req.getRequestDispatcher("/adminaccount.html");
					rd.forward(req,res);
				}
					else
					{
						RequestDispatcher rd=req.getRequestDispatcher("/adminlogin1.html");
						rd.forward(req,res);
					}
					st.close();
			}
		}
		catch (Exception e)
		{
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}	
	
}
