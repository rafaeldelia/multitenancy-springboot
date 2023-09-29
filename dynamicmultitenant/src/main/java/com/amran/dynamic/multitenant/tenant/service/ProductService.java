package com.amran.dynamic.multitenant.tenant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amran.dynamic.multitenant.tenant.entity.Product;
import com.amran.dynamic.multitenant.tenant.repository.ProductRepository;

/**
 * @author Md. Amran Hossain
 */
@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	public Page<Product> getAllProduct(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
}
