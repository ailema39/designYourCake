package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Client;
import domain.Estimation;

public class SelectEstimation implements DatabaseCommand {
	
	private Long id;
	
	public SelectEstimation(Long id){
		this.id = id;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		PreparedStatement sta = conn.prepareStatement("SELECT E.ID, E.CLIENT_ID, E.SPECIAL_OCCASION, E.PRODUCTS, E.GUESTS_NUMBER, E.IMAGE, " +
				"E.DESCRIPTION, DATE_FORMAT(E.APPLICATION_DATE, '%d/%m/%Y'), C.FIRST_NAME, C.LAST_NAME, C.IDENTITY_CARD, C.EMAIL, C.ADDRESS, " +
				"C.PHONE, C.OTHER_PHONE, C.SHIPPING_ADDRESS FROM `estimation` E, client C WHERE E.ID=? AND E.CLIENT_ID = C.ID");
		
		sta.setLong(1, this.id);
					
		ResultSet rs = sta.executeQuery();
		Estimation estimation = new Estimation();
		Client client = new Client();
		
		if (rs.next()) {
			estimation.setId(rs.getLong(1));
			estimation.setClientId(rs.getLong(2));
			estimation.setSpecialOccasion(rs.getString(3));
			estimation.setProducts(rs.getString(4));
			estimation.setGuestsNumber(rs.getInt(5));
			estimation.setImage(rs.getString(6));
			estimation.setDescription(rs.getString(7));
			estimation.setApplicationDate(rs.getString(8));
			client.setFirstName(rs.getString(9));
			client.setLastName(rs.getString(10));
			client.setIdentityCard(rs.getString(11));
			client.setEmail(rs.getString(12));
			client.setAddress(rs.getString(13));
			client.setPhone(rs.getString(14));
			client.setOtherPhone(rs.getString(15));
			client.setShippingAddress(rs.getString(16));
			estimation.setClient(client);
		}
		
		sta.close();
		
		return estimation;
	}

}
