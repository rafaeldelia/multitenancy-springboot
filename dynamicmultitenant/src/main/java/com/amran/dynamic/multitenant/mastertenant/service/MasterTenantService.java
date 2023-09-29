package com.amran.dynamic.multitenant.mastertenant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amran.dynamic.multitenant.mastertenant.entity.MasterTenant;
import com.amran.dynamic.multitenant.mastertenant.repository.MasterTenantRepository;

/**
 * @author Md. Amran Hossain
 */
@Service
public class MasterTenantService {

	@Autowired
	MasterTenantRepository masterTenantRepository;

	public MasterTenant findByClientId(Integer clientId) {
		return masterTenantRepository.findByTenantClientId(clientId);
	}
}
