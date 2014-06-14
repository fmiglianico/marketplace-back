package com.formation.jee.domain;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;

@JsonTypeInfo(  
    use = JsonTypeInfo.Id.NAME,  
    include = JsonTypeInfo.As.PROPERTY,  
    property = "type")  
@JsonSubTypes({  
    @Type(value = Armor.class, name = "armor"),  
    @Type(value = Weapon.class, name = "weapon"),  
    @Type(value = Consumable.class, name = "consumable") }) 
public class Item {
	
	private int id;
	private String name;
	private double price;
	private String imgUrl;
	private int quantity;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Item))
			return false;
		Item other = (Item) obj;
		if (id != other.getId())
			return false;
		return true;
	}
}
