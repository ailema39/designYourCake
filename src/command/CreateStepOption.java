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

		PreparedStatement sta = conn.prepareStatement("UPDATE step_option SET POSITION=POSITION+1 WHERE ORDER_STEP_ID = ? AND POSITION >= ?");
		sta.setLong(1, option.getOrderStepId());
		sta.setInt(2, option.getPosition());
		int rowsUpdated = sta.executeUpdate();

		int lastIdInserted = -1;
		sta = conn.prepareStatement("INSERT INTO step_option (ORDER_STEP_ID, NAME, DESCRIPTION, POSITION, PRICE, IS_UNAVAILABLE, PRICE_TWO, PRICE_THREE, PRICE_FOUR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		sta.setLong(1, option.getOrderStepId());
		sta.setString(2, option.getName());
		sta.setString(3, option.getDescription());
		sta.setInt(4, option.getPosition());
		sta.setDouble(5, option.getPrice());
		sta.setInt(6, option.isUnavailable());
		sta.setDouble(7, option.getPriceTwo());
		sta.setDouble(8, option.getPriceThree());
		sta.setDouble(9, option.getPriceFour());
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
