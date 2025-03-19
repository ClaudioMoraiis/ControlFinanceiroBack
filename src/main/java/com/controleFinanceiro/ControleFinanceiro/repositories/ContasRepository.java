package com.controleFinanceiro.ControleFinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controleFinanceiro.ControleFinanceiro.vo.ContasVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContasRepository extends JpaRepository<ContasVO, Long> {
    @Query("SELECT c FROM ContasVO c WHERE c.usuario.usu_id = :idUsuario")
    List<ContasVO> listarContas(@Param("idUsuario") Integer idUsuario);

}
