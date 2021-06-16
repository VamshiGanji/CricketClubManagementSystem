package cricketclub;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class BatchesDetails extends HttpServlet
{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		try
		{
			String myDriver="org.gjt.mm.mysql.Driver";
			String myUrl="jdbc:mysql://localhost/hitmancricketclub";
			Class.forName(myDriver);
			Connection conn=DriverManager.getConnection(myUrl,"root","");
			PrintWriter pw=res.getWriter();
			String city=req.getParameter("city");
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT City from batchmembers");
			while(rs.next())
			{
				String cities=rs.getString("City");
				if(city.equals(cities))
				{
					PreparedStatement ps=conn.prepareStatement("SELECT * FROM batchmembers WHERE City=?");
					ps.setString(1,cities);
					ResultSet resset=ps.executeQuery();
					res.setContentType("text/html");
					pw.println("<div><a href=home.html align=left><img src=cricketlogo.jpg align=left height=50 width=125></a>");
					pw.println("<ul align=right class=navbartopmenu>");
					pw.println("<a href=groundbooking.html>Ground Booking</a>");
					pw.println("<a href=clubregistration.html>Club</a>");
					pw.println("<a href=batchregistration1.html>Batches</a>");
					pw.println("<a href=adminlogin.html>Admin</a>");
					pw.println("<a href=>About</a></ul>");
					pw.println("</div>");
					pw.println("<center><h1 style=background:teal;>Batch Members</h1>");
					pw.println("<table border=1>");
					pw.println("<tr><th>Name</th><th>Age</th><th>Mobile</th><th>ClubName</th><th>Locality</th><th>City</th><th>Role</th><th>Previous Club</th></tr>");
					while(resset.next())
					{
						String name=resset.getString("Name");
						String age=resset.getString("Age");
						String mobile=resset.getString("Mobile");
						String locality=resset.getString("Locality");
						String citys=resset.getString("City");
						String battim=resset.getString("BatchTimings");
						String role=resset.getString("Role");
						String email=resset.getString("Email");
						pw.println("<tr><td>"+name+"</td><td>"+age+"</td><td>"+mobile+"</td><td>"+locality+"</td><td>"+citys+"</td><td>"+battim+"</td><td>"+role+"</td><td>"+email+"</td></tr>");
					}
					pw.println("</table></center>");
					conn.close();
				}
			}
			RequestDispatcher rd=req.getRequestDispatcher("/batches1.html");
			rd.forward(req,res);
		}
		catch(Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
}
