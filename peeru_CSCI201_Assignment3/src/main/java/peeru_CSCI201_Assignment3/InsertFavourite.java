package peeru_CSCI201_Assignment3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertFavourite {
	public boolean insertFavourite(String email, String artistID) throws ClassNotFoundException, SQLException {
		Connection connection=ConnectionUtil.getConnection();
		String query = "INSERT INTO FavouriteArtists(FID, username) VALUES (?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, artistID);
		preparedStatement.setString(2, email);
		int updated = preparedStatement.executeUpdate();
		if(updated==1) {
			return true;
		}
		return false;
		
	}
}
