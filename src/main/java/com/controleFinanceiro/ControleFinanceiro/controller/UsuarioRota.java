package com.controleFinanceiro.ControleFinanceiro.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controleFinanceiro.ControleFinanceiro.dto.UsuarioLoginDTO;
import com.controleFinanceiro.ControleFinanceiro.services.EmailService;
import com.controleFinanceiro.ControleFinanceiro.services.UsuarioService;
import com.controleFinanceiro.ControleFinanceiro.vo.UsuarioVO;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/usuario")
public class UsuarioRota {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private EmailService emailService;

	@PostMapping(value = "/cadastrar")
	public ResponseEntity<?> insertUser(@Valid @RequestBody(required = true) UsuarioVO mUsuario) throws NoSuchAlgorithmException {
		return service.insertUser(mUsuario);		
	}

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
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@Valid @RequestBody UsuarioLoginDTO loginDTO){
		return service.login(loginDTO);
	}
	
	@PostMapping("recuperar-senha")
	public ResponseEntity<?> recuperarSenha(@RequestParam String email) throws MessagingException {		
		return emailService.enviarEmailRecuperacaoSenha(email);	
	}
	
	@PutMapping("/redefinir-senha")
	public ResponseEntity<?> redefinirSenha(@RequestBody Map<String, String> request) {
	    return emailService.redefinirSenha(request);
	}
	
	
	@GetMapping("/BuscarUsuarioPorEmail")
	public ResponseEntity<?> findUserByEmail(@RequestParam String email){
		return emailService.findUserByEmail(email);		
	}

	
	

};