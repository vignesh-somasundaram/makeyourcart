package com.sbcomplete.application.controller;

import java.util.List;
import java.util.Optional;

import com.sbcomplete.application.service.IProductService;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.sbcomplete.application.model.Product;
import com.sbcomplete.application.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("id")
public class ProductController {
	
	@Autowired
	private ProductService service;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String homePage() {
		return "welcomepage";
	}
	
	@RequestMapping(value="/addproducts", method = RequestMethod.GET)
	public String addProducts(ModelMap model) {
		model.addAttribute("addproductactive","active");
		return "home";
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String saveProducts(Product product) {
		service.saveproduct(product);
		return "productstatus";
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView displayProducts() {
		List<Product>products = service.productsList();
		ModelAndView mv = new ModelAndView("products");
		mv.addObject("products", products);
		mv.addObject("viewproductsactive","active");
		return mv;
	}
	
	@RequestMapping(value="/findprodbyId", method = RequestMethod.GET)
	public String findprodById(ModelMap model) {
		model.addAttribute("searchproductactive","active");
		return "productsbyId";
	}
	
	@RequestMapping(value="/findprodbyId", method = RequestMethod.POST)
	public ModelAndView findprodbyId(@RequestParam(name="id") int id, ModelMap model) {
		ModelAndView mv = new ModelAndView("productsbyId");
		try {
			List<Product> product = service.getProductById(id);
			mv.addObject("products", product);
			mv.addObject("visibility","hidden");
		}catch (Exception e) {
			mv.addObject("errorMessage", "Product with given ID is not present!");
			mv.addObject("visibility","visible");
		}
		return mv;
	}
	
	@RequestMapping(value = "/deleteprodbyId", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		service.deleteproduct(id);
		return "redirect:/products";
	}
	
	@RequestMapping(value = "/updateprod", method = RequestMethod.GET)
	public String showUpdatePage(@RequestParam int id, ModelMap model) {
		Product product = service.getProductByid(id);
		model.addAttribute("product",product);
		return "updateprod";
	}

	@RequestMapping(value = "/updateprod", method = RequestMethod.POST)
	public String showProductPage(Product product) {
		service.saveproduct(product);
		return "redirect:/products";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
						 HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	@Autowired
	private IProductService productService;

	@RequestMapping(value="/productspage", method = RequestMethod.GET)
	public String getPaginatedProducts(){
		return "paginatedproducts";
	}

	@RequestMapping(value="/productspage", method = RequestMethod.POST)
	public ModelAndView getPaginatedProducts(@RequestParam(name="pageNo") int pageNo,
											 @RequestParam(name="pageSize") int pageSize,
											 @RequestParam(name="sortBy") String sortBy){
		ModelAndView mv = new ModelAndView("paginatedproducts");
		try {
			List<Product> product = productService.findPaginated(pageNo, pageSize, Optional.of(sortBy));
			mv.addObject("products", product);
			//System.out.println(product);
		}catch (Exception e){
			mv.addObject("errorMessage", "Page number should be greater than 0");
			mv.addObject("visibility","visible");
			System.out.println(e);
		}
		return mv;
	}

	@RequestMapping(value="/searchbyname", method = RequestMethod.GET)
	public String findprodByName(ModelMap model) {
		model.addAttribute("searchproductactive","active");
		return "productsbyName";
	}

	@RequestMapping(value="/searchbyname", method = RequestMethod.POST)
	public ModelAndView findprodbyName(@RequestParam(name="productName") Optional<String> productName, ModelMap model) {
		ModelAndView mv = new ModelAndView("productsbyName");
		try {
			List<Product> product = service.findbyname(productName);
			mv.addObject("products", product);
			System.out.println(product);
			mv.addObject("visibility","hidden");
		}catch (Exception e) {
			mv.addObject("errorMessage", "Product with given name is not present!");
			mv.addObject("visibility","visible");
		}
		return mv;
	}
}
