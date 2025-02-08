package com.controleFinanceiro.ControleFinanceiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controleFinanceiro.ControleFinanceiro.Services.UsuarioService;
import com.controleFinanceiro.ControleFinanceiro.VO.UsuarioVO;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@Validated
@RestController
@RequestMapping("/usuario")
public class UsuarioRota {

	@Autowired
	private UsuarioService service;

	@PostMapping(value = "/cadastrar")
	public ResponseEntity<String> insertUser(@Valid @RequestBody(required = true) UsuarioVO mUsuario) {
		
		if ((mUsuario == null) && (mUsuario.getUsu_name() == null)) {
			ResponseEntity.ok("Campo ''nome'' é obrigatório");			
		}
		
		if ((mUsuario == null) && (mUsuario.getUsu_email() == null)) {
			ResponseEntity.ok("Campo ''email'' é obrigatório");
		}
		
		if ((mUsuario == null) && (mUsuario.getUsu_password() == null)) {
			ResponseEntity.ok("Campo ''senha'' é obrigatório");
		}
		
		service.insertUser(mUsuario);
		return ResponseEntity.ok().body("Usuario inserido");
	}

	@SuppressWarnings("unused")
	@GetMapping(value = "/nome")
	public ResponseEntity<?> findByName(@RequestParam(value = "nome", required = false) String name) {
		UsuarioVO mobj = service.findByName(name.toUpperCase());
		
		if (name == null) {
			return ResponseEntity.ok("Parâmetro nome não informado");
		} else if (name != null && (mobj == null)) {
			return ResponseEntity.ok("Nenhum resultado encontrado");
		} else {
			return ResponseEntity.ok(mobj);
		}
	}

	@SuppressWarnings("unused")
	@GetMapping(value = "/email")
	public ResponseEntity<?> getEmail(@RequestParam(value = "email", required = false) String email) {
		UsuarioVO mUsuarioVO = service.findByEmail(email.toUpperCase());
		
		if (email == null) {
			return ResponseEntity.ok("Parâmetro de email não informado");
		} else if (email != null && (mUsuarioVO == null)) {
			return ResponseEntity.ok("Nenhum resultado encontrado");
		} else {
			return ResponseEntity.ok(mUsuarioVO);
		}
	}

};