package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import domain.Estimation;

public class CreateOrderSpecial implements DatabaseCommand {
	
	private Estimation estimation;

	public CreateOrderSpecial(Estimation estimation) {
		this.estimation = estimation;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		PreparedStatement sta = conn.prepareStatement("INSERT INTO estimation (CLIENT_ID, SPECIAL_OCCASION, PRODUCTS, GUESTS_NUMBER, IMAGE, DESCRIPTION, APPLICATION_DATE)"
				 +" VALUES (?, ?, ?, ?, ?, ?, DATE(CURDATE()))");
		sta.setLong(1, estimation.getClientId());
		sta.setString(2, estimation.getSpecialOccasion());
		sta.setString(3, estimation.getProducts());
		sta.setInt(4, estimation.getGuestsNumber());
		sta.setString(5, estimation.getImage());
		sta.setString(6, estimation.getDescription());
		
		long lastIdInserted = -1;
		
		int rowsUpdated = sta.executeUpdate();
		
		if (rowsUpdated == 1){
			
			PreparedStatement getLastInsertId = conn.prepareStatement("SELECT LAST_INSERT_ID()");  
			ResultSet rs = getLastInsertId.executeQuery();
			
			if (rs.next()){
				lastIdInserted = rs.getInt("last_insert_id()"); 
			}
				
			getLastInsertId.close();
		}
		
		sta.close();
	
		return new Long(lastIdInserted);
	}

}
