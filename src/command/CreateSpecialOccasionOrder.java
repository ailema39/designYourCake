package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import domain.Order;

public class CreateSpecialOccasionOrder implements DatabaseCommand {
	
	private Order order;

	public CreateSpecialOccasionOrder(Order order) {
		this.order = order;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {

		int lastIdInserted = -1;	
		PreparedStatement sta = conn.prepareStatement("UPDATE estimation SET BECAME_AN_ORDER=1 WHERE ID = ?");
		sta.setLong(1, order.getEstimationId());
		
		int rowsUpdated = sta.executeUpdate();
		
		if(rowsUpdated == 1){
			sta = conn.prepareStatement("INSERT INTO `order` (`CLIENT_ID`, `ORDER_TYPE_ID`, `ORDER_DATE`, `TOTAL`, `DELIVERY_DATE`, `IS_PENDING`, `ESTIMATION_ID`, ADDITIONAL_INFO) " +
			"VALUES (?, ?, DATE(CURDATE()), ?,?, ?, ?, ?)");

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
			sta.setLong(6, order.getEstimationId());
			sta.setString(7, order.getAdditionalInfo());

			rowsUpdated = sta.executeUpdate();
			
			if (rowsUpdated == 1){
				
				PreparedStatement getLastInsertId = conn.prepareStatement("SELECT LAST_INSERT_ID()");  
				ResultSet rs = getLastInsertId.executeQuery();
				
				if (rs.next()){
					lastIdInserted = rs.getInt("last_insert_id()"); 
				}
				
				getLastInsertId.close();
			}
		}
				
		sta.close();
		
		return new Integer(lastIdInserted);
	}

}
