package peeru_CSCI201_Assignment3;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/validate2")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			if (saveUser(fullname, email, password)) {
				response.sendRedirect("index.html?loggedIn=true&user=" + email);
			} else {
				response.sendRedirect("register.html?error=invalid");
			}
		} catch (SQLException ex) {
			if (ex.getMessage().equalsIgnoreCase("User already exists")) {
				response.sendRedirect("register.html?error=invalid");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private boolean checkIfUserExists(Connection conn, String email) throws SQLException {
		String checkUserExistsSql = "select * from UserTable where username ='" + email + "'";
		System.out.println(checkUserExistsSql);
		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery(checkUserExistsSql);
		if (rs.next()) {
			return true;
		}
		return false;
	}

	private boolean insertRecords(Connection connection, String fullname, String email, String password)
			throws SQLException {

		String sql = "INSERT INTO UserTable (fname, username, password) VALUES (?, ?, ?)";
		int update = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		try {
			preparedStatement.setString(1, fullname);

			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);

			update = preparedStatement.executeUpdate();

		} finally {
			preparedStatement.close();
		}
		if (update > 0) {
			return true;
		}
		return false;

	}

	private boolean saveUser(String fullname, String email, String password)
			throws SQLException, ClassNotFoundException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			if (null != conn) {
				if (checkIfUserExists(conn, email)) {
					return false;
				}
				return insertRecords(conn, fullname, email, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return false;
	}
}
