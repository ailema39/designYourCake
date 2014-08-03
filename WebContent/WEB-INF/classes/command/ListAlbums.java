package command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domain.Album;

public class ListAlbums implements DatabaseCommand {

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		// List users in the database
		
		ArrayList<Album> list = new ArrayList<Album>();
		Statement sta = conn.createStatement();
		ResultSet rs = sta.executeQuery("SELECT A.ID, A.NAME, A.IMAGE, A.IS_ACTIVE, A.IS_NEW FROM ALBUM A WHERE A.IS_DELETED=0");
		while(rs.next()) {
			Album album = new Album();
			album.setId(rs.getInt(1));
			album.setName(rs.getString(2));
			album.setImage(rs.getString(3));
			album.setActive(rs.getInt(4));
			album.setNew(rs.getInt(5));
			list.add(album);
		}
		rs.close();
		sta.close();
		
		return list;
	}
}
