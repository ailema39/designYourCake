package domain;

public class Estimation {
	
	private long id;
	private long clientId;
	private String special_occasion;
	private String products;
	private int guests_number;
	private String image;
	private String description;
	private Client client;
	private String applicationDate;
	
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public long getClientId() {
		return clientId;
	}

	public void setSpecialOccasion(String special_occasion) {
		this.special_occasion = special_occasion;
	}

	public String getSpecialOccasion() {
		return special_occasion;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public String getProducts() {
		return products;
	}

	public void setGuestsNumber(int guests_number) {
		this.guests_number = guests_number;
	}

	public int getGuestsNumber() {
		return guests_number;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

}
