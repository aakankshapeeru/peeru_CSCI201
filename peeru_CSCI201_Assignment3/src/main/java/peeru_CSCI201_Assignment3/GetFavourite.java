package peeru_CSCI201_Assignment3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetFavourite {
	public String [] getFavourite(String email,String artistID) throws ClassNotFoundException, SQLException {
		Connection connection=ConnectionUtil.getConnection();
		StringBuilder stringBuilder = new StringBuilder("select * from FavouriteArtists where username = ? ");
		
		if(artistID!=null) {
			stringBuilder.append("AND FID = ?");
		}
		PreparedStatement preparedStatement=connection.prepareStatement(stringBuilder.toString());
		preparedStatement.setString(1, email);
		if(artistID!=null) {
			preparedStatement.setString(2, artistID);
		}
		ArrayList<String> artistList=new ArrayList<>();
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next()) {
			artistList.add(rs.getString("FID"));
		}
		if(rs!=null) {
			rs.close();
		}
		if(preparedStatement!=null) {
			preparedStatement.close();
		}
		if(connection!=null) {
			connection.close();
		}
		return artistList.toArray(new String[0]);
	}


}
