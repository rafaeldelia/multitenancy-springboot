package com.amran.dynamic.multitenant.mastertenant.entity;

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
@Table(name = "tbl_tenant_master")
@NoArgsConstructor
@AllArgsConstructor
public class MasterTenant implements Serializable {

	private static final long serialVersionUID = -7865668921322115275L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tenant_client_id")
	private Integer tenantClientId;

	@Size(max = 50)
	@Column(name = "db_name", nullable = false)
	private String dbName;

	@Size(max = 100)
	@Column(name = "url", nullable = false)
	private String url;

	@Size(max = 50)
	@Column(name = "user_name", nullable = false)
	private String userName;
	@Size(max = 100)
	@Column(name = "password", nullable = false)
	private String password;
	@Size(max = 100)
	@Column(name = "driver_class", nullable = false)
	private String driverClass;
	@Size(max = 10)
	@Column(name = "status", nullable = false)
	private String status;
}