package market.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("machinery")
public class Machinery extends Product{
	private Condition condition;
	
	public Machinery(String pName, float pPrice, Condition pCon) {
		super(pName,pPrice);
		condition = pCon;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
}
