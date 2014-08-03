package domain;

public class OrderItem {
	
	private long id;
	private long orderId;
	private long stepOptionId;
	private double price;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setStepOptionId(long stepOptionId) {
		this.stepOptionId = stepOptionId;
	}

	public long getStepOptionId() {
		return stepOptionId;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}
	

}
