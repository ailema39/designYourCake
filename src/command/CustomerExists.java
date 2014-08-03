package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Client;

public class CustomerExists implements DatabaseCommand {
	
	private String email;
	private String encryptPassword;
	
	public CustomerExists(String email, String encryptPassword){
		this.email = email;
		this.encryptPassword = encryptPassword;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		Client c = null;
		PreparedStatement sta = conn.prepareStatement("SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD " +
				"FROM client WHERE EMAIL = ? AND PASSWORD = ?");
		sta.setString(1, this.email);
		sta.setString(2, this.encryptPassword);
		ResultSet rs = sta.executeQuery();
		
		while(rs.next()) {
			c = new Client();
			c.setId(rs.getInt(1));
			c.setFirstName(rs.getString(2));
			c.setLastName(rs.getString(3));
			c.setEmail(rs.getString(4));
			c.setPassword(rs.getString(5));	
		}
		
		rs.close();
		sta.close();
		
		return c;
	}

}
