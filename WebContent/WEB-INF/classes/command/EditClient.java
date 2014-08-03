package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.Client;

public class EditClient implements DatabaseCommand {
	
	private Client client;
	
	public EditClient(Client client){
		this.client = client;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {

		PreparedStatement sta = conn.prepareStatement("UPDATE CLIENT " +
				" SET IDENTITY_CARD = ?, FIRST_NAME = ? , LAST_NAME= ? , EMAIL= ? , ADDRESS= ? , PHONE= ? , " +
				" OTHER_PHONE= ? , IS_SHIPPING_ADDRESS= ? , SHIPPING_ADDRESS= ? , IS_COMPANY= ? " +
				" WHERE ID = ?");
		
		sta.setString(1,  client.getIdentityCard());
		sta.setString(2,  client.getFirstName());
		sta.setString(3,  client.getLastName());
		sta.setString(4,  client.getEmail());
		sta.setString(5,  client.getAddress());
		sta.setString(6,  client.getPhone());
		sta.setString(7,  client.getOtherPhone());
		sta.setInt(8, client.isShippingAddress());
		sta.setString(9, client.getShippingAddress());
		sta.setInt(10, client.isCompany());
		sta.setLong(11, client.getId());
				
		int rowsUpdated = sta.executeUpdate();
		sta.close();
		
		return new Integer(rowsUpdated);
	}

}
