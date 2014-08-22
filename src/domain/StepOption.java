package domain;

public class StepOption {
	
	private long id;
	private long orderStepId;
	private String name;
	private int position;
	private int oldPosition;
	private double price;
	private double priceTwo;
	private double priceThree;
	private double priceFour;
	private int isUnavailable;
	private String description;
	private String image;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public void setOrderStepId(long orderStepId) {
		this.orderStepId = orderStepId;
	}

	public long getOrderStepId() {
		return orderStepId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setUnavailable(int isUnavailable) {
		this.isUnavailable = isUnavailable;
	}

	public int isUnavailable() {
		return isUnavailable;
	}

	public void setOldPosition(int oldPosition) {
		this.oldPosition = oldPosition;
	}

	public int getOldPosition() {
		return oldPosition;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setPriceTwo(double priceTwo) {
		this.priceTwo = priceTwo;
	}

	public double getPriceTwo() {
		return priceTwo;
	}

	public void setPriceThree(double priceThree) {
		this.priceThree = priceThree;
	}

	public double getPriceThree() {
		return priceThree;
	}

	public void setPriceFour(double priceFour) {
		this.priceFour = priceFour;
	}

	public double getPriceFour() {
		return priceFour;
	}
	
	

}
