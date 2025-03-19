package com.controleFinanceiro.ControleFinanceiro.services;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.ControleFinanceiro.dto.UsuarioLoginDTO;
import com.controleFinanceiro.ControleFinanceiro.vo.UsuarioVO;
import com.controleFinanceiro.ControleFinanceiro.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;	

	    	
	public ResponseEntity<?> insertUser(UsuarioVO mUsuarioVO) throws NoSuchAlgorithmException {		
		
		if (mUsuarioVO.getUsu_name() == null || mUsuarioVO.getUsu_name().isBlank()) {
			ResponseEntity.ok("Campo ''nome'' é obrigatório");			
		}
		
		if (mUsuarioVO.getUsu_email() == null || mUsuarioVO.getUsu_email().isBlank()) {
			ResponseEntity.ok("Campo ''email'' é obrigatório");
		}
		
		if (mUsuarioVO.getUsu_password() == null || mUsuarioVO.getUsu_password().isBlank()) {
			ResponseEntity.ok("Campo ''senha'' é obrigatório");
		}	
		
		if (repository.findByEmail(mUsuarioVO.getUsu_email()) != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já cadastrado.");
		}		
		
		repository.save(mUsuarioVO);
		return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
	}
	
	
	public UsuarioVO findByName(String name) {
		return repository.findByUsuName(name);
	}

	public UsuarioVO findByEmail(String email) {
		return repository.findByEmail(email);		
	}

	public ResponseEntity<?> login(UsuarioLoginDTO loginDTO) {
		UsuarioVO usuarioVO = repository.findByEmail(loginDTO.getUsu_email());
		
		if(usuarioVO == null) {
			return ResponseEntity.ok("E-mail ou senha inválida");
		}
		
		if (usuarioVO.getUsu_password().equals(loginDTO.getUsu_password())) {
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.ok("E-mail ou senha inválida");
		}
	}

	public ResponseEntity<?> getIdByEmail(String email){
		Integer usuarioId = repository.getIdByEmail(email);
		if ((usuarioId == null) || (usuarioId == 0)){
			ResponseEntity.ok("Nenhum usuário localizado com esse email");
		}

		return ResponseEntity.status(HttpStatus.OK).body(usuarioId);
	}

	public ResponseEntity<?> findUserByEmail(String email){
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO = repository.findByEmail(email);

		return ResponseEntity.ok(usuarioVO.getUsu_name());
	}

}
