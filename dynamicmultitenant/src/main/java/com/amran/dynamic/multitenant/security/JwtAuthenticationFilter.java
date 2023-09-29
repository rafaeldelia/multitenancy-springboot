package com.amran.dynamic.multitenant.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.amran.dynamic.multitenant.mastertenant.service.MasterTenantService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Md. Amran Hossain
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	MasterTenantService masterTenantService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
