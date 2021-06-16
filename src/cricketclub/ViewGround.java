package cricketclub;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import java.sql.*;
public class ViewGround extends HttpServlet
{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		try
		{
				String myDriver="org.gjt.mm.mysql.Driver";
				String myUrl="jdbc:mysql://localhost/hitmancricketclub";
				Class.forName(myDriver);
				Connection conn=DriverManager.getConnection(myUrl,"root","");
				PrintWriter pw=res.getWriter();
				String loca=req.getParameter("loc");
				Statement st=conn.createStatement();
				ResultSet rs=st.executeQuery("SELECT Location FROM grounds");
				while(rs.next())
				{
					String loc=rs.getString("Location");
					if(loca.equals(loc))
					{
						PreparedStatement ps=conn.prepareStatement("SELECT * FROM grounds WHERE Location=?");
						ps.setString(1,loca);
						ResultSet resset=ps.executeQuery();
						while(resset.next())
						{
							String gid=resset.getString("GroundID");
							String gname=resset.getString("GroundName");
							String cap=resset.getString("Capacity");
							String location=resset.getString("Location");
							String rent=resset.getString("Rent");
							res.setContentType("text/html");
							pw.println("<div height=70 width=1340><a href=adminaccount.html align=left><img src=cricketlogo.jpg align=left height=50 width=125></a>");
							pw.println("<ul align=right class=navbartopmenu>");
							pw.println("<a href=adminaccount.html>Home</a>");
							pw.println("<a href=changepassword.html>Change Password</a>");
							pw.println("<a href=adminlogin.html>logout</a>");
							pw.println("</div>");
							pw.println("<center><h1 style=background:teal;>Ground Details</h1>");	
							pw.println("<table border=1>");
							pw.println("<tr><th>Ground ID</th><th>Ground Name</th><th>Capacity</th><th>Location</th><th>Rent</th></tr>");
							pw.println("<tr><th>"+gid+"</th><th>"+gname+"</th><th>"+cap+"</th><th>"+location+"</th><th>"+rent+"</th></tr>");
							pw.println("</table></center>");
						}
						conn.close();}
				}	
				RequestDispatcher rd=req.getRequestDispatcher("/allgrounds1.html");
				rd.forward(req,res);	
		}
		catch(Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}	
	}
}
