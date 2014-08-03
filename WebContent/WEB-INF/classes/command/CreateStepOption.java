package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.StepOption;

public class CreateStepOption implements DatabaseCommand {
	
	private StepOption option;

	public CreateStepOption(StepOption option) {
		this.option = option;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {

		PreparedStatement sta = conn.prepareStatement("UPDATE STEP_OPTION SET POSITION=POSITION+1 WHERE ORDER_STEP_ID = ? AND POSITION >= ?");
		sta.setLong(1, option.getOrderStepId());
		sta.setInt(2, option.getPosition());
		int rowsUpdated = sta.executeUpdate();

		int lastIdInserted = -1;
		sta = conn.prepareStatement("INSERT INTO STEP_OPTION (ORDER_STEP_ID, NAME, POSITION, PRICE, IS_UNAVAILABLE) VALUES (?, ?, ?, ?, ?)");
		
		sta.setLong(1, option.getOrderStepId());
		sta.setString(2, option.getName());
		sta.setInt(3, option.getPosition());
		sta.setDouble(4, option.getPrice());
		sta.setInt(5, option.isUnavailable());
		rowsUpdated = sta.executeUpdate();

		if (rowsUpdated == 1){
			
			PreparedStatement getLastInsertId = conn.prepareStatement("SELECT LAST_INSERT_ID()");  
			ResultSet rs = getLastInsertId.executeQuery();
			
			if (rs.next()){
				lastIdInserted = rs.getInt("last_insert_id()"); 
			}
			
			getLastInsertId.close();
		}
		
		sta.close();
		
		return new Integer(lastIdInserted);
	}

}
