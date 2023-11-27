package market.model;

import java.util.Vector;

public class Sale{
	private Post post;
	private int id;
	private double price;
	private Vector<User> usersToGiveCommission = new Vector<User>();
	private int quantity;
//	private User buyer;
	private User seller;
	
	public Sale() {
		
	}
	
	public Sale(Post pPost, int pId, int pQuantity, User pSeller) {
//		this.buyer = pBuyer;
		this.seller = pSeller;
		this.post = pPost;
		this.id = pId;
		this.quantity = pQuantity;
		this.price = pPost.getPrice();
		for(int i = 0; i < pPost.getReviews().size(); i++ ) {
			User user = pPost.getReviews().elementAt(i).getUser();
			this.usersToGiveCommission.add(user);
		}
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vector<User> getUsersToGiveCommission() {
		return usersToGiveCommission;
	}

	public void setUsersToGiveCommission(Vector<User> usersToGiveCommission) {
		this.usersToGiveCommission = usersToGiveCommission;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
//
//	public void setBuyer(User buyer) {
//		this.buyer = buyer;
//	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}
	

	
	
}
