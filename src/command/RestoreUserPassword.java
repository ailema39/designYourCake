package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RestoreUserPassword implements DatabaseCommand {
	
	private String email;
	private String newPassword;
	
	public RestoreUserPassword(String email, String newPassword){
		this.email = email;
		this.newPassword = newPassword;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		PreparedStatement sta = conn.prepareStatement("UPDATE user SET PASSWORD = ? WHERE EMAIL = ?");
		
		sta.setString(1,  newPassword);
		sta.setString(2,  email);
		int rowsUpdated = sta.executeUpdate();
		sta.close();
		
		return new Integer(rowsUpdated);
	}

}
