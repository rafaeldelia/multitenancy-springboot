package com.finner.multitenant.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finner.multitenant.tenant.entity.Product;

/**
 * @author Md. Amran Hossain
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
