package com.controleFinanceiro.ControleFinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.controleFinanceiro.ControleFinanceiro.vo.UsuarioVO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioVO, Long> {
	@Query("SELECT u FROM UsuarioVO u WHERE u.usu_name = :name ORDER BY u.id ASC")
    UsuarioVO findByUsuName(@Param("name") String name);
	
	@Query("SELECT u FROM UsuarioVO u WHERE u.usu_email = :email ORDER BY u.id ASC")
	UsuarioVO findByEmail(@Param("email") String email);
	
	@Query("SELECT COUNT(u) > 0 FROM UsuarioVO u WHERE u.usu_email = :email")	
	Boolean getEmail(@Param("email") String email);
	
	@Query("SELECT u FROM UsuarioVO u WHERE u.usu_id = :id")
	UsuarioVO findById(@Param("id") Integer id);

	@Query("SELECT usu_id FROM UsuarioVO WHERE usu_email = :email")
	Integer getIdByEmail(@Param("email") String email);

	@Query("SELECT u FROM UsuarioVO u WHERE u.usu_email = :email ORDER BY u.id ASC")
	UserDetails findByEmailForDetail(@Param("email") String email);

}
 