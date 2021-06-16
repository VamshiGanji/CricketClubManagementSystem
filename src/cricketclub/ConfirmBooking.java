



package cricketclub;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;
import java.sql.*;
public class ConfirmBooking extends HttpServlet
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
				String names=req.getParameter("name");
				String mobileno=req.getParameter("mbno");
				String email=req.getParameter("usremail");
				String venues=req.getParameter("venue");
				String timslt=req.getParameter("ts");
				String purposes=req.getParameter("purpose"); 
				String dates=req.getParameter("date");
				String months=req.getParameter("month");
				Statement ps=conn.createStatement();
				ResultSet rs=ps.executeQuery("SELECT Venue,TimeSlot,Date,Month from groundbookings");
				while(rs.next())
				{
					String veenue=rs.getString("Venue");
					String timeslots=rs.getString("TimeSlot");
					String datee=rs.getString("Date");
					String monthh=rs.getString("Month");
					if( venues.equals(veenue) && timslt.equals(timeslots) && dates.equals(datee) && months.equals(monthh))
					{
						String page=venues+"booking.html";
						RequestDispatcher rd=req.getRequestDispatcher(page);
						rd.forward(req,res);
					}
				}
				res.setContentType("text/html");
				String query="INSERT into groundbookings(Name,Mobile,Email,Venue,TimeSlot,Purpose,Date,Month) "
						+ "VALUES(?,?,?,?,?,?,?,?)";
				PreparedStatement st=conn.prepareStatement(query);
				st.setString(1,names);
				st.setString(2,mobileno);
				st.setString(3,email);
				st.setString(4,venues);
				st.setString(5,timslt);
				st.setString(6,purposes);
				st.setString(7,dates);
				st.setString(8,months);
				int i=st.executeUpdate();
				if(i>0)
				{
					pw.println("<div height=50 width=1340><a href=home.html align=left><img src=cricketlogo.jpg align=left height=50 width=125></a>");
					pw.println("<ul align=right class=navbartopmenu>");
					pw.println("<a href=groundbooking.html>Ground Booking</a>");
					pw.println("<a href=clubregistration.html>Club</a>");
					pw.println("<a href=batchregistration1.html>Batches</a>");
					pw.println("<a href=adminlogin.html>Admin</a>");
					pw.println("<a href=>About</a></ul>");
					pw.println("</div>");
					pw.println("<center><h4 style=color:green;>Ground Booking Sucessfull</h4></center>");
					pw.println("<center><h1 style= background-color:teal; color: #ABF60C; height:50; width:1350;>Ground Booking Details</h1></center>");
					pw.println("<div><center><table>");
					pw.println("<tr><th>Name:</th><td>"+names+"</td></tr>");
					pw.println("<tr><th>Contact:</th><td>"+mobileno+"</td></tr>");
					pw.println("<tr><th>Email:</th><td>"+email+"</td></tr>");
					pw.println("<tr><th>Venue:</th><td>"+venues+"</td></tr>");
					pw.println("<tr><th>TimeSlot:</th><td>"+timslt+"</td></tr>");
					pw.println("<tr><th>Purpose:</th><td>"+purposes+"</td></tr>");
					pw.println("<tr><th>Date:</th><td>"+dates+"/"+months+"/2020</td></tr>");
					pw.println("</table></center></div>");
					pw.println("<div background-color=black><center><p style=color:white>©2020 | All right reserved by Ganji Vamshi</p></center></div>");
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
