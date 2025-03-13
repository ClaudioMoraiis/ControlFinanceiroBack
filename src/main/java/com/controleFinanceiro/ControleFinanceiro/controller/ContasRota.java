package com.controleFinanceiro.ControleFinanceiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controleFinanceiro.ControleFinanceiro.dto.ContasDTO;
import com.controleFinanceiro.ControleFinanceiro.services.ContasService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/contas")
public class ContasRota {
	@Autowired
	private ContasService service;	
	
	@PostMapping(value = "/cadastrar")
	public ResponseEntity<?> insert(@Valid @RequestBody(required = true) ContasDTO mContaDTO){
		return service.insert(mContaDTO);
	}	
	
	

}
