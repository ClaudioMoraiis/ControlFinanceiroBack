package com.controleFinanceiro.ControleFinanceiro.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.ControleFinanceiro.VO.ContasVO;
import com.controleFinanceiro.ControleFinanceiro.repositories.ContasRepository;

@Service
public class ContasService {
	@Autowired
	private ContasRepository repository;
	
	
	public ResponseEntity<?> insert(ContasVO mContasVO){
		if (mContasVO == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao inserir");					
		}
		
		repository.save(mContasVO);
		return ResponseEntity.status(HttpStatus.CREATED).body("Conta criada com sucesso");		
	}

}
