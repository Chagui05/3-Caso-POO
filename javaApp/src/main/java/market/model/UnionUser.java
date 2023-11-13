package market.model;

import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("unionUser")
public class UnionUser extends User{
	private Vector<SingleUser> peopleInUnion;
	
	public UnionUser() {
		
	}
	public UnionUser(String name, int id, float userRating, Vector<SingleUser> pPeopleInUnion) {
		super(name, id, userRating);
		this.peopleInUnion = pPeopleInUnion;
		this.commision = 0.07;
	}

	public Vector<SingleUser> getPeopleInUnion() {
		return peopleInUnion;
	}

	public void setPeopleInUnion(Vector<SingleUser> peopleInUnion) {
		this.peopleInUnion = peopleInUnion;
	}

}
