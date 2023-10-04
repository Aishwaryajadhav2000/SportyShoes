package com.sportyshoes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.Products;
import com.sportyshoes.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	public List<Products> getAllProduct(){
		
		return productRepo.findAll();
	}
	
	public void addProduct(Products product)
	{
		productRepo.save(product);
	}
	
	public void removeProductById(long id)
	{
		productRepo.deleteById(id);
	}
	
	public Optional<Products> getProductById(long id)
	{
		return productRepo.findById(id);
	}
	
	public List<Products> getAllProductByCategoryId(int id)
	{
		return productRepo.findAllByCategory_Id(id);
	}
	
	
}

