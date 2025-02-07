package com.controleFinanceiro.ControleFinanceiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controleFinanceiro.ControleFinanceiro.Services.UsuarioService;
import com.controleFinanceiro.ControleFinanceiro.VO.UsuarioVO;

@RestController
@RequestMapping("/usuario")
public class UsuarioRota {
	
	@Autowired
	private UsuarioService service;		

	
    @PostMapping(value = "/cadastrar")
	public ResponseEntity<String> insertUser(@RequestBody UsuarioVO mUsuario) {
    	service.insertUser(mUsuario);
		return ResponseEntity.ok().body("Usuario inserido");		
	}
    
    
    @GetMapping(value = "/{name}")
    public ResponseEntity<Object> findByName(@PathVariable String name) {
        UsuarioVO mobj = service.findByName(name);
        return ResponseEntity.ok().body(mobj);
    }

}
;