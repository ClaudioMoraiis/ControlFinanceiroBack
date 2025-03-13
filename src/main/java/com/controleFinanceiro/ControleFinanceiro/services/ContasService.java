package com.controleFinanceiro.ControleFinanceiro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.ControleFinanceiro.dto.ContasDTO;
import com.controleFinanceiro.ControleFinanceiro.vo.ContasVO;
import com.controleFinanceiro.ControleFinanceiro.vo.UsuarioVO;
import com.controleFinanceiro.ControleFinanceiro.repositories.ContasRepository;
import com.controleFinanceiro.ControleFinanceiro.repositories.UsuarioRepository;

@Service
public class ContasService {
	@Autowired
	private ContasRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;	
	
	
	public ResponseEntity<?> insert(ContasDTO mContasDTO){
		UsuarioVO mUsuarioVO = usuarioRepository.findById(mContasDTO.getUsuarioID())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		
		ContasVO mContasVO = new ContasVO();
		mContasVO.setCon_nome(mContasDTO.getNomeConta());
		mContasVO.setCon_tipo(mContasDTO.getTipo());
		mContasVO.setCon_valor(mContasDTO.getValor());
		mContasVO.setUsuario(mUsuarioVO);
		repository.save(mContasVO);	
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso");		
	}

}
