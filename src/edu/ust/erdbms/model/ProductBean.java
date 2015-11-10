package edu.ust.erdbms.model;

public class ProductBean {
	private java.sql.Date date_delivered;
	private String item, manufacturer;
	private int product_code, quantity;
	
	public java.sql.Date getDate_delivered() {
		return date_delivered;
	}
	public void setDate_delivered(java.sql.Date date_delivered) {
		this.date_delivered = date_delivered;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getProduct_code() {
		return product_code;
	}
	public void setProduct_code(int product_code) {
		this.product_code = product_code;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
