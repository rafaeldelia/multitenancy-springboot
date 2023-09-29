package com.amran.dynamic.multitenant.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amran.dynamic.multitenant.tenant.entity.Product;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}