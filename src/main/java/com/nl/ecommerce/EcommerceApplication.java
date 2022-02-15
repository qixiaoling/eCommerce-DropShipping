package com.nl.ecommerce;

import com.nl.ecommerce.model.Category;
import com.nl.ecommerce.model.Customer;
import com.nl.ecommerce.model.Product;
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
	public static void main(String[] args) {

		SpringApplication.run(EcommerceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		createCustomer();
		addProductToCategory();
	}

	private void createCustomer() {
		Customer customer = new Customer("Xiaoling", "Qi", "SinterklaasStreet", "111", "Afoort", "Nl", "xiaoling@mail.com", "06555");
		customerRepo.save(customer);
	}
	private Category createOneCategory() {
		Category category = new Category("cups");
		categoryRepo.save(category);
		return category;
	}

	private void addProductToCategory() {
		Product product = new Product("cup", "aNiceCup", 50, 20.00);
		Category createdCategory = createOneCategory();
		product.setCategory(createdCategory);
		productRepo.save(product);
	}




}
