package com.controleFinanceiro.ControleFinanceiro.services;

import com.controleFinanceiro.ControleFinanceiro.dto.ContaTotalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.ControleFinanceiro.dto.ContasDTO;
import com.controleFinanceiro.ControleFinanceiro.vo.ContasVO;
import com.controleFinanceiro.ControleFinanceiro.vo.UsuarioVO;
import com.controleFinanceiro.ControleFinanceiro.repositories.ContasRepository;
import com.controleFinanceiro.ControleFinanceiro.repositories.UsuarioRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	public List<ContasDTO> listarContasPorUsuario(Integer idUsuario, Boolean detalhado){
		List<ContasDTO> listContaDTO = new ArrayList<>();
		listContaDTO = repository.listarContasPorUsuario(idUsuario);

		BigDecimal valorMensal = BigDecimal.ZERO;
		BigDecimal valorExtra = BigDecimal.ZERO;

		for(ContasDTO contasDTO : listContaDTO ){
			if(contasDTO.getTipo().equals("MENSAL")){
                valorMensal = valorMensal.add(contasDTO.getValor());
			}else {
				valorExtra = valorExtra.add(contasDTO.getValor());
			}
		}

		ContasDTO totais = new ContasDTO();
		totais.setTipo("TOTAIS");

		Map<String, BigDecimal> totaisMap = Map.of("MENSAL", valorMensal, "EXTRA", valorExtra);

		listContaDTO.add(new ContaTotalDTO(totaisMap));
		return listContaDTO;
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

	public List<ContasDTO> buscarContasVencendo() {
		List<UsuarioVO> listaUsuario = usuarioRepository.findAll();
		List<ContasDTO> result = new ArrayList<>();
		LocalDate data = LocalDate.now();

		for (UsuarioVO usuarioVO : listaUsuario) {
			List<ContasDTO> list = repository.listarContasPorUsuario(Math.toIntExact(usuarioVO.getUsu_id()));

			if (list != null) {
				LocalDate dataInicial = LocalDate.now().minusDays(1);
				LocalDate dataFinal = dataInicial.plusDays(5);
				List<ContasDTO> filteredList = list.stream()
						.filter(contasDTO ->
								contasDTO.getData().isAfter(dataInicial) &&
								contasDTO.getData().isBefore(dataFinal) &&
								contasDTO.getTipo().equals("MENSAL"))
						.collect(Collectors.toList());

				result.addAll(filteredList);
			}
		}

		return result;

	}
}
