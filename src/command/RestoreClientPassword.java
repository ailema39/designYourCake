package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RestoreClientPassword implements DatabaseCommand {
	
	private String email;
	private String newPassword;
	
	public RestoreClientPassword(String email, String newPassword){
		this.email = email;
		this.newPassword = newPassword;
	}
	
				

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {

		PreparedStatement sta = conn.prepareStatement("UPDATE client SET PASSWORD = ? WHERE EMAIL = ?");
		
		sta.setString(1,  newPassword);
		sta.setString(2,  email);
		int rowsUpdated = sta.executeUpdate();
		sta.close();
		
		return new Integer(rowsUpdated);
	}

}
