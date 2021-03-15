package com.sbcomplete.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.sbcomplete.application.model.Product;
import com.sbcomplete.application.repository.ProductsRepository;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	ProductsRepository repository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void should_store_a_product() {
		Product products = repository.save(new Product(1, "Iphone", "phone", 100000, "Available"));
		assertThat(products).hasFieldOrPropertyWithValue("id", 1);
		assertThat(products).hasFieldOrPropertyWithValue("productName", "Iphone");
		assertThat(products).hasFieldOrPropertyWithValue("description", "phone");
		assertThat(products).hasFieldOrPropertyWithValue("price", 100000);
		assertThat(products).hasFieldOrPropertyWithValue("availability", "Available");
	}

	@Test
	public void should_find_all_products() {
		Product product1 = new Product(1, "Iphone", "phone", 100000, "Available");
		entityManager.persist(product1);
		Product product2 = new Product(2, "Chair", "Chair", 20000, "Unavailable");
		entityManager.persist(product2);
		Product product3 = new Product(3, "Charger", "Laptop charger", 3000, "Available");
		entityManager.persist(product3);
		Iterable<Product> products = repository.findAll();
		assertThat(products).hasSize(3).contains(product1, product2, product3);
	}

	@Test
	public void should_find_available_products() {
		Product product1 = new Product(1, "Iphone", "phone", 100000, "Available");
		entityManager.persist(product1);
		Product product2 = new Product(2, "Chair", "Chair", 20000, "Available");
		entityManager.persist(product2);
		Product product3 = new Product(3, "Charger", "Laptop charger", 3000, "Unavailable");
		entityManager.persist(product3);

		Iterable<Product> products = repository.findByavailability("Available");

		assertThat(products).hasSize(2).contains(product1, product2);
	}

	@Test
	public void should_find_product_by_id() {
		Product product1 = new Product(1, "Iphone", "phone", 100000, "Available");
		entityManager.persist(product1);
		Product product2 = new Product(2, "Chair", "Chair", 20000, "Available");
		entityManager.persist(product2);
		Product product3 = new Product(3, "Charger", "Laptop charger", 3000, "Unavailable");
		entityManager.persist(product3);

		Product foundProduct = repository.findById(product3.getId()).get();

		assertThat(foundProduct).isEqualTo(product3);
	}

	@Test
	public void should_update_product_by_id() {
		Product product1 = new Product(1, "Iphone", "phone", 100000, "Available");
		entityManager.persist(product1);
		Product product2 = new Product(2, "Chair", "Chair", 20000, "Available");
		entityManager.persist(product2);
		Product product3 = new Product(3, "Charger", "Laptop charger", 3000, "Unavailable");
		entityManager.persist(product3);

		Product updatedProduct = new Product(2, "Computer chair", "Chair", 10000, "Available");

		Product product = repository.findById(product2.getId()).get();
		product.setProductName(updatedProduct.getProductName());
		product.setDescription(updatedProduct.getDescription());
		product.setPrice(updatedProduct.getPrice());
		product.setAvailability(updatedProduct.getAvailability());
		repository.save(product);

		Product checkProduct = repository.findById(product2.getId()).get();

		assertThat(checkProduct.getProductName()).isEqualTo(checkProduct.getProductName());
		assertThat(checkProduct.getDescription()).isEqualTo(checkProduct.getDescription());
		assertThat(checkProduct.getPrice()).isEqualTo(checkProduct.getPrice());
		assertThat(checkProduct.getAvailability()).isEqualTo(checkProduct.getAvailability());
	}

	@Test
	public void should_delete_product_by_id() {
		Product product1 = new Product(1, "Iphone", "phone", 100000, "Available");
		entityManager.persist(product1);
		Product product2 = new Product(2, "Chair", "Chair", 20000, "Available");
		entityManager.persist(product2);
		Product product3 = new Product(3, "Charger", "Laptop charger", 3000, "Unavailable");
		entityManager.persist(product3);

		repository.deleteById(product2.getId());

		Iterable<Product> products = repository.findAll();

		assertThat(products).hasSize(2).contains(product1, product3);
	}

	@Test
	public void should_delete_all_products() {
		Product product1 = new Product(1, "Iphone", "phone", 100000, "Available");
		entityManager.persist(product1);
		Product product2 = new Product(2, "Chair", "Chair", 20000, "Available");
		entityManager.persist(product2);
		Product product3 = new Product(3, "Charger", "Laptop charger", 3000, "Unavailable");
		entityManager.persist(product3);

		repository.deleteAll();

		assertThat(repository.findAll()).isEmpty();
	}


}