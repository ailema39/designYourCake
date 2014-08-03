package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Order;

public class ListOrders implements DatabaseCommand {

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		// List orders of a client in the database		
		ArrayList<Order> list = new ArrayList<Order>();
		PreparedStatement sta = conn.prepareStatement("SELECT O.ID, O.CLIENT_ID, O.ORDER_TYPE_ID, DATE_FORMAT(O.ORDER_DATE, '%d/%m/%Y'), O.TOTAL, DATE_FORMAT(O.DELIVERY_DATE, '%d/%m/%Y'), " +
				"O.IS_PENDING, OT.NAME, C.FIRST_NAME, C.LAST_NAME FROM `order` O, order_type OT, client C WHERE O.IS_DELETED=0 AND O.ORDER_TYPE_ID = OT.ID AND O.CLIENT_ID = C.ID");
		ResultSet rs = sta.executeQuery();
		
		while(rs.next()) {
			Order order = new Order();
			order.setId(rs.getLong(1));
			order.setClientId(rs.getLong(2));
			order.setOrderTypeId(rs.getLong(3));
			order.setOrderDate(rs.getString(4));
			order.setTotal(rs.getDouble(5));
			order.setDeliveryDate(rs.getString(6));
			order.setIsPending(rs.getInt(7));
			order.setOrderTypeName(rs.getString(8));
			order.setClientName(rs.getString(9), rs.getString(10));
			list.add(order);
		}
		
		rs.close();
		sta.close();
		
		return list;
	}

}
