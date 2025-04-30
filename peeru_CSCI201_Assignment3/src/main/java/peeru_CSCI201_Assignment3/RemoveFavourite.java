package peeru_CSCI201_Assignment3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveFavourite {
	public boolean removeFavourite(String email, String artistID) throws ClassNotFoundException, SQLException {
		Connection connection=ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM FavouriteArtists WHERE FID = ? AND username = ?");
		preparedStatement.setString(1, artistID);
		preparedStatement.setString(2, email);
		int update=preparedStatement.executeUpdate();
		if (update>0) {
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(connection!=null) {
				connection.close();
			}
			return true;
		}
		else {
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(connection!=null) {
				connection.close();
			}
			return false;
		}
		
	}
}
