package domain;

import java.util.List;

public class Order {
	
	private long id;
	private long clientId;
	private long orderTypeId;
	private String orderDate;
	private double total;
	private String deliveryDate;
	private int isPending;
	private String orderTypeName;
	private String clientName;
	private String additionalInfo;
	private Long estimationId;
	private List<Item> items;
	private Estimation estimation;
	private Client client;
	
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

	public void setOrderTypeId(long orderTypeId) {
		this.orderTypeId = orderTypeId;
	}

	public long getOrderTypeId() {
		return orderTypeId;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotal() {
		return total;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setIsPending(int isPending) {
		this.isPending = isPending;
	}

	public int getIsPending() {
		return isPending;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public String getOrderTypeName() {
		return orderTypeName;
	}

	public void setClientName(String firstName, String lastName) {
		
		if(lastName != null && !lastName.equalsIgnoreCase("")){
			this.clientName = firstName + " " + lastName;
		} else {
			this.clientName = firstName;
		}
	}

	public String getClientName() {
		return clientName;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setEstimationId(Long estimationId) {
		this.estimationId = estimationId;
	}

	public Long getEstimationId() {
		return estimationId;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setEstimation(Estimation estimation) {
		this.estimation = estimation;
	}

	public Estimation getEstimation() {
		return estimation;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}
}
