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

import java.util.List;

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
		mContasVO.setCon_nome(mContasDTO.getNome());
		mContasVO.setCon_tipo(mContasDTO.getTipo());
		mContasVO.setCon_valor(mContasDTO.getValor());
		mContasVO.setCon_data(mContasDTO.getData());
		mContasVO.setUsuario(mUsuarioVO);
		repository.save(mContasVO);	
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Conta cadastrada com sucesso");
	}

	public List<ContasDTO> listarContas(Integer idUsuario){
		return repository.listarContas(idUsuario);
	}

	public ResponseEntity<?> deletar(Long id){
		ContasVO contasVO = new ContasVO();
		contasVO = repository.buscarPorid(id);

		if (contasVO == null){
			return ResponseEntity.ok("Nenhuma conta localiza com esse id");
		}

		repository.delete(contasVO);
		return ResponseEntity.status(HttpStatus.OK).body("Conta deletada com sucesso");
	}

	public ResponseEntity<?> alterar(ContasDTO contasDTO){
		ContasVO contasVO = repository.buscarPorid(contasDTO.getId());
		if (contasVO == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum conta localizada com esse id");
		}

		contasVO.setCon_data(contasDTO.getData());
		contasVO.setCon_nome(contasDTO.getNome());
		contasVO.setCon_tipo(contasDTO.getTipo());
		contasVO.setCon_valor(contasDTO.getValor());

		repository.save(contasVO);
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(contasVO));
	}
}
