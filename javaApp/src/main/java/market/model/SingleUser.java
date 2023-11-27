package market.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("singleUser")
public class SingleUser extends User{
	
	public SingleUser() {
		
	}
	public SingleUser(String name, int id, float userRating, String email, String password) {
		super(name, id, userRating, email, password);
		this.commision = 0.04;
	}

}
