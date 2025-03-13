package com.controleFinanceiro.ControleFinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controleFinanceiro.ControleFinanceiro.VO.ContasVO;
import com.controleFinanceiro.ControleFinanceiro.VO.UsuarioVO;

public interface ContasRepository extends JpaRepository<ContasVO, Long> {
	

}
