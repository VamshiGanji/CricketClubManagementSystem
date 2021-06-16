package cricketclub;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class ViewGroundBookings2 extends HttpServlet 
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
				String date=req.getParameter("date");
				String month=req.getParameter("month");
				Statement st=conn.createStatement();
				ResultSet rs=st.executeQuery("SELECT Date,Month FROM groundbookings");
				while(rs.next())
				{
					String dates=rs.getString("Date");
					String months=rs.getString("Month");
					if(date.equals(dates) && month.equals(months))
					{
						PreparedStatement ps=conn.prepareStatement("SELECT * FROM groundbookings WHERE Date=? and Month=?");
						ps.setString(1,dates);
						ps.setString(2,month);
						ResultSet resset=ps.executeQuery();
						res.setContentType("text/html");
						pw.println("<a href=viewbooking.html>go back</a>");
						pw.println("<center><h1 style=background:teal;>Ground Details</h1>");	
						pw.println("<table border=1>");
						pw.println("<tr><th>Name</th><th>Mobile</th><th>Email</th><th>Venue</th><th>TimeSlot</th><th>Purpose</th><th>Date</th><th>Month</th></tr>");
						while(resset.next())
						{
							String name=resset.getString("Name");
							String mobile=resset.getString("Mobile");
							String email=resset.getString("Email");
							String venue=resset.getString("Venue");
							String timeslot=resset.getString("TimeSlot");
							String purpose=resset.getString("Purpose");
							String datees=resset.getString("Date");
							String monthhs=resset.getString("Month");
							pw.println("<tr><th>"+name+"</th><th>"+mobile+"</th><th>"+email+"</th><th>"+venue+"</th><th>"+timeslot+"</th><th>"+purpose+"</th><th>"+datees+"</th><th>"+monthhs+"</th></tr>");
						}
						pw.println("</table></center>");					
						conn.close();		
					}
				}	
				RequestDispatcher rd=req.getRequestDispatcher("/viewbooking1.html");
				rd.forward(req,res);
		}
		catch(Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}	
	}
}
