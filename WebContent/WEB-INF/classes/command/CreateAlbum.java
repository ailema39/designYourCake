package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Album;

public class CreateAlbum implements DatabaseCommand {
	
	private Album album;
	
	public CreateAlbum(Album album){
		this.album = album;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		int lastIdInserted = -1;
		PreparedStatement sta = conn.prepareStatement("INSERT INTO ALBUM (NAME, IMAGE, IS_ACTIVE, IS_NEW) VALUES (?, ?, ?, ?)");
		
		sta.setString(1, album.getName());
		sta.setString(2, album.getImage());
		sta.setInt(3, album.isActive());
		sta.setInt(4, album.isNew());
		int rowsUpdated = sta.executeUpdate();
		
		if (rowsUpdated == 1){
			
			PreparedStatement getLastInsertId = conn.prepareStatement("SELECT LAST_INSERT_ID()");  
			ResultSet rs = getLastInsertId.executeQuery();
			
			if (rs.next()){
				lastIdInserted = rs.getInt("last_insert_id()"); 
			}
			
			getLastInsertId.close();
		}
		
		sta.close();
		
		return new Integer(lastIdInserted);
	}

}
