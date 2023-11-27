package market.model;

public class SaleRequest {
	private int quantity;
//	private User buyer;
	
	public SaleRequest() {
		
	}
	

	public SaleRequest(int quantity) {
		this.quantity = quantity;
//		this.buyer = buyer;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

//	public User getBuyer() {
//		return buyer;
//	}

//	public void setBuyer(User buyer) {
//		this.buyer = buyer;
//	}
//	

}
