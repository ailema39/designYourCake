package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.Photo;

public class EditPhoto implements DatabaseCommand {
	
	private Photo photo;

	public EditPhoto(Photo photo) {
		this.photo = photo;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		PreparedStatement sta = conn.prepareStatement("UPDATE photo SET NAME = ?, IMAGE = ?, IS_ACTIVE = ?, IS_NEW = ? WHERE ID = ?");
		
		sta.setString(1, photo.getName());
		sta.setString(2, photo.getImage());
		sta.setInt(3, photo.isActive());
		sta.setInt(4, photo.isNew());
		sta.setLong(5, photo.getId());
		int rows_updated = sta.executeUpdate();
		sta.close();
		
		return new Integer(rows_updated);
	}

}
