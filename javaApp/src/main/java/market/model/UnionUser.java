package market.model;

import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("unionUser")
public class UnionUser extends User{
	private Vector<SingleUser> peopleInUnion;
	private double commision = 0.07;
	
	public UnionUser() {
		
	}
	public UnionUser(String name, int id, float userRating, Vector<SingleUser> pPeopleInUnion) {
		super(name, id, userRating);
		this.peopleInUnion = pPeopleInUnion;
	}

	public Vector<SingleUser> getPeopleInUnion() {
		return peopleInUnion;
	}

	public void setPeopleInUnion(Vector<SingleUser> peopleInUnion) {
		this.peopleInUnion = peopleInUnion;
	}

	public double getCommision() {
		return commision;
	}

	public void setCommision(double commision) {
		this.commision = commision;
	}

}
