package com.nl.ecommerce;

import com.nl.ecommerce.model.Cart_Items;
import com.nl.ecommerce.model.Category;
import com.nl.ecommerce.model.Customer;
import com.nl.ecommerce.model.Product;
import com.nl.ecommerce.repository.Cart_ItemsRepository;
import com.nl.ecommerce.repository.CustomerRepository;
import com.nl.ecommerce.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class eCommerceTest {
    @Autowired
    private Cart_ItemsRepository cart_itemsRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private TestEntityManager entityManager;

    @Test
     public void testAddOneCartItem(){
        Customer cus1 = new Customer("Xiaoling", "Qi", "SinterklaasStreet", "111", "Afoort", "Nl", "xiaoling@mail.com", "06555");
        Customer cus2 = new Customer("Eric", "Doorn", "SinterklaasStreet", "111", "Afoort", "Nl", "xiaoling@mail.com", "06555");

        Product product = new Product("Cups", "acup", 50, 20.00);

        Cart_Items cart_items1 = new Cart_Items();
        Cart_Items cart_items2 = new Cart_Items();

        cart_items1.setCustomer(cus1);
        cart_items1.setProduct(product);
        cart_items1.setQuantity(10);

        cart_items2.setCustomer(cus2);
        cart_items2.setProduct(product);
        cart_items2.setQuantity(20);

        Cart_Items savedCart_Item1 = cart_itemsRepository.save(cart_items1);
        Cart_Items savedCart_Item2 = cart_itemsRepository.save(cart_items2);

        assertTrue(savedCart_Item1.getId()>0);


    }
    @Test
    public void testGetCartItemsByCustomer(){
        Customer cus1 = new Customer("Xiaoling", "Qi", "SinterklaasStreet", "111", "Afoort", "Nl", "xiaoling@mail.com", "06555");
        Customer cus2 = new Customer("Eric", "Doorn", "SinterklaasStreet", "111", "Afoort", "Nl", "xiaoling@mail.com", "06555");

        Product product = new Product("Cups", "acup", 50, 20.00);

        Cart_Items cart_items1 = new Cart_Items();
        Cart_Items cart_items2 = new Cart_Items();

        cart_items1.setCustomer(cus1);
        cart_items1.setProduct(product);
        cart_items1.setQuantity(10);

        cart_items2.setCustomer(cus2);
        cart_items2.setProduct(product);
        cart_items2.setQuantity(20);

        cart_itemsRepository.save(cart_items1);
        cart_itemsRepository.save(cart_items2);

        List<Cart_Items> possibleCart_Items = cart_itemsRepository.findByCustomer(cus2);
        assertTrue(possibleCart_Items.size() ==1 );
    }

    @Test
    public void testFindByCustomerAndProduct() {

        Customer cus1 = new Customer("Xiaoling", "Qi", "SinterklaasStreet", "111", "Afoort", "Nl", "xiaoling@mail.com", "06555");
        Customer cus2 = new Customer("Eric", "Doorn", "SinterklaasStreet", "111", "Afoort", "Nl", "xiaoling@mail.com", "06555");

        Product product = new Product("Cups", "acup", 50, 20.00);

        Cart_Items cart_items1 = new Cart_Items();
        Cart_Items cart_items2 = new Cart_Items();

        cart_items1.setCustomer(cus1);
        cart_items1.setProduct(product);
        cart_items1.setQuantity(10);

        cart_items2.setCustomer(cus2);
        cart_items2.setProduct(product);
        cart_items2.setQuantity(20);

        cart_itemsRepository.save(cart_items1);
        cart_itemsRepository.save(cart_items2);

        Cart_Items possibleCart_Item = cart_itemsRepository.findByCustomerAndProduct(cus1, product);

        assertTrue(possibleCart_Item.getId() == 1);


    }
//    @Test
//    public void testAddProductToCategory(){
//        Product product1 = new Product("cups_blue", "acup", 50, 20.00);
//        Product product2 = new Product("cups_pink", "acup", 50, 20.00);
//        Category category = new Category("allCups");
//
//       productService.addNewProductToCategory(category.getName(), product1);
//       productService.addNewProductToCategory(category.getName(), product2);
//       assertTrue("allCups".equals( product1.getCategory().toString()));
//
//
//
//    }


}
