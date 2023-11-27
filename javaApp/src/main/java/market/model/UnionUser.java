package market.model;

import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("unionUser")
public class UnionUser extends User{
	private Vector<SingleUser> peopleInUnion;
	
	public UnionUser() {
		
	}
	public UnionUser(String name, int id, float userRating, Vector<SingleUser> pPeopleInUnion, String email, String password) {
		super(name, id, userRating, email, password);
		this.peopleInUnion = pPeopleInUnion;
		this.commision = 0.07;
		this.posts = new Vector<Post>();
	}

	public Vector<SingleUser> getPeopleInUnion() {
		return peopleInUnion;
	}

	public void setPeopleInUnion(Vector<SingleUser> peopleInUnion) {
		this.peopleInUnion = peopleInUnion;
	}

}
