package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;

public class SelectUser implements DatabaseCommand {
	
	private String email;
	
	public SelectUser(String email){
		this.email = email;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		// select an album in the database

		User user = new User();
		PreparedStatement sta = conn.prepareStatement("SELECT U.ID, U.FIRST_NAME, U.LAST_NAME, U.EMAIL, U.USER_NAME, U.PASSWORD FROM user U WHERE U.EMAIL = ? ");
		sta.setString(1, this.email);
		ResultSet rs = sta.executeQuery();
		
		if (rs.next()) {
			user.setId(rs.getLong(1));
			user.setFirstName(rs.getString(2));
			user.setLastName(rs.getString(3));
			user.setEmail(rs.getString(4));
			user.setUserName(rs.getString(5));
			user.setPassword(rs.getString(6));
		}
		
		rs.close();
		sta.close();
		
		return user;
	}

}
