package com.finner.multitenant.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO implements Serializable {

	private static final long serialVersionUID = 950220868224271648L;
	private String userName;
	private String password;
	private Integer tenantOrClientId;
}