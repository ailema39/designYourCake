package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.StepOption;

public class SelectStepOption implements DatabaseCommand {
	
	private Long optionId;

	public SelectStepOption(Long optionId) {
		this.optionId = optionId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		// select an order step in the database

		StepOption option = new StepOption();
		PreparedStatement sta = conn.prepareStatement("SELECT SO.ID, SO.ORDER_STEP_ID, SO.NAME, SO.POSITION, SO.PRICE, SO.IS_UNAVAILABLE FROM STEP_OPTION SO WHERE SO.ID = ? ");
		sta.setLong(1, this.optionId);
		ResultSet rs = sta.executeQuery();
		
		if (rs.next()) {
			option.setId(rs.getLong(1));
			option.setOrderStepId(rs.getLong(2));
			option.setName(rs.getString(3));
			option.setPosition(rs.getInt(4));
			option.setPrice(rs.getDouble(5));
			option.setUnavailable(rs.getInt(6));
		}
		
		rs.close();
		sta.close();
		
		return option;
	}

}
