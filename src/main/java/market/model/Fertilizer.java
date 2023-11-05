package market.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("fertilizer")
public class Fertilizer extends Product{

	public Fertilizer(String pName,  float pPrice) {
		super(pName, pPrice);
	}

}
