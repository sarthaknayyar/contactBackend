package com.kdhabadge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kdhabadge.entity.Product;
import com.kdhabadge.services.EmailSenderService;
import com.kdhabadge.services.ProductService;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
    private EmailSenderService emailService;
	
	//adding a single product
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product product)
	{
		String resp = service.saveProduct(product);
		if(resp == "1"){
		String emailResponse = emailService.sendSimpleMail(product.getEmail(),  "Dear ," + product.getName() +" Query Recieved " ," Your Query has been recorded and will be handled soon by our executives. Thank you for contacting us.");
        System.out.println(emailResponse);
			return "Product Added Successfully!!!";
		}
		else{
			return "Product Not Added!!!";
		}
		
	}
	
	//adding multiple products
	@PostMapping("/addProducts")
	public String addProducts(@RequestBody List<Product> products)
	{
		return service.saveProducts(products);
	}
	
	//Getting all list of Products
	@GetMapping("/products")
	public List<Product> findAllProducts()
	{
		return service.getProducts();
	}
	
	//Getting single product by ID
	@GetMapping("/productByID/{id}")
	public Product findProductByID(@PathVariable int id)
	{
		return service.getProductById(id);
	}
	
	//Getting single product by NAME
	@GetMapping("/productByName/{name}")
	public Product findProductByName(@PathVariable String name)
	{
		return service.getProductByName(name);
	}
	
	//Updating the existing Product by passing id
	@PutMapping("/update")
	public String updateProduct(@RequestBody Product product)
	{
		return service.updateProduct(product);
	}
	
	//Deleting the product by ID
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id)
	{
		return service.deleteProductByID(id);
	}
	
	
	//Testing Application is up and running or not
	@GetMapping("/hello")
	public String hello()
	{
		return "hello";
	}
	
}
