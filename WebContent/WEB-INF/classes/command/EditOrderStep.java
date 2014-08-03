package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.OrderStep;

public class EditOrderStep implements DatabaseCommand {
	
	private OrderStep step;

	public EditOrderStep(OrderStep step) {
		this.step = step;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		PreparedStatement sta;
		int rowsUpdated;
		
		if (step.getOldPosition() > step.getPosition()) {
			sta = conn.prepareStatement("UPDATE ORDER_STEP SET POSITION=POSITION+1 WHERE ORDER_TYPE_ID = ? AND POSITION >= ? AND POSITION < ?");
			sta.setLong(1, step.getOrderTypeId());
			sta.setInt(2, step.getPosition());
			sta.setInt(3, step.getOldPosition());
			rowsUpdated = sta.executeUpdate();
		} else if (step.getOldPosition() < step.getPosition()) {
			sta = conn.prepareStatement("UPDATE ORDER_STEP SET POSITION=POSITION-1 WHERE ORDER_TYPE_ID = ? AND POSITION > ? AND POSITION <= ?");
			sta.setLong(1, step.getOrderTypeId());
			sta.setInt(2, step.getOldPosition());
			sta.setInt(3, step.getPosition());
			rowsUpdated = sta.executeUpdate();
		}
				
		sta = conn.prepareStatement("UPDATE ORDER_STEP SET NAME = ?, POSITION = ?, IS_MULTIPLE_CHOICE = ? WHERE ID = ?");
		sta.setString(1, step.getName());
		sta.setInt(2, step.getPosition());
		sta.setInt(3, step.isMultipleChoice());
		sta.setLong(4, step.getId());
		rowsUpdated = sta.executeUpdate();				
		sta.close();
		
		return new Integer(rowsUpdated);
	}
}
