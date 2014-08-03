package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


import domain.Estimation;
import domain.Order;

public class CreateOrderSpecial implements DatabaseCommand {
	
	private Order order;
	private Estimation estimation;

	public CreateOrderSpecial(Order order,  Estimation estimation) {
		this.order = order;
		this.estimation = estimation;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		PreparedStatement sta = conn.prepareStatement("INSERT INTO ESTIMATION (CLIENT_ID, SPECIAL_OCCASION, PRODUCTS, GUESTS_NUMBER, IMAGE, DESCRIPTION)"
				 +" VALUES (?, ?, ?, ?, ?, ?)");
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
				lastIdInserted = rs.getLong("last_insert_id()"); 

				sta = conn.prepareStatement("INSERT INTO `order` (`CLIENT_ID`, `ORDER_TYPE_ID`, `ORDER_DATE`, `TOTAL`, `DELIVERY_DATE`, `IS_PENDING`, `ESTIMATION_ID`) " +
						"VALUES (?, ?, DATE(CURDATE()), ?,?, ?, ?)");
				sta.setLong(1, order.getClientId());
				sta.setLong(2, order.getOrderTypeId());
				sta.setDouble(3, order.getTotal());
				
				java.sql.Date dte = null;
				try{
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
					java.util.Date dt = formatter.parse(order.getDeliveryDate());
					dte = new java.sql.Date(dt.getTime());
				}catch(Exception e){
					e.printStackTrace();	
				}	
				
				sta.setDate(4, dte);
				sta.setInt(5, order.getIsPending());
				sta.setLong(6, lastIdInserted);
				
				sta.executeUpdate();
			}
			
			getLastInsertId.close();
		}
		
		sta.close();
		
		return new Long(lastIdInserted);
	}

}
