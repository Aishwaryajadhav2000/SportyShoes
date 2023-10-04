package com.sportyshoes.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sportyshoes.dto.productDTO;
import com.sportyshoes.model.Category;
import com.sportyshoes.model.Products;

import com.sportyshoes.service.CategoryService;
import com.sportyshoes.service.ProductService;


import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
		
	public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/images";
	
	@Autowired
	private CategoryService categoryservice;
	
	@Autowired
	private ProductService productService;
		
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories",categoryservice.getAllCategory());
		return "Categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
				
		model.addAttribute("category",new Category());
		return "CategoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute ("category") Category category) 
	{
		categoryservice.addCategory(category);
		return "redirect:/admin/categories";		
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
		categoryservice.removeCategoryById(id);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id, Model model) 
	{
	 Optional<Category> category = categoryservice.getCategoryById(id);	
	 
	 if(category.isPresent())
	 {
		 model.addAttribute("category", category.get());
		 return "CategoriesAdd";
	 }else {
		 return "404";
	 }
	 }
	
	@GetMapping("/admin/products")
	public String Product(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "Products";
	}
	
	
	@GetMapping("/admin/products/add")
	public String ProdAdd(Model model) {
				
		model.addAttribute("productDTO",new productDTO());
		model.addAttribute("categories", categoryservice.getAllCategory());
		return "ProductsAdd";
	}

	@PostMapping("/admin/products/add")
	public String productAdd(@ModelAttribute ("ProductDTO") productDTO ProductDTO, @RequestParam("productImage") MultipartFile file, 
			@RequestParam("imgName") String imgName) throws IOException 
	{
		Products product = new Products();
		product.setId(ProductDTO.getId());
		product.setName(ProductDTO.getName());
		product.setCategory(categoryservice.getCategoryById(ProductDTO.getCategoryId()).get());
		product.setPrice(ProductDTO.getPrice());
		product.setWeight(ProductDTO.getWeight());
		product.setDescription(ProductDTO.getDescription());
		
		String imageUUID;
		if(!file.isEmpty()) 
		{
			imageUUID = file.getOriginalFilename();
		    Path fileNameAndPath = Paths.get(uploadDir, imageUUID);	
		    Files.write(fileNameAndPath, file.getBytes());
		}else {
			imageUUID = imgName;
		}
		
		product.setImageName(imageUUID);
		productService.addProduct(product);
		
		return "redirect:/admin/products";		
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id)
	{
		productService.removeProductById(id);
		return "redirect:/admin/products";
	}
	
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProductGet(@PathVariable long id, Model model)
	{
	Products product = productService.getProductById(id).get();
	productDTO ProductDTO = new productDTO();
	ProductDTO.setId(product.getId());
	ProductDTO.setName(product.getName());
	ProductDTO.setCategoryId(product.getCategory().getId() );
	ProductDTO.setPrice(product.getPrice());
	ProductDTO.setWeight(product.getWeight());
	ProductDTO.setDescription(product.getDescription());
	ProductDTO.setImageName(product.getImageName());
	
	model.addAttribute("categories", categoryservice.getAllCategory());
	model.addAttribute("productDTO", ProductDTO);
	
	
		return "ProductsAdd";
	}
	
//	@GetMapping("/admin/product/cart/{id}")
//	public String addtocart(@PathVariable long id, Model model)
//	{
//		return "Cart";
//	}
	
	

	
	
}

