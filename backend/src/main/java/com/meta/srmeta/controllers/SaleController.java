package com.meta.srmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meta.srmeta.entities.Sale;
import com.meta.srmeta.services.SaleService;

@RestController
@RequestMapping(value="/sales")
public class SaleController {
	
	@Autowired
	public SaleService service;
	
	@GetMapping
	public List<Sale> findSales(){
		return service.fingSales();
	}
}
