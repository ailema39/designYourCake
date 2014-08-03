package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.StepOption;

public class ListDulcesTortas implements DatabaseCommand {

	private Integer typeId;

	public ListDulcesTortas(Integer typeId) {
		this.typeId = typeId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		// List options of a step order in the database		
		ArrayList<StepOption> list = new ArrayList<StepOption>();
		PreparedStatement sta = conn.prepareStatement("SELECT OS.ID " +
				"FROM order_step OS WHERE OS.ORDER_TYPE_ID = ? AND OS.IS_DELETED=0");
		sta.setInt(1, this.typeId);
		long stepId = -1;
		ResultSet rs = sta.executeQuery();
		while(rs.next()) {
			stepId = rs.getLong(1);
		}
		if (typeId != -1){
			sta = conn.prepareStatement("SELECT SO.ID, " +
				"SO.ORDER_STEP_ID, " +
				"SO.NAME, " +
				"SO.POSITION, " +
				"SO.PRICE,  SO.DESCRIPTION,  SO.IMAGE " +
				"FROM step_option SO WHERE SO.ORDER_STEP_ID = ? AND SO.IS_DELETED=0 AND IS_UNAVAILABLE=0" +
				" ORDER BY SO.POSITION ASC");
			sta.setLong(1, stepId);
			rs = sta.executeQuery();
			
			while(rs.next()) {
				StepOption option = new StepOption();
				option.setId(rs.getLong(1));
				option.setOrderStepId(rs.getLong(2));
				option.setName(rs.getString(3));
				option.setPosition(rs.getInt(4));
				option.setPrice(rs.getDouble(5));
				option.setDescription(rs.getString(6));
				option.setImage(rs.getString(7));
				list.add(option);
			}
		}
		
		rs.close();
		sta.close();
		
		return list;
	}

}
