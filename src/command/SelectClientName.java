package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectClientName implements DatabaseCommand {
	
	private String email;
	
	public SelectClientName(String email) {
		this.email = email;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		// Selects the client name
		String name = "";
		PreparedStatement sta = conn.prepareStatement("select first_name from client where email = ? AND IS_DELETED = 0");
		
		sta.setString(1, this.email);
		
		ResultSet rs = sta.executeQuery();
		
		if (rs.next()) {
			name = rs.getString(1);
		}
		
		rs.close();
		sta.close();
		
		return name;
	}
}
