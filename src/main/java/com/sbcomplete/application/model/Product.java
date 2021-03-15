package com.sbcomplete.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	private int id;
	private String productName;
	private String description;
	private int price;
	private String availability;

	public Product(){}

	public Product(int id, String productName, String description, int price, String availability) {
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.availability = availability;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() { return price; }
	public void setPrice(int price) { this.price = price; }
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", description=" + description + ", availability="
				+ availability + "]";
	}


}
