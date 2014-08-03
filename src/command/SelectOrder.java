package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Order;

public class SelectOrder implements DatabaseCommand {
	
	private Long orderId;

	public SelectOrder(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		PreparedStatement sta = conn.prepareStatement("SELECT O.ID, O.CLIENT_ID, O.ORDER_TYPE_ID, DATE_FORMAT(O.ORDER_DATE, '%d/%m/%Y'), " +
				"O.TOTAL, DATE_FORMAT(O.DELIVERY_DATE, '%d/%m/%Y'), O.IS_PENDING, OT.NAME, C.FIRST_NAME, C.LAST_NAME, O.ESTIMATION_ID, O.ADDITIONAL_INFO FROM `order` O, " +
				"order_type OT, client C WHERE O.ID = ? AND O.ORDER_TYPE_ID = OT.ID AND O.CLIENT_ID = C.ID");
		
		sta.setLong(1, this.orderId);
		
		ResultSet rs = sta.executeQuery();
		Order order = new Order();
		
		if (rs.next()) {
			order.setId(rs.getLong(1));
			order.setClientId(rs.getLong(2));
			order.setOrderTypeId(rs.getLong(3));
			order.setOrderDate(rs.getString(4));
			order.setTotal(rs.getDouble(5));
			order.setDeliveryDate(rs.getString(6));
			order.setIsPending(rs.getInt(7));
			order.setOrderTypeName(rs.getString(8));
			order.setClientName(rs.getString(9), rs.getString(10));
			order.setEstimationId(rs.getLong(11));
			order.setAdditionalInfo(rs.getString(12));
		}
		
		sta.close();
		
		return order;
	}
}
