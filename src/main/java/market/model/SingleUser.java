package market.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("singleUser")
public class SingleUser extends User{
	private double commision = 0.04;
	
	public SingleUser() {
		
	}
	public SingleUser(String name, int id, float userRating) {
		super(name, id, userRating);
	}

	public double getCommision() {
		return commision;
	}

	public void setCommision(double commision) {
		this.commision = commision;
	}

}
