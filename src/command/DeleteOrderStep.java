package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.OrderStep;

public class DeleteOrderStep implements DatabaseCommand {
	
	private OrderStep step;

	public DeleteOrderStep(OrderStep step) {
		this.step = step;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		PreparedStatement sta = conn.prepareStatement("UPDATE order_step SET POSITION=POSITION-1 WHERE ORDER_TYPE_ID = ? AND POSITION > ?");
		sta.setLong(1, step.getOrderTypeId());
		sta.setInt(2, step.getPosition());
		int rowsUpdated = sta.executeUpdate();
		
		sta = conn.prepareStatement("UPDATE step_option SET IS_DELETED=1, DELETED_DATE = NOW() WHERE ORDER_STEP_ID = ?");
		sta.setLong(1, step.getId());
		rowsUpdated = sta.executeUpdate();
		
		sta = conn.prepareStatement("UPDATE order_step SET IS_DELETED=1, DELETED_DATE = NOW() WHERE ID = ?");
		sta.setLong(1, step.getId());
		rowsUpdated = sta.executeUpdate();
		
		sta.close();
		
		return new Integer(rowsUpdated);
	}

}
