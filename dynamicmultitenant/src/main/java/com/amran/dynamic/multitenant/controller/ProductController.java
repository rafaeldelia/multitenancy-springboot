package com.amran.dynamic.multitenant.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import com.amran.dynamic.multitenant.mastertenant.config.DBContextHolder;
import com.amran.dynamic.multitenant.mastertenant.entity.MasterTenant;
import com.amran.dynamic.multitenant.mastertenant.service.MasterTenantService;
import com.amran.dynamic.multitenant.security.UserTenantInformation;
import com.amran.dynamic.multitenant.tenant.service.ProductService;
import com.arlepton.apis.framework.annotation.ARLeptonRoles;
import com.arlepton.apis.framework.constants.ConstantsFunctionality;

/**
 * @author Md. Amran Hossain | amrancse930@gmail.com
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

	private Map<String, String> mapValue = new HashMap<>();
	private Map<String, String> userDbMap = new HashMap<>();

	@Autowired
	MasterTenantService masterTenantService;

	@Autowired
	private ProductService productService;

	//@ARLeptonRoles(functionality = ConstantsFunctionality.NAME_GET_USERS)
	@GetMapping("/all")
	public ResponseEntity<Object> getAllProduct(@RequestHeader(value = "apiKey", required = true) String apiKey,
			@RequestParam(value = "lang", required = false) String lang) {
		
		// receber o apiKey e identificar o email.
		
		Integer intValue = Integer.valueOf(1);
		MasterTenant masterTenant = masterTenantService.findByClientId(intValue);
		loadCurrentDatabaseInstance(masterTenant.getDbName(), "rafael.delia@arphoenix.com.br");
		return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
	}

	//@ARLeptonRoles(functionality = ConstantsFunctionality.NAME_GET_USERS)
	@GetMapping("/allPageable")
	public ResponseEntity<Object> getAllProductPageable(@RequestHeader(value = "apiKey", required = true) String apiKey,
			@RequestParam(value = "lang", required = false) String lang) {
		
		// receber o apiKey e identificar o email.
		
		Integer intValue = Integer.valueOf(1);
		MasterTenant masterTenant = masterTenantService.findByClientId(intValue);
		loadCurrentDatabaseInstance(masterTenant.getDbName(), "rafael.delia@arphoenix.com.br");
		Pageable pageable = PageRequest.of(0, 10);
		return new ResponseEntity<>(productService.getAllProduct(pageable), HttpStatus.OK);
	}

	private void loadCurrentDatabaseInstance(String databaseName, String userName) {
		DBContextHolder.setCurrentDb(databaseName);
		mapValue.put(userName, databaseName);
	}

	@Bean(name = "userTenantInfo")
	@ApplicationScope
	public UserTenantInformation setMetaDataAfterLogin() {
		UserTenantInformation tenantInformation = new UserTenantInformation();
		if (mapValue.size() > 0) {
			for (String key : mapValue.keySet()) {
				if (null == userDbMap.get(key)) {
					userDbMap.putAll(mapValue);
				} else {
					userDbMap.put(key, mapValue.get(key));
				}
			}
			mapValue = new HashMap<>();
		}
		tenantInformation.setMap(userDbMap);
		return tenantInformation;
	}
}