package com.sbcomplete.application.restcontroller;

import com.sbcomplete.application.model.Product;
import com.sbcomplete.application.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class RestControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RestController restController;

    @Mock
    private ProductService productService;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(restController).build();
    }

    @Test
    public void getAllProducts() throws Exception {
        Product p = new Product();
        List<Product> product = new ArrayList<>();
        p.setId(1);
        p.setProductName("Iphone");
        p.setDescription("Phone");
        p.setPrice(10000);
        p.setAvailability("Available");
        product.add(p);
        String URI = "/v1/api/productList";
        Mockito.when(productService.productsList()).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get(URI)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].productName", Matchers.is("Iphone")))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].description", Matchers.is("Phone")))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].price", Matchers.is(10000)))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].availability", Matchers.is("Available")));

        Mockito.verify(productService).productsList();

    }

    @Test
    public void getProductById() throws Exception{
        Product product = new Product();
        product.setId(1);
        product.setProductName("Jaguar");
        product.setDescription("Car");
        product.setPrice(1000000);
        product.setAvailability("Available");
        String URI = "/v1/api/products?id=1";
        Mockito.when(productService.getProductByid(1)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get(URI)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName", Matchers.is("Jaguar")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("Car")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(1000000)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.availability", Matchers.is("Available")));

        Mockito.verify(productService).getProductByid(1);

    }

    @Test
    public void getProductsByName() throws Exception {
        Product p = new Product();
        List<Product> product = new ArrayList<>();
        p.setId(1);
        p.setProductName("Chair");
        p.setDescription("Comfortable chair");
        p.setPrice(8000);
        p.setAvailability("Unavailable");
        product.add(p);
        String URI = "/v1/api/products/name?productName=Chair";
        Mockito.when(productService.findprodbyname("Chair")).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get(URI)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].productName", Matchers.is("Chair")))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].description", Matchers.is("Comfortable chair")))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].price", Matchers.is(8000)))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].availability", Matchers.is("Unavailable")));

        Mockito.verify(productService).findprodbyname("Chair");

    }

//    @Test
//    public void deleteProduct() throws Exception{
//        Product p = new Product();
//        List<Product> product = new ArrayList<>();
//        p.setId(1);
//        p.setProductName("Chair");
//        p.setDescription("Comfortable chair");
//        p.setPrice(8000);
//        p.setAvailability("Unavailable");
//        product.add(p);
//        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8081/v1/api/products/delete/1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isGone());
//    }

}