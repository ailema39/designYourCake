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
		PreparedStatement sta = conn.prepareStatement("SELECT SO.ID, SO.ORDER_STEP_ID, SO.NAME, SO.DESCRIPTION, SO.POSITION, SO.IMAGE, SO.PRICE, SO.IS_UNAVAILABLE, SO.PRICE_TWO, SO.PRICE_THREE, SO.PRICE_FOUR FROM step_option SO WHERE SO.ID = ? ");
		sta.setLong(1, this.optionId);
		ResultSet rs = sta.executeQuery();
		
		if (rs.next()) {
			option.setId(rs.getLong(1));
			option.setOrderStepId(rs.getLong(2));
			option.setName(rs.getString(3));
			option.setDescription(rs.getString(4));
			option.setPosition(rs.getInt(5));
			option.setImage(rs.getString(6));
			option.setPrice(rs.getDouble(7));
			option.setUnavailable(rs.getInt(8));
			option.setPriceTwo(rs.getDouble(9));
			option.setPriceThree(rs.getDouble(10));
			option.setPriceFour(rs.getDouble(11));
		}
		
		rs.close();
		sta.close();
		
		return option;
	}

}
