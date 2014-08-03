package domain;

import java.util.List;


public class ListOrder_Step {
	
	private OrderStep order;
	private List<StepOption> steps;

	
	public void setOrder(OrderStep order) {
		this.order = order;
	}
	
	public OrderStep getOrder() {
		return order;
	}
	
	public void setStep( List<StepOption> steps) {
		this.steps = steps;
	}
	
	public  List<StepOption> getOrderTypeId() {
		return steps;
	}

}
