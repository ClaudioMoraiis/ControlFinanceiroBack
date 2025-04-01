package com.controleFinanceiro.ControleFinanceiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/listarContasPorUsuario")
	public ResponseEntity<?> listarContasPorUsuario(@RequestParam Integer idUsuario, @RequestParam(required = false) Boolean detalhado){
		return ResponseEntity.ok(service.listarContasPorUsuario(idUsuario, detalhado));
	}

	@DeleteMapping("/deletar")
	public ResponseEntity<?> deletar(@RequestParam Long id){
		return ResponseEntity.ok(service.deletar(id));
	}

	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@Valid @RequestBody ContasDTO contasDTO){
		return service.alterar(contasDTO);
	}
}
