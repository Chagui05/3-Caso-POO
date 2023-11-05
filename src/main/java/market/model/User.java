package market.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
	@JsonSubTypes.Type(value = SingleUser.class, name = "singleUser"),
    @JsonSubTypes.Type(value = UnionUser.class, name = "unionUser")
})
public abstract class User {
	private String name;
	private int id;
	private double userRating;
	private double wallet;
	
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
	

}
