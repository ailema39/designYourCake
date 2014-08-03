package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.OrderStep;

public class CreateOrderStep implements DatabaseCommand {
	
	private OrderStep step;

	public CreateOrderStep(OrderStep step) {
		this.step = step;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		PreparedStatement sta = conn.prepareStatement("UPDATE ORDER_STEP SET POSITION=POSITION+1 WHERE ORDER_TYPE_ID = ? AND POSITION >= ?");
		sta.setLong(1, step.getOrderTypeId());
		sta.setInt(2, step.getPosition());
		int rowsUpdated = sta.executeUpdate();

		int lastIdInserted = -1;
		sta = conn.prepareStatement("INSERT INTO ORDER_STEP (ORDER_TYPE_ID, NAME, POSITION, IS_MULTIPLE_CHOICE) VALUES (?, ?, ?, ?)");
		
		sta.setLong(1, step.getOrderTypeId());
		sta.setString(2, step.getName());
		sta.setInt(3, step.getPosition());
		sta.setInt(4, step.isMultipleChoice());
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
