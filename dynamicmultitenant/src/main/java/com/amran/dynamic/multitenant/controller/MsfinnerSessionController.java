package com.amran.dynamic.multitenant.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import com.amran.dynamic.multitenant.mastertenant.config.DBContextHolder;
import com.amran.dynamic.multitenant.mastertenant.entity.MasterTenant;
import com.amran.dynamic.multitenant.mastertenant.service.MasterTenantService;
import com.amran.dynamic.multitenant.security.UserTenantInformation;
import com.arlepton.apis.framework.controller.LeptonSessionController;
import com.arlepton.apis.framework.request.LeptonLoginRequest;
import com.arlepton.apis.framework.response.LoginResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/session")
@Tag(name = "Sessions", description = "Sessions for users - login logout")
public class MsfinnerSessionController extends LeptonSessionController {

	private Map<String, String> mapValue = new HashMap<>();
	private Map<String, String> userDbMap = new HashMap<>();

	@Autowired
	MasterTenantService masterTenantService;

	@Autowired
	ApplicationContext applicationContext;

	@Override
	@PostMapping(value = "/loginTenancy")
	public ResponseEntity<LoginResponse> doLogin(String lang, LeptonLoginRequest request) {
		Integer intValue = Integer.valueOf(1);
		MasterTenant masterTenant = masterTenantService.findByClientId(intValue);
		loadCurrentDatabaseInstance(masterTenant.getDbName(), request.getEmail());
		return super.doLogin(lang, request);
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logoutFromApp(Principal principal) {
		UserTenantInformation userCharityInfo = applicationContext.getBean(UserTenantInformation.class);
		Map<String, String> map = userCharityInfo.getMap();
		map.remove(principal.getName());
		userCharityInfo.setMap(map);
		return ResponseEntity.ok(HttpStatus.OK);
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