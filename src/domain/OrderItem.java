package domain;

public class OrderItem {
	
	private long id;
	private long orderId;
	private long stepOptionId;
	private double price;
	private String nameImage;
	private String txtCalcomania;
	private String cantDocenas;
	
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
	
	public void setNombreImg(String nombreImg) {
		this.nameImage = nombreImg;
	}

	public String getNombreImg() {
		return nameImage;
	}
	
	public void setTxtCalcomania(String txtCalcomania) {
		this.txtCalcomania = txtCalcomania;
	}

	public String getTxtCalcomania() {
		return txtCalcomania;
	}
	
	public void setCantDocenas(String cantDocenas) {
		this.cantDocenas = cantDocenas;
	}

	public String getCantDocenas() {
		return cantDocenas;
	}
	

}
