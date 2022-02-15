package com.nl.ecommerce;

import com.nl.ecommerce.model.Cart_Items;
import com.nl.ecommerce.model.Category;
import com.nl.ecommerce.model.Customer;
import com.nl.ecommerce.model.Product;
import com.nl.ecommerce.repository.Cart_itemsRepo;
import com.nl.ecommerce.repository.CategoryRepo;
import com.nl.ecommerce.repository.CustomerRepo;
import com.nl.ecommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {
	@Autowired private CustomerRepo customerRepo;
	@Autowired private ProductRepo productRepo;
	@Autowired private CategoryRepo categoryRepo;
	@Autowired private Cart_itemsRepo cart_itemsRepo;
	public static void main(String[] args) {

		SpringApplication.run(EcommerceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		hello();
	}

	public void hello() {
		Customer cus1 = new Customer("Xiaoling", "Qi", "SinterklaasStreet", "111", "Afoort", "Nl", "xiaoling@mail.com", "06555");
		Customer cus2 = new Customer("Eric", "Doorn", "SinterklaasStreet", "111", "Afoort", "Nl", "xiaoling@mail.com", "06555");

		Product product = new Product("Cups", "acup", 50, 20.00);

		Cart_Items cart_items1 = new Cart_Items(10, cus1, product);
		Cart_Items cart_items2 = new Cart_Items(20, cus2, product);

		product.getItems().add(cart_items1);
		product.getItems().add(cart_items2);

		cart_itemsRepo.save(cart_items1);
	}

//	private Customer createOneCustomer() {
//		Customer customer = new Customer("Xiaoling", "Qi", "SinterklaasStreet", "111", "Afoort", "Nl", "xiaoling@mail.com", "06555");
//		customerRepo.save(customer);
//		return customer;
//	}
//	private Category createOneCategory() {
//		Category category = new Category("cups");
//		categoryRepo.save(category);
//		return category;
//	}
//
//	private Product addProductToCategory() {
//		Product product = new Product("cup", "aNiceCup", 50, 20.00);
//		Category createdCategory = createOneCategory();
//		product.setCategory(createdCategory);
//		productRepo.save(product);
//		return product;
//	}
//
//	private void linkCustomerWithProduct () {
//		Customer createdCustomer = createOneCustomer();
//		Product createdProduct = addProductToCategory();
//		Cart_Items c = new Cart_Items(createdCustomer, createdProduct);
//		createdCustomer.getItems().add(c);
//		createdProduct.getItems().add(c);
//		customerRepo.save(createdCustomer);
//		productRepo.save(createdProduct);
//		cart_itemsRepo.save(c);
//	}




}
