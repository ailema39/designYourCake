package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.OrderType;

public class SelectOrderType implements DatabaseCommand {
	
	private Integer typeId;

	public SelectOrderType(Integer typeId) {
		this.typeId = typeId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		// select an order type in the database

		OrderType orderType = new OrderType();
		PreparedStatement sta = conn.prepareStatement("SELECT OT.ID, OT.NAME FROM ORDER_TYPE OT WHERE OT.ID = ? ");
		sta.setLong(1, this.typeId);
		ResultSet rs = sta.executeQuery();
		
		if (rs.next()) {
			orderType.setId(rs.getLong(1));
			orderType.setName(rs.getString(2));
		}
		
		rs.close();
		sta.close();
		
		return orderType;
	}

}
