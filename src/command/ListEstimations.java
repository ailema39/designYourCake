package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Client;
import domain.Estimation;

public class ListEstimations implements DatabaseCommand {

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		// List estimations in the database		
		ArrayList<Estimation> list = new ArrayList<Estimation>();
		PreparedStatement sta = conn.prepareStatement("SELECT E.ID, E.CLIENT_ID, E.SPECIAL_OCCASION, E.PRODUCTS, E.GUESTS_NUMBER, E.IMAGE, " +
				"E.DESCRIPTION, DATE_FORMAT(E.APPLICATION_DATE, '%d/%m/%Y'), C.FIRST_NAME, C.LAST_NAME FROM `estimation` E, client C WHERE E.IS_DELETED=0 AND E.BECAME_AN_ORDER = 0 AND E.CLIENT_ID = C.ID");
		ResultSet rs = sta.executeQuery();
		
		while(rs.next()) {
			Estimation estimation = new Estimation();
			
			estimation.setId(rs.getLong(1));
			estimation.setClientId(rs.getLong(2));
			estimation.setSpecialOccasion(rs.getString(3));
			estimation.setProducts(rs.getString(4));
			estimation.setGuestsNumber(rs.getInt(5));
			estimation.setImage(rs.getString(6));
			estimation.setDescription(rs.getString(7));
			estimation.setApplicationDate(rs.getString(8));
			
			Client client = new Client();
			
			client.setFirstName(rs.getString(9));
			client.setLastName(rs.getString(10));
			estimation.setClient(client);
			
			list.add(estimation);
		}
		
		rs.close();
		sta.close();
		
		return list;
	}

}
