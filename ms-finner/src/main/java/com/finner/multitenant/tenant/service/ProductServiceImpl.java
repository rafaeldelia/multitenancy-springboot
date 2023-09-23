package com.finner.multitenant.tenant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finner.multitenant.tenant.entity.Product;
import com.finner.multitenant.tenant.repository.ProductRepository;

/**
 * @author Md. Amran Hossain
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}
}
