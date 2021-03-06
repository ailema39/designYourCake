package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.StepOption;

public class EditStepOption implements DatabaseCommand {
	
	private StepOption option;

	public EditStepOption(StepOption option) {
		this.option = option;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		PreparedStatement sta;
		int rowsUpdated;
		
		if (option.getOldPosition() > option.getPosition()) {
			sta = conn.prepareStatement("UPDATE step_option SET POSITION=POSITION+1 WHERE ORDER_STEP_ID = ? AND POSITION >= ? AND POSITION < ?");
			sta.setLong(1, option.getOrderStepId());
			sta.setInt(2, option.getPosition());
			sta.setInt(3, option.getOldPosition());
			rowsUpdated = sta.executeUpdate();
		} else if (option.getOldPosition() < option.getPosition()) {
			sta = conn.prepareStatement("UPDATE step_option SET POSITION=POSITION-1 WHERE ORDER_STEP_ID = ? AND POSITION > ? AND POSITION <= ?");
			sta.setLong(1, option.getOrderStepId());
			sta.setInt(2, option.getOldPosition());
			sta.setInt(3, option.getPosition());
			rowsUpdated = sta.executeUpdate();
		}
				
		sta = conn.prepareStatement("UPDATE step_option SET NAME = ?, DESCRIPTION = ?, POSITION = ?, PRICE = ?, IS_UNAVAILABLE = ?, " +
				"PRICE_TWO = ?, PRICE_THREE = ?, PRICE_FOUR = ? WHERE ID = ?");
		sta.setString(1, option.getName());
		sta.setString(2, option.getDescription());
		sta.setInt(3, option.getPosition());
		sta.setDouble(4, option.getPrice());
		sta.setInt(5, option.isUnavailable());
		sta.setDouble(6, option.getPriceTwo());
		sta.setDouble(7, option.getPriceThree());
		sta.setDouble(8, option.getPriceFour());
		sta.setLong(9, option.getId());
		rowsUpdated = sta.executeUpdate();				
		sta.close();
		
		return new Integer(rowsUpdated);
	}

}
