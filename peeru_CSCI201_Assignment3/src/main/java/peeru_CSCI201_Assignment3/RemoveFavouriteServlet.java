package peeru_CSCI201_Assignment3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/removeF")
public class RemoveFavouriteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String email=request.getParameter("username");
		String artistID=request.getParameter("artistID");
		try {
			RemoveFavourite rF=new RemoveFavourite();
			boolean removed=rF.removeFavourite(email, artistID);
			PrintWriter out=response.getWriter();
			out.write(Boolean.toString(removed));
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
