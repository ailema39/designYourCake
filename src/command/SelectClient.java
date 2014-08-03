package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import domain.Client;

public class SelectClient implements DatabaseCommand {
	
	private Long id;
	
	public SelectClient(Long id){
		this.id = id;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {

		PreparedStatement sta = conn.prepareStatement("SELECT IDENTITY_CARD, FIRST_NAME, LAST_NAME, EMAIL, ADDRESS," +
				" PHONE, OTHER_PHONE, IS_SHIPPING_ADDRESS, SHIPPING_ADDRESS, IS_COMPANY FROM client" +
				" WHERE ID = ?");
		
		sta.setLong(1, this.id);
					
		ResultSet rs = sta.executeQuery();
		Client client = new Client();
		if (rs.next()) {
			client.setId(this.id);
			client.setIdentityCard(rs.getString(1));
			client.setFirstName(rs.getString(2));
			client.setLastName(rs.getString(3));
			client.setEmail(rs.getString(4));
			client.setAddress(rs.getString(5));
			client.setPhone(rs.getString(6));
			client.setOtherPhone(rs.getString(7));
			int shipping = rs.getInt(8);
			client.setShippingAddress(shipping);
			if (shipping == 0){
				client.setShippingAddress(rs.getString(9));
			}else
				client.setShippingAddress(rs.getString(5));
			int company = rs.getInt(10);
			if (company == 0)
				client.setCompany(0);
			else
				client.setCompany(1);
		}
		
		sta.close();
		
		return client;
	}

}
