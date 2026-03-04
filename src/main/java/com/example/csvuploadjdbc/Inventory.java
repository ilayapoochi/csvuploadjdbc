package com.example.csvuploadjdbc;

import java.math.BigDecimal;
import java.util.Date;


public class Inventory {

	Long id;
	String name;
	String category;
	String subCategory;
	Date modDate;
	Date expiryDate;
	String specification;
	BigDecimal price;
	String stock;
	String model;
	
	public Inventory() {
		
	}
	public Inventory(String name, String category, String subCategory, Date modDate, Date expiryDate,
			String specification, BigDecimal price, String stock, String model, String seller, String location) {
		super();
		this.name = name;
		this.category = category;
		this.subCategory = subCategory;
		this.modDate = modDate;
		this.expiryDate = expiryDate;
		this.specification = specification;
		this.price = price;
		this.stock = stock;
		this.model = model;
		this.seller = seller;
		this.location = location;
	}
	String seller;
	String location;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
