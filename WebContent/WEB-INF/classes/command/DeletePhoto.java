package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePhoto implements DatabaseCommand {
	
	private Long id;

	public DeletePhoto(Long id) {
		this.id = id;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		PreparedStatement sta = conn.prepareStatement("UPDATE PHOTO SET IS_DELETED=1, DELETED_DATE = NOW() WHERE ID = ?");
		sta.setLong(1, this.id);
		int rowsUpdated = sta.executeUpdate();
		
		sta.close();
		
		return new Integer(rowsUpdated);
	}

}
