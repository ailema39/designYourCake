package domain;

public class Item {
	
	private long id;	
	private long orderId;
	private int position;
	private long orderStepId;
	private String stepLabel;
	private int isMultipleChoice;
	private long stepOptionId;
	private String optionName;	
	private String additionalInfo;
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

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	public void setOrderStepId(long orderStepId) {
		this.orderStepId = orderStepId;
	}

	public long getOrderStepId() {
		return orderStepId;
	}

	public void setStepLabel(String stepLabel) {
		this.stepLabel = stepLabel;
	}

	public String getStepLabel() {
		return stepLabel;
	}

	public void setIsMultipleChoice(int isMultipleChoice) {
		this.isMultipleChoice = isMultipleChoice;
	}

	public int getIsMultipleChoice() {
		return isMultipleChoice;
	}

	public void setStepOptionId(long stepOptionId) {
		this.stepOptionId = stepOptionId;
	}

	public long getStepOptionId() {
		return stepOptionId;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}
	

}
