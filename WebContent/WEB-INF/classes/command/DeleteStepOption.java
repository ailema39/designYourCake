package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.StepOption;

public class DeleteStepOption implements DatabaseCommand {
	
	private StepOption option;

	public DeleteStepOption(StepOption option) {
		this.option = option;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		PreparedStatement sta = conn.prepareStatement("UPDATE STEP_OPTION SET POSITION=POSITION-1 WHERE ORDER_STEP_ID = ? AND POSITION > ?");
		sta.setLong(1, option.getOrderStepId());
		sta.setInt(2, option.getPosition());
		int rowsUpdated = sta.executeUpdate();
		
		sta = conn.prepareStatement("UPDATE STEP_OPTION SET IS_DELETED=1, DELETED_DATE = NOW() WHERE ID = ?");
		sta.setLong(1, option.getId());
		rowsUpdated = sta.executeUpdate();
		
		sta.close();
		
		return new Integer(rowsUpdated);
	}

}
