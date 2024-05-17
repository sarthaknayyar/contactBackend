package com.kdhabadge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdhabadge.entity.Product;
import com.kdhabadge.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	// Save a single product
	public String saveProduct(Product product) {
		productRepository.save(product);

		return "1";
	}

	// Save all products
	public String saveProducts(List<Product> products) {
		productRepository.saveAll(products);

		return "All Products Added Successfully!!!";
	}

	// Get single product by ID
	public Product getProductById(int id) {
		return productRepository.findById(id).orElse(null);
	}

	// Get single product by NAME
	public Product getProductByName(String name) {
		return productRepository.findByName(name);
	}

	// Get All Products
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	// Delete product by ID
	public String deleteProductByID(int id) {
		productRepository.deleteById(id);

		return "Product Deleted " + id;
	}

	// Update Product details
	public String updateProduct(Product product) {
		Product existingProduct = productRepository.findById(product.getId()).orElse(null);
		existingProduct.setName(product.getName());
		existingProduct.setEmail(product.getEmail());
		existingProduct.setMessage(product.getMessage());
		productRepository.save(existingProduct);

		return "Product updated successfully\n\tID = " + product.getId() + "\n\tNAME = " + product.getName()
				+ "\n\tEmail = " + product.getEmail() + "\n\tMessage = " + product.getMessage();

	}

}
