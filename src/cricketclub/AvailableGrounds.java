package cricketclub;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class AvailableGrounds extends HttpServlet
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
						PreparedStatement ps=conn.prepareStatement("SELECT GroundName,Capacity,Location,Rent FROM grounds WHERE Location=?");
						ps.setString(1,loca);
						ResultSet resset=ps.executeQuery();
						while(resset.next())
						{
							String gname=resset.getString("GroundName");
							String cap=resset.getString("Capacity");
							String location=resset.getString("Location");
							String rent=resset.getString("Rent");
							res.setContentType("text/html");
							pw.println("<div height=50 width=1340><a href=home.html align=left><img src=cricketlogo.jpg align=left height=50 width=125></a>");
							pw.println("<ul align=right class=navbartopmenu>");
							pw.println("<a href=groundbooking.html>Ground Booking</a>");
							pw.println("<a href=clubregistration.html>Club</a>");
							pw.println("<a href=batchregistration1.html>Batches</a>");
							pw.println("<a href=adminlogin.html>Admin</a>");
							pw.println("<a href=>About</a></ul>");
							pw.println("</div>");
							pw.println("<center><h1 style=background:teal;>Ground Details</h1>");	
							pw.println("<table border=1>");
							pw.println("<tr><th>Ground Name</th><th>Capacity</th><th>Location</th><th>Rent</th></tr>");
							pw.println("<tr><td>"+gname+"</td><td>"+cap+"</td><td>"+location+"</td><td>"+rent+"</td></tr>");
							pw.println("</table></center>");
						}
						conn.close();}
				}	
				RequestDispatcher rd=req.getRequestDispatcher("/grounds1.html");
				rd.forward(req,res);	
		}
		catch(Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
}
