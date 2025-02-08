package com.controleFinanceiro.ControleFinanceiro.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.controleFinanceiro.ControleFinanceiro.VO.UsuarioVO;
import com.controleFinanceiro.ControleFinanceiro.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public void insertUser(UsuarioVO mUsuarioVO) {		
		repository.save(mUsuarioVO);
	}
	
	
	public UsuarioVO findByName(String name) {
		return repository.findByUsuName(name);
	}

	public UsuarioVO findByEmail(String email) {
		return repository.findByEmail(email);		
	}
}
