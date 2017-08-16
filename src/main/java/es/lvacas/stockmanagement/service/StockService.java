package es.lvacas.stockmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import es.lvacas.stockmanagement.model.Product;

/**
 * This class defines the interface for the services related with
 * the management of the product stock
 */
@Service
public interface StockService {

	/**
	 * Get the arraylist with all the existing products
	 * @return all the existing products
	 */
	List<Product> getAllProductsInformation();
	
	/**
	 * Get the product requested by its Id
	 * @param productId
	 * @return the product requested
	 */
	Product getProductStockById(long productId);
	
	/**
	 * Increase the stock for the product selected
	 * @param productId
	 * @param quantityToIncrement
	 * @return the product updated
	 */
	Product refillProductStockById(long productId, long quantityToIncrement);
	
	/**
	 * Decrease the stock for the product selected
	 * @param productId
	 * @param quantityToDecreased
	 * @return the product updated
	 */
	Product decreasedProductStockById(long productId, long quantityToDecreased);
	
}
