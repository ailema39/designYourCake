package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.OrderStep;

public class ListOrderSteps implements DatabaseCommand {
	
	private Integer typeId;

	public ListOrderSteps(Integer typeId) {
		this.typeId = typeId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		// List steps of an order type in the database		
		ArrayList<OrderStep> list = new ArrayList<OrderStep>();
		PreparedStatement sta = conn.prepareStatement("SELECT OS.ID, OS.ORDER_TYPE_ID, OS.NAME, OS.POSITION, OS.IS_MULTIPLE_CHOICE, OS.PRICE_MODE FROM order_step OS WHERE OS.ORDER_TYPE_ID = ? AND OS.IS_DELETED=0");
		sta.setInt(1, this.typeId);
		ResultSet rs = sta.executeQuery();
		
		while(rs.next()) {
			OrderStep step = new OrderStep();
			step.setId(rs.getLong(1));
			step.setOrderTypeId(rs.getLong(2));
			step.setName(rs.getString(3));
			step.setPosition(rs.getInt(4));
			step.setMultipleChoice(rs.getInt(5));
			step.setPriceMode(rs.getInt(6));
			list.add(step);
		}
		
		rs.close();
		sta.close();
		
		return list;
	}

}
