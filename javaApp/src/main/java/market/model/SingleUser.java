package market.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("singleUser")
public class SingleUser extends User{
	
	public SingleUser() {
		
	}
	public SingleUser(String name, int id, float userRating) {
		super(name, id, userRating);
		this.commision = 0.04;
	}

}
