package com.amran.dynamic.multitenant.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arlepton.apis.framework.controller.LeptonCredentialController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Credentials", description = "Manage User Credentials")
@RequestMapping("/credential")
public class MsfinnerCredentialController extends LeptonCredentialController {

}
