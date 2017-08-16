package es.lvacas.stockmanagement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import es.lvacas.stockmanagement.model.Product;
import es.lvacas.stockmanagement.restcontroller.StockRestController;
import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockmanagementRestControllerTests {

	@Autowired private StockRestController stockRestController;

	/* 
	 * Products Stored to use to test the stock management application
	 * Product product1 = new Product(1, "Product1 Name", "Description for product1", 5);
	 * Product product2 = new Product(2, "Product2 Name", "Description for product2", 10);
	 * Product product3 = new Product(3, "Product3 Name", "Description for product3", 15);
	 * Product product4 = new Product(4, "Product4 Name", "Description for product4", 20);
	 */
		
	
	/**
	 * Test the REST API to get all the existing products information
	 */
	@Test
	public void getAllProductsInfoTest() throws JSONException{
		String result = stockRestController.getAllProductsInfo().toString();
		JSONArray jarrayProducts = new JSONArray(result);
		long numberOfProducts = jarrayProducts.length();
		assertThat(numberOfProducts).isEqualTo(4);
		
		List<Product> productList = new ArrayList<>();
		
		for(int i=0; i<jarrayProducts.length(); i++){
			JSONObject jsonProduct = jarrayProducts.getJSONObject(i);
			Product product = createProductFromJsonObject(jsonProduct);
			productList.add(product);
		}
		
		Product product1 = productList.get(0);
		assertThat(product1.getId()).isEqualTo(1);
		assertThat(product1.getStock()).isEqualTo(5);
	}
	
	
	/**
	 * Test the REST API to get the information about the product
	 * indicated by its product id
	 */
	@Test
	public void getProductByIdTest() throws JSONException{
		String result = stockRestController.getProductInfoById(3);
		JSONObject jsonProduct = new JSONObject(result);
			
		Product product3 = createProductFromJsonObject(jsonProduct);
		
		assertThat(product3.getId()).isEqualTo(3);
		assertThat(product3.getName()).isEqualTo("Product3 Name");
		assertThat(product3.getDescription()).isEqualTo("Description for product3");
		assertThat(product3.getStock()).isEqualTo(15);
	}
	
	
	/**
	 * Test the API REST to refill the stock of a product given its product id
	 */
	@Test
	public void refillProductStock() throws JSONException{
		String result = stockRestController.getProductInfoById(2);
		JSONObject jsonProduct = new JSONObject(result);
			
		Product product2 = createProductFromJsonObject(jsonProduct);
		long product2StockBeforeRefill = product2.getStock();
		
		assertThat(product2.getId()).isEqualTo(2);
		assertThat(product2.getName()).isEqualTo("Product2 Name");
		assertThat(product2.getDescription()).isEqualTo("Description for product2");
		assertThat(product2StockBeforeRefill).isEqualTo(10);
		
		// Refill product stock
		long quantityToIncrementStock = 50;
		result = stockRestController.refillProductStock(2, quantityToIncrementStock);
		jsonProduct = new JSONObject(result);
		Product product2AfterRefill = createProductFromJsonObject(jsonProduct);
		long product2StockAfterRefill = product2AfterRefill.getStock();
				
		assertThat(product2StockAfterRefill).isEqualTo(product2StockBeforeRefill+quantityToIncrementStock);
	}
	
	
	/**
	 * Test the API REST to decrease the stock of a product given its product id
	 */
	@Test
	public void decreaseProductStock() throws JSONException{
		String result = stockRestController.getProductInfoById(4);
		JSONObject jsonProduct = new JSONObject(result);
			
		Product product4 = createProductFromJsonObject(jsonProduct);
		long product4StockBeforeDecrease = product4.getStock();
		
		assertThat(product4.getId()).isEqualTo(4);
		assertThat(product4.getName()).isEqualTo("Product4 Name");
		assertThat(product4.getDescription()).isEqualTo("Description for product4");
		assertThat(product4StockBeforeDecrease).isEqualTo(20);
		
		//Decrease product stock
		long quantityToDecrementStock = 5;
		result = stockRestController.decreaseProductStock(4, quantityToDecrementStock);
		jsonProduct = new JSONObject(result);
		Product product4AfterDecrease = createProductFromJsonObject(jsonProduct);
		long product4StockAfterDecrease = product4AfterDecrease.getStock();
				
		assertThat(product4StockAfterDecrease).isEqualTo(product4StockBeforeDecrease-quantityToDecrementStock);
	}
		
	
	
	
	private Product createProductFromJsonObject(JSONObject jsonProduct) throws JSONException{
		return new Product(jsonProduct.getLong("Id"), jsonProduct.getString("Name"),
				jsonProduct.getString("Description"), jsonProduct.getLong("Stock"));
	}
	
	

}
