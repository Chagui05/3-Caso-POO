package market.model;

import java.util.Vector;

public class Sale{
	private Post post;
	private int id;
	private double price;
	private Vector<User> usersToGiveCommission;
	
	public Sale() {
		
	}
	
	public Sale(Post pPost, int pId) {
		this.post = pPost;
		this.id = pId;
		for(Review review : pPost.getReviews() ) {
			this.usersToGiveCommission.add(review.getUser());
		}
		this.price = pPost.getPrice();
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
	

	
	
}