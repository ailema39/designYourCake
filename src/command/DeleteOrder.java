package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteOrder implements DatabaseCommand {
	
	private Long orderId;

	public DeleteOrder(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {

		PreparedStatement sta = conn.prepareStatement("UPDATE order_item SET IS_DELETED=1, DELETED_DATE = NOW() WHERE ORDER_ID = ?");
		sta.setLong(1, this.orderId);
		int rowsUpdated = sta.executeUpdate();
		sta = conn.prepareStatement("UPDATE `order` SET IS_DELETED=1,DELETED_DATE = NOW() WHERE ID = ?");
		sta.setLong(1, this.orderId);
		rowsUpdated = sta.executeUpdate();
		sta.close();
		return new Integer(rowsUpdated);
	}

}
