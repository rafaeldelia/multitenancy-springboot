package com.amran.dynamic.multitenant.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arlepton.apis.framework.controller.LeptonUserController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Manage Users")
public class MsfinnerUserController extends LeptonUserController {

	
}
