package market.model;

import java.util.Vector;

public class Review implements IVisitable{
	
	private User user;
	private int id;
	private double rating;
	private Vector<Double> allRatings;
	private String comment;
	private int likes;
	
	
	public Review() {
		
	}
	
	public Review(User user, double rating, String comment,int id) {
		this.user = user;
		this.rating = rating;
		this.comment = comment;
		this.likes = 0;
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vector<Double> getAllRatings() {
		return allRatings;
	}

	public void setAllRatings(Vector<Double> allRatings) {
		this.allRatings = allRatings;
	}
	
	//rating
	@Override
	public void accept(IVisitor visitor) {
		rating = visitor.visit(this);
	}


}
