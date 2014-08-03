package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Album;

public class SelectAlbum implements DatabaseCommand {
	
	private Long albumId;
	
	public SelectAlbum(Long albumId){
		this.albumId = albumId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		// select an album in the database

		Album album = new Album();
		PreparedStatement sta = conn.prepareStatement("SELECT A.ID, A.NAME, A.IMAGE, A.IS_ACTIVE, A.IS_NEW FROM album A WHERE A.ID = ? ");
		sta.setLong(1, this.albumId);
		ResultSet rs = sta.executeQuery();
		
		if (rs.next()) {
			album.setId(rs.getLong(1));
			album.setName(rs.getString(2));
			album.setImage(rs.getString(3));
			album.setActive(rs.getInt(4));
			album.setNew(rs.getInt(5));
		}
		
		rs.close();
		sta.close();
		
		return album;
	}

}
