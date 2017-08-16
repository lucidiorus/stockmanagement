package es.lvacas.stockmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.lvacas.stockmanagement.model.Product;

@Service
public class StockServiceImpl implements StockService{
	
	private static List<Product> productList = new ArrayList<Product>();
	
	static{
		Product product1 = new Product(1, "Product1 Name", "Description for product1", 5);
		Product product2 = new Product(2, "Product2 Name", "Description for product2", 10);
		Product product3 = new Product(3, "Product3 Name", "Description for product3", 15);
		Product product4 = new Product(4, "Product4 Name", "Description for product4", 20);
		
		productList.add(product1);
		productList.add(product2);
		productList.add(product3);
		productList.add(product4);
	}

	
	@Override
	public List<Product> getAllProductsInformation() {
		return productList;
	}

	
	@Override
	public Product getProductStockById(long productId) {
		Product productRequested = getProductOfProductListById(productId);
		return productRequested;
	}

	
	@Override
	public Product refillProductStockById(long productId, long quantityToIncrement) {
		Product productRequested = getProductOfProductListById(productId);
		
		if(productRequested!=null){
			long currentStock = productRequested.getStock();
			long newStock = currentStock + quantityToIncrement;
			productRequested.setStock(newStock);
		}
		return productRequested;
	}

	@Override
	public Product decreasedProductStockById(long productId, long quantityToDecrease) {
		Product productRequested = getProductOfProductListById(productId);
		
		if(productRequested!=null){
			long currentStock = productRequested.getStock();
			long newStock = currentStock - quantityToDecrease;
			if(newStock<0)
				newStock = 0;
			productRequested.setStock(newStock);
		}
		return productRequested;
	}
	
	
	// Auxiliar method
	private Product getProductOfProductListById(long productId){
		Product productRequested = null;
		for(Product product : productList){
			if(product.getId() == productId){
				productRequested = product;
				break;
			}
		}
		return productRequested;
	}


	

}
