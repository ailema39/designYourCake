package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Album;


public class SelectMaxAlbum implements DatabaseCommand {
	
	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		// Selects the most recent album

		Album album = new Album();
		PreparedStatement sta = conn.prepareStatement("SELECT MAX(A.ID), A.NAME, A.IMAGE, A.IS_ACTIVE, A.IS_NEW FROM ALBUM A WHERE A.IS_DELETED = 0");
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
