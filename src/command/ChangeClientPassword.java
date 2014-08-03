package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeClientPassword implements DatabaseCommand {
	
	private String email;
	private String newPassword;
	private String oldPassword;
	
	public ChangeClientPassword(String email, String newPassword, String oldPassword){
		this.email = email;
		this.newPassword = newPassword;
		this.oldPassword = oldPassword;
		
	}
	
				

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {

		PreparedStatement sta = conn.prepareStatement("UPDATE client SET PASSWORD = ? WHERE EMAIL = ? AND PASSWORD= ?");
		
		sta.setString(1,  newPassword);
		sta.setString(2,  email);
		sta.setString(3,  oldPassword);
		int rowsUpdated = sta.executeUpdate();
		sta.close();
		
		return new Integer(rowsUpdated);
	}

}
