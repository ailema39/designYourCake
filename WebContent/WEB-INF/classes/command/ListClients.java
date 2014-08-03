package command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domain.Client;

public class ListClients implements DatabaseCommand {

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		// List clients in the database
		
		ArrayList<Client> list = new ArrayList<Client>();
		Statement sta = conn.createStatement();
		ResultSet rs = sta.executeQuery("SELECT C.ID, C.IS_COMPANY, C.IDENTITY_CARD, C.FIRST_NAME, C.LAST_NAME, C.EMAIL, C.PASSWORD, " +
				"C.ADDRESS, C.PHONE, C.OTHER_PHONE, C.IS_SHIPPING_ADDRESS, C.SHIPPING_ADDRESS FROM CLIENT C WHERE C.IS_DELETED=0");
		while(rs.next()) {
			Client client = new Client();
			client.setId(rs.getInt(1));
			client.setCompany(rs.getInt(2));
			client.setIdentityCard(rs.getString(3));
			client.setFirstName(rs.getString(4));
			client.setLastName(rs.getString(5));
			client.setEmail(rs.getString(6));
			client.setPassword(rs.getString(7));
			client.setAddress(rs.getString(8));
			client.setPhone(rs.getString(9));
			client.setOtherPhone(rs.getString(10));
			client.setShippingAddress(rs.getInt(11));
			client.setShippingAddress(rs.getString(12));

			list.add(client);
		}
		rs.close();
		sta.close();
		
		return list;
	}

}
