package market.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("pesticide")
public class Pesticide extends Product {

	public Pesticide(String pName, float pPrice) {
		super(pName, pPrice);
	}

}
