package com.amran.dynamic.multitenant.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arlepton.apis.framework.controller.LeptonRoleController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Tag(name = "Role", description = "Manage roles operations")
@RestController
@RequestMapping("/role")
@Data
@EqualsAndHashCode(callSuper = false)
public class MsfinnerRoleController extends LeptonRoleController {

}
