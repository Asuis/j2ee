package com.asuis.j2ee.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 15988440973
 */
@RestController
@RequestMapping("/saleman")
@PreAuthorize("hasRole('ROLE_SALE') or hasRole('ROLE_ADMIN')")
public class SaleManController {

}
