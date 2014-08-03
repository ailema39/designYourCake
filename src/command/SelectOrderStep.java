package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.OrderStep;

public class SelectOrderStep implements DatabaseCommand {
	
	private Long stepId;

	public SelectOrderStep(Long stepId) {
		this.stepId = stepId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		// select an order step in the database

		OrderStep step = new OrderStep();
		PreparedStatement sta = conn.prepareStatement("SELECT OS.ID, OS.ORDER_TYPE_ID, OS.NAME, OS.POSITION, OS.IS_MULTIPLE_CHOICE FROM order_step OS WHERE OS.ID = ? ");
		sta.setLong(1, this.stepId);
		ResultSet rs = sta.executeQuery();
		
		if (rs.next()) {
			step.setId(rs.getLong(1));
			step.setOrderTypeId(rs.getLong(2));
			step.setName(rs.getString(3));
			step.setPosition(rs.getInt(4));
			step.setMultipleChoice(rs.getInt(5));
		}
		
		rs.close();
		sta.close();
		
		return step;
	}

}
