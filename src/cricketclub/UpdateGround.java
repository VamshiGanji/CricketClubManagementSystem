package cricketclub;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;
import java.sql.*;
public class UpdateGround extends HttpServlet{

			protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
			{
					try
					{
						String myDriver="org.gjt.mm.mysql.Driver";
						String myUrl="jdbc:mysql://localhost/hitmancricketclub";
						Class.forName(myDriver);
						Connection conn = DriverManager.getConnection(myUrl, "root", "");
						PrintWriter pw=res.getWriter();
						String groundid=req.getParameter("gid");
						Statement ps=conn.createStatement();
						ResultSet rs=ps.executeQuery("SELECT GroundID from grounds");
						while(rs.next())
						{
							String gid=rs.getString("GroundID");
							if(groundid.equals(gid))
							{
								HttpSession session=req.getSession();
								session.setAttribute("id",groundid);
								pw.println("<html><head><title>update ground</title></head>");
								pw.println("<body>");
								pw.println("<div height=70 width=1340><a href=adminaccount.html align=left><img src=cricketlogo.jpg align=left height=50 width=125></a>");
								pw.println("<ul align=right class=navbartopmenu>");
								pw.println("<a href=adminaccount.html>Home</a>");
								pw.println("<a href=changepassword.html>Change Password</a>");
								pw.println("<a href=adminlogin.html>logout</a>");
								pw.println("</div>");
								pw.println("<center><h1 style= background-color:teal; color:#32a84e; height:50; width:1350;>Update Ground Details</h1></center>");
								pw.println("<center><fieldset><legend><h1>Enter Ground Details</h1></legend><table>");
								pw.println("<form action=setgrounddetails method=post>");
								pw.println("<tr><th>Ground ID:</th><td>"+groundid+"</td></tr>");
								pw.println("<tr><th>Ground Name:</th><td><input type=text name=groundname></td></tr>");
								pw.println("<tr><th>Capacity:</th><td><input type=text name=capacity ></td></tr>");
								pw.println("<tr><th>Location:</th><td><input type=text name=location></td></tr>");
								pw.println("<tr><th>Rent:</th><td><input type=text name=rent></td></tr>");
								pw.println("<tr><th><input type=submit value=update></th><th><input type=reset value=cancel></th></tr>");
								pw.println("</form></table></fieldset></center>");
								pw.println("</body>");
								pw.println("</html>");
								conn.close();
							}
						}
						RequestDispatcher rd=req.getRequestDispatcher("/updateground1.html");
						rd.forward(req,res);
						conn.close();
					}
					catch (Exception e)
					{
						System.err.println("Got an exception! ");
						System.err.println(e.getMessage());
					}
			}
}
