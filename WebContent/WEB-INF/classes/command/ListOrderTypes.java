package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.OrderType;

public class ListOrderTypes implements DatabaseCommand {

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		// select order types in the database

		ArrayList<OrderType> list = new ArrayList<OrderType>();
		PreparedStatement sta = conn.prepareStatement("SELECT OT.ID, OT.NAME FROM ORDER_TYPE OT ");
		ResultSet rs = sta.executeQuery();
		
		while(rs.next()) {
			OrderType orderType = new OrderType();
			orderType.setId(rs.getLong(1));
			orderType.setName(rs.getString(2));
			list.add(orderType);
		}
		
		rs.close();
		sta.close();
		
		return list;
	}

}
