package market.model;

import java.time.LocalDate;
import java.util.Vector;

public class Post implements IVisitable {
	
	private double rating;
	private Vector<Double> allRatings;
	private int id;
	private LocalDate date;
	private String description;
	private double price;
	private Product product;
	private String imageURL;
	private Vector<Review> reviews;
	private User user; 
	
	public Post() {
		
	}
	public Post(double rating, LocalDate date, String description, Product product, String imageURL,Vector<Review> reviews, int id, User user) {
		this.rating = rating;
		this.allRatings = new Vector<Double>();
		this.date = date;
		this.description = description;
		this.price = product.getPrice();
		this.product = product;
		this.imageURL = imageURL;
		this.reviews = reviews;
		this.id = id;
		this.user = user;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Vector<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Vector<Review> reviews) {
		this.reviews = reviews;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public Vector<Double> getAllRatings() {
		return allRatings;
	}
	public void setAllRatings(Vector<Double> allRatings) {
		this.allRatings = allRatings;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	//rating
	@Override
	public void accept(IVisitor visitor) {
		rating = visitor.visit(this);
	}
	
	
	
}
