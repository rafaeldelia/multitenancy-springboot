package com.amran.dynamic.multitenant.security;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.amran.dynamic.multitenant.mastertenant.config.DBContextHolder;
import com.amran.dynamic.multitenant.mastertenant.service.MasterTenantService;

/**
 * @author Md. Amran Hossain
 */
@Aspect
@Component
public class RequestAuthorizationIntercept {

	private static final String EMAIL_ADMIN_ARLEPTON = "arlepton@arlepton.com";

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	MasterTenantService masterTenantService;

	@Around("@annotation(com.amran.dynamic.multitenant.security.RequestAuthorization)")
	public Object checkPermission(ProceedingJoinPoint pjp) throws Throwable {
		UserTenantInformation tenantInformation = applicationContext.getBean(UserTenantInformation.class);
		Map<String, String> map = tenantInformation.getMap();
		String tenantName = map.get(EMAIL_ADMIN_ARLEPTON);
		if (tenantName != null && tenantName.equals(DBContextHolder.getCurrentDb())) {
			return pjp.proceed();
		}
		throw new RuntimeException("Access is Denied. Please again login or contact service provider");
	}
}
