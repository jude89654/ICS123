package edu.ust.erdbms.utility.sql;

import edu.ust.erdbms.model.ProductBean;

public class BeanFactory {
	
	public static ProductBean getInstance(java.sql.Date date_delivered, 
			String item, String manufacturer, int product_code, int quantity,double product_price){
		
		ProductBean bean = new ProductBean(); 
		bean.setDate_delivered(date_delivered);
		bean.setItem(item);
		bean.setManufacturer(manufacturer);
		bean.setProduct_code(product_code);
		bean.setQuantity(quantity);
		bean.setProduct_price(product_price);
		bean.compute();
		return bean;
	}
}
