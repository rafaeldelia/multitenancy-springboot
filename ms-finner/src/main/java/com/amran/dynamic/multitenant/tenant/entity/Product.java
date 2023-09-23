package com.amran.dynamic.multitenant.tenant.entity;

import java.io.Serializable;

import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Md. Amran Hossain
 */
@Data
@Builder
@Entity
@Table(name = "tbl_product")
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

	private static final long serialVersionUID = 1439825489968200542L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;
	@Size(max = 50)
	@Column(name = "product_name", nullable = false)
	private String productName;
	@Size(max = 10)
	@Column(name = "quantity", nullable = false)
	private String quantity;
	@Size(max = 3)
	@Column(name = "size", nullable = false, unique = true)
	private String size;
}