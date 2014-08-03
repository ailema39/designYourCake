package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;

public class EditUserPassword implements DatabaseCommand {

	private User user;
	private String oldPassword;

	public EditUserPassword(User user, String oldPassword) {
		this.user = user;
		this.oldPassword = oldPassword;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		int rowsUpdated = 0;
		
		PreparedStatement sta = conn.prepareStatement("SELECT PASSWORD FROM user WHERE ID = ?");
		sta.setLong(1, user.getId());
		ResultSet rs = sta.executeQuery();
		
		while(rs.next()) {
			
			String oldPass = rs.getString(1);
			
			if (oldPass.equals(oldPassword)){
				sta = conn.prepareStatement("UPDATE user SET PASSWORD = ? WHERE ID = ?");
				sta.setString(1, user.getPassword());
				sta.setLong(2, user.getId());
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
