package peeru_CSCI201_Assignment3;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/validate")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("username");
		String password = request.getParameter("password");
		if (isValid(email, password)) {
			response.sendRedirect("index.html?loggedIn=true&user=" + email);

		} else {
			response.sendRedirect("login.html?error=invalid");
		}

	}

	boolean isValid(String email, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			connection = ConnectionUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM UserTable WHERE username = ? AND password = ?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				try {
					if (resultSet != null)
						resultSet.close();
					if (preparedStatement != null)
						preparedStatement.close();
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return true;
			} else {
				try {
					if (resultSet != null)
						resultSet.close();
					if (preparedStatement != null)
						preparedStatement.close();
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return false;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
