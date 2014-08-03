package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEstimation implements DatabaseCommand {
	
	private Long estimationId;

	public DeleteEstimation(Long estimationId) {
		this.estimationId = estimationId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		PreparedStatement sta = conn.prepareStatement("UPDATE estimation SET IS_DELETED=1, DELETED_DATE = NOW() WHERE ID = ?");
		sta.setLong(1, this.estimationId);
		int rowsUpdated = sta.executeUpdate();
		
		sta.close();
		
		return new Integer(rowsUpdated);
	}

}
