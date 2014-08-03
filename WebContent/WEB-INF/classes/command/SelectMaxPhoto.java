package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Photo;


public class SelectMaxPhoto implements DatabaseCommand {
	
	private Integer albumId;

	public SelectMaxPhoto(Integer albumId) {
		this.albumId = albumId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		// Selects the most recent album

		Photo photo = new Photo();
		PreparedStatement sta = conn.prepareStatement("SELECT MAX(P.ID), P.NAME, P.IMAGE, P.IS_ACTIVE, P.IS_NEW FROM PHOTO P WHERE P.IS_DELETED = 0 AND P.ALBUM_ID = ?");
		sta.setLong(1, albumId);
		ResultSet rs = sta.executeQuery();
		
		if (rs.next()) {
			photo.setId(rs.getLong(1));
			photo.setAlbumId(albumId);
			photo.setName(rs.getString(2));
			photo.setImage(rs.getString(3));
			photo.setActive(rs.getInt(4));
			photo.setNew(rs.getInt(5));
		}
		
		rs.close();
		sta.close();
		
		return photo;
	}

}
