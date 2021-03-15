package com.sbcomplete.application.restcontroller;

import com.sbcomplete.application.model.Product;
import com.sbcomplete.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/v1")
public class RestController {
    @Autowired
    private ProductService service;

    @GetMapping(value = "/api/productList")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts(){
        return service.productsList();
    }

    @GetMapping(value = "/api/products")
    @ResponseStatus(HttpStatus.FOUND)
    public Product getProductById(@RequestParam int id){
        try {
            return service.getProductByid(id);
        }catch (Exception e){
            return new Product();
        }
    }

    @GetMapping("/api/products/name")
    @ResponseStatus(HttpStatus.FOUND)
    public Object getProductByName(@RequestParam String productName){
            return service.findprodbyname(productName);
    }

    @GetMapping("/api/products/delete/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public Object deleteProductById(@PathVariable int id){
        try {
            service.deleteproduct(id);
            return service.productsList();
        }catch(Exception e){
            return service.productsList();
        }
    }

}
