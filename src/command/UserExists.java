package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;

public class UserExists implements DatabaseCommand {
	
	private String name;
	private String encryptPassword;
	
	public UserExists(String name, String encryptPassword){
		this.name = name;
		this.encryptPassword = encryptPassword;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		User u = null;
		PreparedStatement sta = conn.prepareStatement("SELECT ID, FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD " +
				"FROM user WHERE USER_NAME = ? AND PASSWORD = ?");
		sta.setString(1, this.name);
		sta.setString(2, this.encryptPassword);
		ResultSet rs = sta.executeQuery();
		
		while(rs.next()) {
			u = new User();
			u.setId(rs.getInt(1));
			u.setFirstName(rs.getString(2));
			u.setLastName(rs.getString(3));
			u.setUserName(rs.getString(4));
			u.setPassword(rs.getString(5));	
		}
		
		rs.close();
		sta.close();
		
		return u;
	}

}
