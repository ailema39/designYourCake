package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Photo;

public class SelectPhoto implements DatabaseCommand {
	
	private Long photoId;
	
	public SelectPhoto(Long photoId){
		this.photoId = photoId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		// select an album in the database

		Photo photo = new Photo();
		PreparedStatement sta = conn.prepareStatement("SELECT P.ID, P.ALBUM_ID, P.NAME, P.IMAGE, P.IS_ACTIVE, P.IS_NEW FROM photo P WHERE P.ID = ? ");
		sta.setLong(1, this.photoId);
		ResultSet rs = sta.executeQuery();
		
		if (rs.next()) {
			photo.setId(rs.getLong(1));
			photo.setAlbumId(rs.getLong(2));
			photo.setName(rs.getString(3));
			photo.setImage(rs.getString(4));
			photo.setActive(rs.getInt(5));
			photo.setNew(rs.getInt(6));
		}
		
		rs.close();
		sta.close();
		
		return photo;
	}

}
