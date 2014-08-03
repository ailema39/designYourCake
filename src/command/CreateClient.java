package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Client;

public class CreateClient implements DatabaseCommand {
	
	private Client client;
	
	public CreateClient(Client client){
		this.client = client;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {

		int lastIdInserted = -1;
		
		PreparedStatement sta = conn.prepareStatement("SELECT ID FROM client WHERE EMAIL = ?");
		sta.setString(1, client.getEmail());
		ResultSet rs = sta.executeQuery();
		if (rs.next()){
			// El cliente ya tiene ese correo registrado, dar error
			lastIdInserted = -2;
			
		} else {
			sta = conn.prepareStatement("INSERT INTO client" +
				" (IDENTITY_CARD, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ADDRESS, PHONE, OTHER_PHONE, IS_SHIPPING_ADDRESS, SHIPPING_ADDRESS, IS_COMPANY) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			sta.setString(1,  client.getIdentityCard());
			sta.setString(2,  client.getFirstName());
			sta.setString(3,  client.getLastName());
			sta.setString(4,  client.getEmail());
			sta.setString(5,  client.getPassword());
			sta.setString(6,  client.getAddress());
			sta.setString(7,  client.getPhone());
			sta.setString(8,  client.getOtherPhone());
			sta.setInt(9, client.isShippingAddress());
			sta.setString(10, client.getShippingAddress());
			sta.setInt(11, client.isCompany());
					
			int rowsUpdated = sta.executeUpdate();
			
			if (rowsUpdated == 1){
				
				PreparedStatement getLastInsertId = conn.prepareStatement("SELECT LAST_INSERT_ID()");  
				rs = getLastInsertId.executeQuery();
				
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
