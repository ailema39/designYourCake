package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.OrderItem;

public class SelectOrderItem implements DatabaseCommand {
	
	private Long orderId;
	private Long stepOptionId;

	public SelectOrderItem(Long orderId, Long stepOptionId) {
		this.orderId = orderId;
		this.stepOptionId = stepOptionId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		PreparedStatement sta = conn.prepareStatement("SELECT O.ID, O.ORDER_ID, O.STEP_OPTION_ID, O.ADDITIONAL_INFO, O.PRICE " +
				"FROM `order_item` O WHERE O.ORDER_ID = ? AND O.STEP_OPTION_ID = ?");
		
		sta.setLong(1, this.orderId);
		sta.setLong(2, this.stepOptionId);
		
		ResultSet rs = sta.executeQuery();
		OrderItem item = new OrderItem();
		
		if (rs.next()) {
			item.setId(rs.getLong(1));
			item.setOrderId(rs.getLong(2));
			item.setStepOptionId(rs.getLong(3));
			item.setNombreImg(rs.getString(4));
			item.setPrice(rs.getDouble(5));
		}
		
		sta.close();
		
		return item;
	}
}

