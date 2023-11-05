package market.model;

public class Review {
	
	private User user;
	private int id;
	private double rating;
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


}
