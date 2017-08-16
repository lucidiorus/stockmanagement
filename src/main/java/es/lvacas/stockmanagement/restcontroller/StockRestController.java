package es.lvacas.stockmanagement.restcontroller;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.lvacas.stockmanagement.model.Product;
import es.lvacas.stockmanagement.service.StockService;

/**
 * This class handles all the request done through the API REST created.
 * When a request is done, the corresponding controller return a response
 * encapsulation the products information in JSON format
 */
@RestController(value = "/")
public class StockRestController {
	
	@Autowired private StockService stockService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Get all the information about the existing products
	 * @return a string in JSON format with the information of all products
	 */
	@RequestMapping(value = "/getAllProductsInfo", method = RequestMethod.GET)
	public String getAllProductsInfo(){
		List<Product> productList =  stockService.getAllProductsInformation();
		ArrayNode jsonProducts = mapper.createArrayNode();
		for(Product product : productList){
			ObjectNode jsonProduct = createJsonProductFromProduct(product);
			jsonProducts.add(jsonProduct);
			
		}
		return jsonProducts.toString();
		
	}
	
	/**
	 * Get the information about the product with the id requested. If the product
	 * with such id does not exist a message is shown
	 * @param id
	 * @return a string in JSON format with the information of the product requested
	 */
	@RequestMapping(value = "/getProductInfo", method = RequestMethod.GET)
	public String getProductInfoById(@RequestParam("id") long id){
		String result = null;
		
		Product productRequested = stockService.getProductStockById(id);
		if(productRequested!=null){
			ObjectNode jsonProduct = createJsonProductFromProduct(productRequested);
			result = jsonProduct.toString();
		}
		else{
			result = "Product does not exist!";
		}
		
		return result;
	}
	
	
	/**
	 * Get the product selected and increase its stock the quantity indicated
	 * @param id
	 * @param quantity
	 * @return the product updated as a string in JSON format
	 */
	@RequestMapping(value = "/refillProductStock", method = RequestMethod.GET)
	public String refillProductStock(@RequestParam("id") long id, @RequestParam("quantity") long quantity){
		String result = null;
		
		Product productRefilled = stockService.refillProductStockById(id, quantity);		
		if(productRefilled!=null){
			ObjectNode jsonProduct = createJsonProductFromProduct(productRefilled);
			result = jsonProduct.toString();
		}
		else{
			result = "Product does not exist!";
		}
		
		return result;
	}
	
	/**
	 * Get the product selected and decrease its stock the quantity indicated
	 * @param id
	 * @param quantity
	 * @return the product updated as a string in JSON format
	 */
	@RequestMapping(value = "/decreaseProductStock", method = RequestMethod.GET)
	public String decreaseProductStock(@RequestParam("id") long id, @RequestParam("quantity") long quantity){
		String result = null;
		Product productDecreased = stockService.decreasedProductStockById(id, quantity);	
		
		if(productDecreased!=null){
			ObjectNode jsonProduct = createJsonProductFromProduct(productDecreased);
			result = jsonProduct.toString();
		}
		else{
			result = "Product does not exist!";
		}
		
		return result;
	}
	
	
	// Auxiliar method to return the product as a JSON string
	private ObjectNode createJsonProductFromProduct(Product product){ 
	
		ObjectNode jsonProduct = mapper.createObjectNode();
		jsonProduct.put("Id", product.getId());
		jsonProduct.put("Name", product.getName());
		jsonProduct.put("Description", product.getDescription());
		jsonProduct.put("Stock", product.getStock());
		
		return jsonProduct;
	}
	
	

}
