package com.controleFinanceiro.ControleFinanceiro.repositories;

import com.controleFinanceiro.ControleFinanceiro.dto.ContasDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.controleFinanceiro.ControleFinanceiro.vo.ContasVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContasRepository extends JpaRepository<ContasVO, Long> {
    @Query("SELECT new com.controleFinanceiro.ControleFinanceiro.dto.ContasDTO(c.con_id, c.con_nome, c.con_valor, " +
            "c.con_tipo, c.usuario.usu_id, c.con_data) " +
            "FROM ContasVO c JOIN c.usuario u WHERE u.usu_id = :idUsuario")
    List<ContasDTO> listarContas(@Param("idUsuario") Integer idUsuario);

}
