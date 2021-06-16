package cricketclub;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;
import java.sql.*;
public class ConfirmBatchReg extends HttpServlet
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
				String age=req.getParameter("age");
				String mobileno=req.getParameter("mbno");
				String locality=req.getParameter("loc");
				String city=req.getParameter("city"); 
				String batchtime=req.getParameter("bt");
				String role=req.getParameter("rol");
				String email=req.getParameter("eMail");
				res.setContentType("text/html");
				String query="INSERT into batchmembers(Name,Age,Mobile,Locality,City,BatchTimings,Role,Email) "
				+ "VALUES(?,?,?,?,?,?,?,?)";
				PreparedStatement st=conn.prepareStatement(query);
				st.setString(1,names);
				st.setString(2,age);
				st.setString(3,mobileno);
				st.setString(4,locality);
				st.setString(5,city);
				st.setString(6,batchtime);
				st.setString(7,role);
				st.setString(8,email);
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
					pw.println("<center><h4 style=color:green;>Batch Registration Sucessfull</h4></center>");
					pw.println("<center><h1 style= background-color:teal; color:#32a84e; height:50; width:1350;>Ground Booking Details</h1></center>");
					pw.println("<div><center><table>");
					pw.println("<tr><th>Name:</th><td>"+names+"</td></tr>");
					pw.println("<tr><th>Age:</th><td>"+age+"</td></tr>");
					pw.println("<tr><th>Contact:</th><td>"+mobileno+"</td></tr>");
					pw.println("<tr><th>Locality:</th><td>"+locality+"</td></tr>");
					pw.println("<tr><th>City:</th><td>"+city+"</td></tr>");
					pw.println("<tr><th>Batch Timings:</th><td>"+batchtime+"</td></tr>");
					pw.println("<tr><th>Club Name:</th><td>"+role+"</td></tr>");
					pw.println("<tr><th>Email:</th><td>"+email+"</td></tr>");
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
