package com.controleFinanceiro.ControleFinanceiro.VO;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class UsuarioVO {
	
	public UsuarioVO(Long usu_id, String usu_name, String usu_email, String usu_password) {
		this.usu_id = usu_id;
		this.usu_name = usu_name;
		this.usu_email = usu_email;
		this.usu_password = usu_password;
	}
	
	public UsuarioVO() {}

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)	
	private Long usu_id;
	
	@JsonProperty("nome")
	@Column(name = "usu_name")
	private String usu_name;
	
	@JsonProperty("email")
	@Column(name = "usu_email")
	private String usu_email;
	
	@JsonProperty("senha")
	@Column(name = "usu_password")
	private String usu_password;
	
	@Override
	public int hashCode() {
		return Objects.hash(usu_id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioVO other = (UsuarioVO) obj;
		return Objects.equals(usu_id, other.usu_id);
	}
	
	public Long getUsu_id() {
		return usu_id;
	}
	public void setUsu_id(Long usu_id) {
		this.usu_id = usu_id;
	}
	public String getUsu_name() {
		return usu_name;
	}
	public void setUsu_name(String usu_name) {
		this.usu_name = usu_name;
	}
	public String getUsu_email() {
		return usu_email;
	}
	public void setUsu_email(String usu_email) {
		this.usu_email = usu_email;
	}
	public String getUsu_password() {
		return usu_password;
	}
	public void setUsu_password(String usu_password) {
		this.usu_password = usu_password;
	}
	
	@Override
	public String toString() {
		return "UsuarioVO [usu_id=" + usu_id + ", usu_name=" + usu_name + ", usu_email=" + usu_email + ", usu_password="
				+ usu_password + "]";
	}
	
	
	
	
	
	

}
