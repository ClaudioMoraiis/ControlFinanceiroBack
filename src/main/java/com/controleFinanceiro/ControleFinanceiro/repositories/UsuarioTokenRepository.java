package com.controleFinanceiro.ControleFinanceiro.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.controleFinanceiro.ControleFinanceiro.VO.UsuarioToken;

public interface UsuarioTokenRepository extends JpaRepository<UsuarioToken, Long> {
	@Query("SELECT u FROM UsuarioToken u WHERE u.uto_token = :token")
	Optional<UsuarioToken> findByUtotoken(@Param("token")String token);
	
	@Query("SELECT ut.uto_token from UsuarioToken ut " +
		   "  INNER JOIN ut.usuario u " +
		   "WHERE (u.usu_email = :email)"
		 + "AND (ut.uto_dthr_expiracao > :dataHora)")
	String getTokenAtivo(@Param("email") String email, @Param("dataHora") LocalDateTime dataHora);
	
	
	@Query("SELECT u FROM UsuarioToken ut " +
		   "  INNER JOIN ut.usuario u " +
		   "WHERE (u.usu_email = :email)")
	UsuarioToken findByEmail(@Param("email") String email);

}
