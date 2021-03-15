package com.sbcomplete.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sbcomplete.application.model.Product;
import com.sbcomplete.application.repository.ProductsRepository;
@Service
public class ProductService implements IProductService {

	@Autowired
	private final ProductsRepository repo;

	public ProductService(ProductsRepository repo) {
		this.repo = repo;
	}

	public void saveproduct(Product product) {
		repo.save(product);
	}
	
	public List<Product> productsList(){
		List<Product> products = new ArrayList<Product>();
		repo.findAll().forEach(product -> products.add(product));
		return products;
	}
	
	public List<Product> getProductById(int id){
		List<Product> products = new ArrayList<>();
		Product prod = repo.findById(id).get();
		products.add(prod);
		System.out.println(products);
		return products;
	}

	public Product getProductByid(int id){
		Product product = repo.findById(id).get();
		System.out.println(product);
		return product;
	}
	
	public void updateproduct(Product product) {
		repo.save(product);
	}
	
	public void deleteproduct(int id) {
		repo.deleteById(id);
	}

	public void updateTodo(Product product) {
		// TODO Auto-generated method stub
		repo.delete(product);
		repo.save(product);
	}

	@Override
	public List<Product> findPaginated(int pageNo, int pageSize, Optional<String> name){
		Pageable paging = PageRequest.of(pageNo-1, pageSize, Sort.Direction.ASC,name.orElse("id"));
		Page<Product> pagedResult = repo.findAll(paging);
		return pagedResult.toList();
	}

	public List<Product> findbyname(Optional<String> name) {
		System.out.println("Product - "+repo.findByProductName(name.orElse("_")));
		return repo.findByProductName(name.orElse("_"));
	}

	public List<Product> findprodbyname(String prodName){
		return repo.findByProductName(prodName);
	}

	public List<Product> findbyAvail(String availablity){
		return repo.findByavailability(availablity);
	}
}
