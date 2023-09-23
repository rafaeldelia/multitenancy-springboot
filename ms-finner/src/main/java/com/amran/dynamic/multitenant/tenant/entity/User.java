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

@Data
@Builder
@Entity
@Table(name = "tbl_user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = -1566275994485051361L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Size(max = 100)
	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Size(max = 10)
	@Column(name = "gender", nullable = false)
	private String gender;

	@Size(max = 50)
	@Column(name = "user_name", nullable = false, unique = true)
	private String userName;
	@Size(max = 100)
	@Column(name = "password", nullable = false)
	private String password;
	@Size(max = 10)
	@Column(name = "status", nullable = false)
	private String status;
}