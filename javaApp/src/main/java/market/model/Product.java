package market.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
	@JsonSubTypes.Type(value = Pesticide.class, name = "pesticide"),
    @JsonSubTypes.Type(value = Tool.class, name = "tool"),
    @JsonSubTypes.Type(value = Machinery.class, name = "machinery"),
    @JsonSubTypes.Type(value = Fertilizer.class, name = "fertilizer")
})

public abstract class Product {
	
	private String name;
	private float price;
	
	public Product() {
		
	}
	public Product(String pName,float pPrice) {
		this.name = pName;
		this.price = pPrice;	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
