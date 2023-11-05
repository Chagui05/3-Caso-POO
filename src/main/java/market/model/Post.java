package market.model;

import java.awt.Image;
import java.time.LocalDate;
import java.util.Vector;

public class Post {
	
	private double rating;
	private int id;
	private LocalDate date;
	private String description;
	private double price;
	private Product product;
	private String imageURL;
	private Vector<Review> reviews;
	
	public Post() {
		
	}
	public Post(double rating, LocalDate date, String description, Product product, String image,Vector<Review> reviews, int id) {
		this.rating = rating;
		this.date = date;
		this.description = description;
		this.price = product.getPrice();
;		this.product = product;
		this.imageURL = image;
		this.reviews = reviews;
		this.id = id;
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
	
	
	
}
