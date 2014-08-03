package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Client;

public class EditClientPassword implements DatabaseCommand {
	
	private Client client;
	private String oldPassword;

	public EditClientPassword(Client client, String oldPassword) {
		this.client = client;
		this.oldPassword = oldPassword;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		int rowsUpdated = 0;
		
		PreparedStatement sta = conn.prepareStatement("SELECT PASSWORD FROM client WHERE ID = ?");
		sta.setLong(1, client.getId());
		ResultSet rs = sta.executeQuery();
		
		while(rs.next()) {
			String oldPass = rs.getString(1);
			if (oldPass.equals(oldPassword)){
				sta = conn.prepareStatement("UPDATE client SET PASSWORD = ? WHERE ID = ?");
				sta.setString(1, client.getPassword());
				sta.setLong(2, client.getId());
				rowsUpdated = sta.executeUpdate();
			}
			else {
				rowsUpdated = -2;
			}
		}
		
		sta.close();
		return new Integer(rowsUpdated);
	}

}
