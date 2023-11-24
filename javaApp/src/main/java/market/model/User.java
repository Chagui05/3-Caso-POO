package market.model;

import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
	@JsonSubTypes.Type(value = SingleUser.class, name = "singleUser"),
    @JsonSubTypes.Type(value = UnionUser.class, name = "unionUser")
})
public abstract class User implements IVisitable {
	private String name;
	private int id;
	private double userRating;
	private Vector<Double> allRatings;
	private double wallet;
	private Vector<Post> posts;
	protected double commision;
	
	public User() {
		
	}
	public User(String name, int id, double userRating) {
		this.name = name;
		this.id = id;
		this.userRating = userRating;
		this.wallet = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getUserRating() {
		return userRating;
	}

	public void setUserRating(double userRating) {
		this.userRating = userRating;
	}

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	public Vector<Double> getAllRatings() {
		return allRatings;
	}
	public void setAllRatings(Vector<Double> allRatings) {
		this.allRatings = allRatings;
	}
	//rating
	public void accept(IVisitor visitor) {
		userRating = visitor.visit(this);
	}
	public void addToWallet(double amount) {
		this.wallet += amount;
	}
	public double getCommision() {
		return commision;
	}
	public void setCommision(double commision) {
		this.commision = commision;
	}

	public Vector<Post> getPosts() {
		return posts;
	}
	public void setPosts(Vector<Post> posts) {
		this.posts = posts;
	}

}