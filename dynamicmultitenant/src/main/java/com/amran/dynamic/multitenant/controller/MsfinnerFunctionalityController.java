package com.amran.dynamic.multitenant.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arlepton.apis.framework.controller.LeptonFunctionalityController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Tag(name = "Functionality", description = "Manage functionalities operations")
@RestController
@RequestMapping("/functionality")
@Data
@EqualsAndHashCode(callSuper = false)
public class MsfinnerFunctionalityController extends LeptonFunctionalityController {

}
