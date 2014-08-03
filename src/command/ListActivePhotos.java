package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Photo;

public class ListActivePhotos implements DatabaseCommand {
	
	private Integer albumId;

	public ListActivePhotos(Integer albumId) {
		this.albumId = albumId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		// List photos of an album in the database		
		ArrayList<Photo> list = new ArrayList<Photo>();
		PreparedStatement sta = conn.prepareStatement("SELECT P.ID, P.ALBUM_ID, P.NAME, P.IMAGE, P.IS_ACTIVE, P.IS_NEW FROM photo P WHERE P.IS_ACTIVE = 1 AND P.ALBUM_ID = ? AND P.IS_DELETED=0");
		sta.setInt(1, this.albumId);
		ResultSet rs = sta.executeQuery();
		
		while(rs.next()) {
			Photo photo = new Photo();
			photo.setId(rs.getLong(1));
			photo.setAlbumId(rs.getLong(2));
			photo.setName(rs.getString(3));
			photo.setImage(rs.getString(4));
			photo.setActive(rs.getInt(5));
			photo.setNew(rs.getInt(6));
			list.add(photo);
		}
		
		rs.close();
		sta.close();
		
		return list;
	}

}
