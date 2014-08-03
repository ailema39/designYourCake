package domain;

public class User {
	
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private String password;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
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

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}
