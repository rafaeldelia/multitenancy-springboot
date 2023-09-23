package com.finner.multitenant.mastertenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finner.multitenant.mastertenant.entity.MasterTenant;

/**
 * @author Md. Amran Hossain
 */
@Repository
public interface MasterTenantRepository extends JpaRepository<MasterTenant, Integer> {
	MasterTenant findByTenantClientId(Integer clientId);
}
