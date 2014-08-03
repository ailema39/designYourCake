package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.Album;

public class EditAlbum implements DatabaseCommand {
	
	private Album album;
	
	public EditAlbum(Album album){
		this.album = album;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {

		PreparedStatement sta = conn.prepareStatement("UPDATE ALBUM SET NAME = ?, IMAGE = ?, IS_ACTIVE = ?, IS_NEW = ? WHERE ID = ?");
		
		sta.setString(1, album.getName());
		sta.setString(2, album.getImage());
		sta.setInt(3, album.isActive());
		sta.setInt(4, album.isNew());
		sta.setLong(5, album.getId());
		int rows_updated = sta.executeUpdate();
		sta.close();
		
		return new Integer(rows_updated);
	}

}
