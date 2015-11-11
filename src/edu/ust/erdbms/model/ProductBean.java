package edu.ust.erdbms.model;

public class ProductBean {
	private java.sql.Date date_delivered;
	private String item, manufacturer;
	private int product_code, quantity;
	private double product_price,total_price;
	
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
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	
	public void compute()
	{
		setTotal_price(getProduct_price()*getQuantity());
	}
}
