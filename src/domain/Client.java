package domain;

public class Client {
	
	private long id;
	private int isCompany;
	private String identityCard;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String address;
	private String phone;
	private String otherPhone;
	private int isShippingAddress;
	private String shippingAddress;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public void setCompany(int isCompany) {
		this.isCompany = isCompany;
	}

	public int isCompany() {
		return isCompany;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setShippingAddress(int isShippingAddress) {
		this.isShippingAddress = isShippingAddress;
	}

	public int isShippingAddress() {
		return isShippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

}
