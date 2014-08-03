package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Photo;

public class CreatePhoto implements DatabaseCommand {
	
	private Photo photo;

	public CreatePhoto(Photo photo) {
		this.photo = photo;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		int lastIdInserted = -1;
		PreparedStatement sta = conn.prepareStatement("INSERT INTO photo (ALBUM_ID, NAME, IMAGE, IS_ACTIVE, IS_NEW) VALUES (?, ?, ?, ?, ?)");
		
		sta.setLong(1, photo.getAlbumId());
		sta.setString(2, photo.getName());
		sta.setString(3, photo.getImage());
		sta.setInt(4, photo.isActive());
		sta.setInt(5, photo.isNew());
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
