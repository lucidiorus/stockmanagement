package es.lvacas.stockmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity to store all the details about the products. The annotations used by JPA to 
 * match the objects with the corresponding database have been set but are not used
 * as it has not been created a Database. The application is loading the products for
 * test directly in memory. The next step should be to create the DAO of the application
 * to access to the database where the products are stored.
 */

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name", length = 48)
	private String name;
	
	@Column(name = "description", length = 512)
	private String description;
	
	@Column(name = "stock")
	private long stock;
	
	
	
	public Product(long id, String name, String description, long stock) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.stock = stock;
	}


	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public long getStock() {
		return stock;
	}


	public void setStock(long stock) {
		this.stock = stock;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", stock=" + stock + "]";
	}
	
}
