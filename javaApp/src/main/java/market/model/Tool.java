package market.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("tool")
public class Tool extends Product{
	private Condition condition;
	
	public Tool() {
		
	}
	public Tool(String pName,float pPrice, Condition pCon) {
		super(pName, pPrice);
		condition = pCon;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

}
