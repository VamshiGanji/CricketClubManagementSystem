package cricketclub;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class ChangePassword extends HttpServlet
{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		try
		{
			String myDriver="org.gjt.mm.mysql.Driver";
			String myUrl="jdbc:mysql://localhost/hitmancricketclub";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl,"root","");
			PrintWriter pw=res.getWriter();
			String aid=req.getParameter("aid");
			String pass=req.getParameter("cpwd");
			String npass=req.getParameter("npwd");
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT AdminID,Password from adminaccount");
			while(rs.next())
			{	
				String adminid=rs.getString("AdminID");
				String pwd=rs.getString("Password");
				if(aid.equals(adminid) && pass.equals(pwd))
				{
					PreparedStatement ps=conn.prepareStatement("UPDATE adminaccount SET Password=? WHERE AdminID=?");
					ps.setString(1,npass);
					ps.setString(2,adminid);
					int i=ps.executeUpdate();
					while(i>0)
					{
						RequestDispatcher rd=req.getRequestDispatcher("/changepassword2.html");
						rd.forward(req,res);
					}
				}
			}
			RequestDispatcher reqdis=req.getRequestDispatcher("/changepassword1.html");
			reqdis.forward(req,res);
		}
		catch(Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
}
