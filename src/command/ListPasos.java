package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import domain.ListOrder_Step;
import domain.OrderStep;
import domain.StepOption;

public class ListPasos implements DatabaseCommand {

	private Integer typeId;

	public ListPasos(Integer typeId) {
		this.typeId = typeId;
	}

	@Override
	public Object executeDatabaseOperation(Connection conn) throws SQLException {
		
		// List options of a step order in the database		
		/* Obtengo los pasos primeros */
		PreparedStatement sta = conn.prepareStatement("SELECT OS.ID, OS.NAME, OS.IS_MULTIPLE_CHOICE, OS.POSITION " +
				"FROM order_step OS WHERE OS.ORDER_TYPE_ID = ? AND OS.IS_DELETED=0 ORDER BY OS.POSITION ASC");
		sta.setInt(1, this.typeId);
		long stepId = -1;
		ResultSet rs = sta.executeQuery();
		ArrayList<ListOrder_Step> listOrder = new ArrayList<ListOrder_Step>();
		
		while(rs.next()) {
			
			OrderStep order = new OrderStep();
			stepId = rs.getLong(1);
			order.setName(rs.getString(2));
			order.setMultipleChoice(rs.getInt(3));
			order.setPosition(rs.getInt(4));
			if (typeId != -1){
				/* Obtengo las opciones de esos pasos */
				PreparedStatement staInt = conn.prepareStatement("SELECT SO.ID, " +
					"SO.ORDER_STEP_ID, " +
					"SO.NAME, SO.DESCRIPTION, " +
					"SO.POSITION, SO.IMAGE, " +
					"SO.PRICE, SO.PRICE_TWO, SO.PRICE_THREE, SO.PRICE_FOUR " +
					"FROM step_option SO WHERE SO.ORDER_STEP_ID = ? AND SO.IS_DELETED=0 AND IS_UNAVAILABLE=0" +
					" ORDER BY SO.POSITION ASC");
				staInt.setLong(1, stepId);
				ResultSet rs2 = staInt.executeQuery();
				List<StepOption> steps = new LinkedList<StepOption>();
				ListOrder_Step orderSteps = new ListOrder_Step();
				while(rs2.next()) {
					StepOption option = new StepOption();
					option.setId(rs2.getLong(1));
					option.setOrderStepId(rs2.getLong(2));
					option.setName(rs2.getString(3));
					option.setDescription(rs2.getString(4));
					option.setPosition(rs2.getInt(5));
					option.setImage(rs2.getString(6));
					option.setPrice(rs2.getDouble(7));
					option.setPriceTwo(rs2.getDouble(8));
					option.setPriceThree(rs2.getDouble(9));
					option.setPriceFour(rs2.getDouble(10));
					System.out.println("price 1" + option.getPrice() +  "price 2" + option.getPriceTwo() + "price 3" + option.getPriceThree() + "Preice 4 "+ option.getPriceFour());
					steps.add(option);
				}
				orderSteps.setOrder(order);
				orderSteps.setStep(steps);
				listOrder.add(orderSteps);
				staInt.close();
				rs2.close();
			}
		}
		rs.close();
		sta.close();
		
		return listOrder;
	}

}
