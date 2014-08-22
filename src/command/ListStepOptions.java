package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.StepOption;

public class ListStepOptions implements DatabaseCommand {

	private Integer stepId;

	public ListStepOptions(Integer stepId) {
		this.stepId = stepId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		// List options of a step order in the database		
		ArrayList<StepOption> list = new ArrayList<StepOption>();
		PreparedStatement sta = conn.prepareStatement("SELECT SO.ID, SO.ORDER_STEP_ID, SO.NAME, SO.POSITION, SO.PRICE, SO.IS_UNAVAILABLE, SO.PRICE_TWO, SO.PRICE_THREE, SO.PRICE_FOUR FROM step_option SO WHERE SO.ORDER_STEP_ID = ? AND SO.IS_DELETED=0");
		sta.setInt(1, this.stepId);
		ResultSet rs = sta.executeQuery();
		
		while(rs.next()) {
			StepOption option = new StepOption();
			option.setId(rs.getLong(1));
			option.setOrderStepId(rs.getLong(2));
			option.setName(rs.getString(3));
			option.setPosition(rs.getInt(4));
			option.setPrice(rs.getDouble(5));
			option.setUnavailable(rs.getInt(6));
			option.setPriceTwo(rs.getDouble(7));
			option.setPriceThree(rs.getDouble(8));
			option.setPriceFour(rs.getDouble(9));
			
			list.add(option);
		}
		
		rs.close();
		sta.close();
		
		return list;
	}

}
