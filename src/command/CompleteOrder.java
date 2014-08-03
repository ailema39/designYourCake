package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompleteOrder implements DatabaseCommand {
	
	private long orderId;

	public CompleteOrder(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		PreparedStatement sta = conn.prepareStatement("UPDATE `order` SET IS_PENDING=0 WHERE ID = ?");
		sta.setLong(1, this.orderId);
		int rowsUpdated = sta.executeUpdate();
		sta.close();
		return new Integer(rowsUpdated);
	}

}
