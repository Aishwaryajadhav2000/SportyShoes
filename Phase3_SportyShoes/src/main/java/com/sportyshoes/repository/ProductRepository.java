package com.sportyshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.model.Products;

public interface ProductRepository extends JpaRepository<Products, Long>{

	
	List<Products> findAllByCategory_Id(int id);
}