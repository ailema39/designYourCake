package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectNextOption implements DatabaseCommand {
	
	private Integer stepId;

	public SelectNextOption(Integer stepId) {
		this.stepId = stepId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		// Selects the next step
		Integer position = 1;
		PreparedStatement sta = conn.prepareStatement("SELECT MAX(POSITION)+1 FROM STEP_OPTION WHERE ORDER_STEP_ID = ? AND IS_DELETED = 0");
		
		sta.setLong(1, this.stepId);
		
		ResultSet rs = sta.executeQuery();
		
		if (rs.next()) {
			position = rs.getInt(1);
			
			if(position==0){
				position++;
			}
		}
		
		rs.close();
		sta.close();
		
		return position;
	}
}
