package cricketclub;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.sql.*;
public class ViewClubDetails extends HttpServlet
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
			String clubname=req.getParameter("clubname");
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT ClubName from clubmembers");
			while(rs.next())
			{
				String clubnam=rs.getString("ClubName");
				if(clubname.equals(clubnam))
				{
					PreparedStatement ps=conn.prepareStatement("SELECT * FROM clubmembers WHERE ClubName=?");
					ps.setString(1,clubnam);
					ResultSet resset=ps.executeQuery();
					res.setContentType("text/html");
					pw.println("<a href=clubdetails.html>go back</a>");
					pw.println("<center><h1 style=background:teal;>Club Members</h1>");	
					pw.println("<table border=1>");
					pw.println("<tr><th>Name</th><th>Age</th><th>Mobile</th><th>ClubName</th><th>Locality</th><th>City</th><th>Role</th><th>Previous Club</th></tr>");
					while(resset.next())
					{
						String name=resset.getString("Name");
						String age=resset.getString("Age");
						String mobile=resset.getString("Mobile");
						String clubnames=resset.getString("ClubName");
						String locality=resset.getString("Locality");
						String city=resset.getString("City");
						String role=resset.getString("Role");
						String preclub=resset.getString("PreviousClub");
						pw.println("<tr><td>"+name+"</td><td>"+age+"</td><td>"+mobile+"</td><td>"+clubnames+"</td><td>"+locality+"</td><td>"+city+"</td><td>"+role+"</td><td>"+preclub+"</td></tr>");
					}
					pw.println("</table></center>");
					conn.close();
				}
			}
			RequestDispatcher rd=req.getRequestDispatcher("/clubdetails1.html");
			rd.forward(req,res);
		}
		catch(Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
}

