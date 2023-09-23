package com.finner.multitenant.mastertenant.service;

import com.finner.multitenant.mastertenant.entity.MasterTenant;

/**
 * @author Md. Amran Hossain
 */
public interface MasterTenantService {

	MasterTenant findByClientId(Integer clientId);
}
