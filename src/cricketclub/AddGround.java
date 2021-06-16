package cricketclub;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;
import java.sql.*;
public class AddGround extends HttpServlet
{
		protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
		{
				try
				{
					String myDriver="org.gjt.mm.mysql.Driver";
					String myUrl="jdbc:mysql:3306//localhost/hitmancricketclub";
					Class.forName(myDriver);
					Connection conn = DriverManager.getConnection(myUrl, "root", "");
					PrintWriter pw=res.getWriter();
					String groundid=req.getParameter("gid");
					String gnames=req.getParameter("gname");
					String capacity=req.getParameter("capacity");
					String loc=req.getParameter("loc");
					String rent=req.getParameter("rent");
					Statement ps=conn.createStatement();
					ResultSet rs=ps.executeQuery("SELECT GroundID,GroundName from grounds");
					while(rs.next())
					{
						String gid=rs.getString("GroundID");
						String gname=rs.getString("GroundName");
						if( groundid.equals(gid) && gnames.equals(gname))
						{
							RequestDispatcher rd=req.getRequestDispatcher("/addground1.html");
							rd.forward(req,res);
						}
					}
					res.setContentType("text/html");
					String query="INSERT into Grounds(GroundID,GroundName,Capacity,Location,Rent) "
					+ "VALUES(?,?,?,?,?)";
					PreparedStatement st=conn.prepareStatement(query);
					st.setString(1,groundid);
					st.setString(2,gnames);
					st.setString(3,capacity);
					st.setString(4,loc);
					st.setString(5,rent);
					int i=st.executeUpdate();
					if(i>0)
					{	
						pw.println("<div height=70 width=1340><a href=adminaccount.html align=left><img src=cricketlogo.jpg align=left height=50 width=125></a>");
						pw.println("<ul align=right class=navbartopmenu>");
						pw.println("<a href=adminaccount.html>Home</a>");
						pw.println("<a href=changepassword.html>Change Password</a>");
						pw.println("<a href=adminlogin.html>logout</a>");
						pw.println("</div>");
						pw.println("<center><h4 style=color:green;>New Ground Added Sucessfull</h4></center>");
						pw.println("<center><h1 style= background-color:teal; color:#32a84e; height:50; width:1350;>Ground Details</h1></center>");
						pw.println("<div><center><table border=1>");
						pw.println("<tr><th>Ground ID:</th><td>"+groundid+"</td></tr>");
						pw.println("<tr><th>Ground Name:</th><td>"+gnames+"</td></tr>");
						pw.println("<tr><th>Capacity:</th><td>"+capacity+"</td></tr>");
						pw.println("<tr><th>Location:</th><td>"+loc+"</td></tr>");
						pw.println("<tr><th>Rent:</th><td>"+rent+"</td></tr>");
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
