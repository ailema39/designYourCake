package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteClient implements DatabaseCommand {
	
	private long clientId;

	public DeleteClient(Long clientId) {
		this.clientId = clientId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		PreparedStatement sta = conn.prepareStatement("UPDATE order_item SET IS_DELETED=1, DELETED_DATE = NOW() WHERE ORDER_ID IN (SELECT ID FROM `order` WHERE CLIENT_ID = ? AND IS_DELETED=0) AND IS_DELETED=0");
		sta.setLong(1, this.clientId);
		int rowsUpdated = sta.executeUpdate();
		sta = conn.prepareStatement("UPDATE `order` SET IS_DELETED=1, DELETED_DATE = NOW() WHERE CLIENT_ID = ? AND IS_DELETED=0");
		sta.setLong(1, this.clientId);
		rowsUpdated = sta.executeUpdate();
		sta = conn.prepareStatement("UPDATE client SET IS_DELETED=1, DELETED_DATE = NOW() WHERE ID = ?");
		sta.setLong(1, this.clientId);
		rowsUpdated = sta.executeUpdate();
		sta.close();
		return new Integer(rowsUpdated);
	}
}
