package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Item;

public class ListItems implements DatabaseCommand {
	
	private Long orderId;

	public ListItems(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		// List orders of a client in the database		
		ArrayList<Item> list = new ArrayList<Item>();
		PreparedStatement sta = conn.prepareStatement("SELECT OI.ID, OI.ORDER_ID, OI.STEP_OPTION_ID, OI.ADDITIONAL_INFO, OI.PRICE, SO.ORDER_STEP_ID, OS.LABEL, SO.NAME AS OPTION_NAME, " +
				"OS.POSITION, OS.IS_MULTIPLE_CHOICE FROM order_item OI, step_option SO, order_step OS WHERE OI.ORDER_ID = ? AND OI.IS_DELETED = 0 AND OI.STEP_OPTION_ID = SO.ID AND " +
				"SO.ORDER_STEP_ID = OS.ID ORDER BY POSITION ASC");
		sta.setLong(1, this.orderId);
		
		ResultSet rs = sta.executeQuery();
		
		while(rs.next()) {
			Item item = new Item();
			item.setId(rs.getLong(1));
			item.setOrderId(rs.getLong(2));
			item.setStepOptionId(rs.getLong(3));
			item.setAdditionalInfo(rs.getString(4));
			item.setPrice(rs.getDouble(5));
			item.setOrderStepId(rs.getLong(6));
			item.setStepLabel(rs.getString(7));
			item.setOptionName(rs.getString(8));
			item.setPosition(rs.getInt(9));
			item.setIsMultipleChoice(rs.getInt(10));
			list.add(item);
		}
		
		rs.close();
		sta.close();
		
		return list;
	}

}
