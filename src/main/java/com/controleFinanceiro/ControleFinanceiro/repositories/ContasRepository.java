package com.controleFinanceiro.ControleFinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controleFinanceiro.ControleFinanceiro.vo.ContasVO;

public interface ContasRepository extends JpaRepository<ContasVO, Long> {
	

}
