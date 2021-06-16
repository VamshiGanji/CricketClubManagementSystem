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
public class SetGroundDetails extends HttpServlet
{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
			try
			{
				String myDriver="org.gjt.mm.mysql.Driver";
				String myUrl="jdbc:mysql://localhost/hitmancricketclub";
				Class.forName(myDriver);
				Connection conn = DriverManager.getConnection(myUrl, "root", "");
				PrintWriter pw=res.getWriter();
				HttpSession session=req.getSession();
				String gid=(String) session.getAttribute("id");
				String gname=req.getParameter("groundname");
				String cap=req.getParameter("capacity");
				String loc=req.getParameter("location");
				String rents=req.getParameter("rent");
				res.setContentType("text/html");
				String query="UPDATE grounds SET GroundName=?,Capacity=?,Location=?,Rent=? WHERE GroundID=?";
				PreparedStatement st=conn.prepareStatement(query);
				st.setString(1,gname);
				st.setString(2,cap);
				st.setString(3,loc);
				st.setString(4,rents);
				st.setString(5,gid);
				int i=st.executeUpdate();
				if(i>0)
				{
					pw.println("<div height=70 width=1340><a href=adminaccount.html align=left><img src=cricketlogo.jpg align=left height=50 width=125></a>");
					pw.println("<ul align=right class=navbartopmenu>");
					pw.println("<a href=adminaccount.html>Home</a>");
					pw.println("<a href=changepassword.html>Change Password</a>");
					pw.println("<a href=adminlogin.html>logout</a>");
					pw.println("</div>");
					pw.println("<center><h4 style=color:green;>Ground Details Updated Sucessfully</h4></center>");
					pw.println("<center><h1 style= background-color:teal; color:#32a84e; height:50; width:1350;>Ground Details</h1></center>");
					pw.println("<div><center><table border=1>");
					pw.println("<tr><th>Ground ID:</th><td>"+gid+"</td></tr>");
					pw.println("<tr><th>Ground Name:</th><td>"+gname+"</td></tr>");
					pw.println("<tr><th>Capacity:</th><td>"+cap+"</td></tr>");
					pw.println("<tr><th>Location:</th><td>"+loc+"</td></tr>");
					pw.println("<tr><th>Rent:</th><td>"+rents+"</td></tr>");
					pw.println("</table></center></div>");
				}
				conn.close();
			}
			catch (Exception e)
			{
				System.err.println("Got an exception! ");
				System.err.println(e.getMessage());
			}
	}
}
