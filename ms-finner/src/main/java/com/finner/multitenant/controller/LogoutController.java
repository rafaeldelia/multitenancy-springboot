package com.finner.multitenant.controller;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finner.multitenant.security.UserTenantInformation;

/**
 * @author Md. Amran Hossain
 */
@RestController
@RequestMapping("/api/product/logout")
public class LogoutController implements Serializable {

	private static final long serialVersionUID = -2067197894891524486L;

	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);

	@Autowired
	ApplicationContext applicationContext;

	@GetMapping("/logout")
	public ResponseEntity<?> logoutFromApp(Principal principal) {
		LOGGER.info("AuthenticationController::logoutFromApp() method call..");
		UserTenantInformation userCharityInfo = applicationContext.getBean(UserTenantInformation.class);
		Map<String, String> map = userCharityInfo.getMap();
		map.remove(principal.getName());
		userCharityInfo.setMap(map);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
