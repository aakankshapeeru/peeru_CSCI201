package peeru_CSCI201_Assignment3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/getF")
public class GetFavouriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String email=request.getParameter("username");
		String artistID=request.getParameter("artistID");
		try {
			GetFavourite getF=new GetFavourite();
			String[] artistList=getF.getFavourite(email,artistID);
			PrintWriter out=response.getWriter();
			Gson gson=new Gson();
			String jsonList=gson.toJson(artistList);
			response.setContentType("application/json");
			System.out.println(jsonList);
			out.write(jsonList);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
