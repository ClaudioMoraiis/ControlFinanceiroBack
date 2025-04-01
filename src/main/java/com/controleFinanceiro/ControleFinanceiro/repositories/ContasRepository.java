package com.controleFinanceiro.ControleFinanceiro.repositories;

import com.controleFinanceiro.ControleFinanceiro.dto.ContasDTO;
import com.controleFinanceiro.ControleFinanceiro.vo.UsuarioVO;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;

import com.controleFinanceiro.ControleFinanceiro.vo.ContasVO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface ContasRepository extends JpaRepository<ContasVO, Long> {
    @Query("SELECT new com.controleFinanceiro.ControleFinanceiro.dto.ContasDTO(c.con_id, c.con_nome, c.con_valor, " +
           "c.con_tipo, c.usuario.usu_id, c.con_data) " +
           "FROM ContasVO c INNER JOIN c.usuario u WHERE u.usu_id = :idUsuario")
    List<ContasDTO> listarContasPorUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT c FROM ContasVO c WHERE c.con_id = :id")
    ContasVO buscarPorid(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM ContasVO c WHERE c.con_data < :data")
    void excluirRegistrosVencidos(@Param("data")LocalDate data);
}
